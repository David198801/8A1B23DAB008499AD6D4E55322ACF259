https://zhuanlan.zhihu.com/p/88555159



今天分享一个JDK中令人惊讶的BUG，这个BUG的神奇之处在于，复现它的用例太简单了，人肉眼就能回答的问题，JDK中却存在了十几年。经过测试，我们发现从JDK8到14都存在这个问题。

大家可以在自己的开发平台上试试这段代码：

```javascript
public class Hello {
    public void test() {
        int i = 8;
        while ((i -= 3) > 0);
        System.out.println("i = " + i);
    }
    
    public static void main(String[] args) {
        Hello hello = new Hello();
        for (int i = 0; i < 50_000; i++) {
            hello.test();
        }
    }
}
```

再使用以下命令执行：

java Hello

然后，就会看到这样的输出：

```javascript
i = /
i = /
i = /
```

当然，在程序的开始阶段，还是能打印出正确的"i = -1"。

这个问题最终Huawei JDK的两名同事解决掉了，并且回合到社区。我这里大概讲一下分析的思路。

首先，使用解释执行可以发现，结果都是正确的，这就说明，这基本上是JIT编译器的问题，然后通过-XX:-TieredCompilation关闭C1编译，问题同样复现，但是使用-XX:TieredStopAtLevel=3将JIT编译停留在C阶段，问题就不复现，这可以确定是C2的问题了。

接下来，一名同事立即猜想到这个"/"其实是('0'-1)，刚好是字符零的ascii码减掉1。嗯，熟记ascii码表的重要性就体现出来了。接下来，就是找到c2中 int 转字符的地方。关键点，就在于这个字符'0'，当然这里要对C2有足够的了解，马上就找到c2中字符转化的方法（具体的代码 ，请参考OpenJDK社区）：

```javascript
void PhaseStringOpts::int_getChars(GraphKit& kit, Node* arg, Node* char_array, Node* start, Node* end) {
  // ......
  // char sign = 0;

  Node* i = arg;
  Node* sign = __ intcon(0);

  // if (i < 0) {
  //     sign = '-';
  //     i = -i;
  // }
  {
    IfNode* iff = kit.create_and_map_if(kit.control(),
                                        __ Bool(__ CmpI(arg, __ intcon(0)), BoolTest::lt),
                                        PROB_FAIR, COUNT_UNKNOWN);

    RegionNode *merge = new (C) RegionNode(3);
    kit.gvn().set_type(merge, Type::CONTROL);
    i = new (C) PhiNode(merge, TypeInt::INT);
    kit.gvn().set_type(i, TypeInt::INT);
    sign = new (C) PhiNode(merge, TypeInt::INT);
    kit.gvn().set_type(sign, TypeInt::INT);

    merge->init_req(1, __ IfTrue(iff));
    i->init_req(1, __ SubI(__ intcon(0), arg));
    sign->init_req(1, __ intcon('-'));
    merge->init_req(2, __ IfFalse(iff));
    i->init_req(2, arg);
    sign->init_req(2, __ intcon(0));

    kit.set_control(merge);

    C->record_for_igvn(merge);
    C->record_for_igvn(i);
    C->record_for_igvn(sign);
  }

  // for (;;) {
  //     q = i / 10;
  //     r = i - ((q << 3) + (q << 1));  // r = i-(q*10) ...
  //     buf [--charPos] = digits [r];
  //     i = q;
  //     if (i == 0) break;
  // }

  {
   // 略去和这个循环相对应的代码 
  }

  // 略去很多代码 
}
```

可以看到，这里在中间表示阶段引入了一个“i < 0"的判断。主要就是那个CmpI结点，看起来这里的逻辑走错了，导致 i 明明小于0，结果却走到了大于0的分支，这样，直接拿字符'0'与i求和的结果，就是错的了。

那这个CmpI为什么会错呢？使用c2visualizer工具可以看到，在GVN阶段，上面循环中的CmpI和这里引入的CmpI被合并了。GVN的全称是Global Value Numbering，名字很高大上，其实就是表达式去重。例如：

![](assets/JVM%20JIT%20BUG_image_0.jpeg)

上面的例子中，两个 CmpI 的输入参数是完全相同的。都是变量 i 和整数 0，那么，这两个CmpI 结点其实就是完全相同的。这样的话，编译器在做中间优化的时候就会把这两个CmpI结点合并成一个。

到这里为止，其实还是没问题的。但接下来，编译器会对空的循环体做一些特别的变换，编译器能直接计算出空循环体结束以后，i 的值是 -1，又发现空循环体什么都不做，所以，它干脆把CmpI的两个参数都换成了 -1，以便于让循环走不进来——而且，编译器再做一次常量传播就可以把这个CmpI彻底干掉了。但是，这里CmpI就有问题了，这里强行搞成 False 让循环不执行，并且把 i 的值也直接变成循环结束的那个值。但刚才合并的那个CmpI 也被吃掉了。

这就导致，直接拿着 i = -1 这个值进到了 i >= 0 的分支里支了。所以修改也很简单，那就是在对CmpI变换的时候，看看它还有没有其他的out，如果有，就复制一份出来。

这个BUG的相关issue和patch在这里：

https://bugs.openjdk.java.net/projects/JDK/issues/JDK-8231988?filter=allissues​bugs.openjdk.java.net/projects/JDK/issues/JDK-8231988?filter=allissues

JBS系统上没有详细的分析过程，只有最后的patch，所以我把这个问题写了个总结发在这里。可以看到，即使是很简单的测试用例，在编译器内部也会经历各种复杂的变换和优化。然后一些阶段的优化可能会影响后一个阶段的，所以编译器的BUG也往往晦涩。但反过来说，也很有意思。