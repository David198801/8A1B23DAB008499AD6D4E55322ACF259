每个台阶都有踩或不睬2种可能，除了最后一个必须踩，

故一共2^(n-1)次

```javascript
public int JumpFloorII(int target) {
    return 1<<--target;
}
```



```javascript
public int JumpFloorII(int target) {
	return (int)Math.pow(2,target-1);
}
```



另一种分析

第1步可以跳1，2，3...n阶共n种跳法

跳1阶，剩n-1阶有f(n-1)种跳法

跳2阶，剩n-2阶有f(n-2)种跳法

...

跳n-1阶，剩1阶有f(1)种跳法

故f(n)=f(n-1)+f(n-2)+...+f(1)

f(n-1)=f(n-2)+...+f(1)

相减得f(n)=2f(n-1)

等比数列公式an=a1*q^(n-1)得f(n)=2^(n-1)

