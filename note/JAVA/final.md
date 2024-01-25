final修饰类：不能被继承

final修饰方法：不能被重写

final修饰变量：常量，初始化后不能修改，如果是引用变量则地址不能修改

可以修饰局部变量

可以修饰形参：传入后不可修改

```javascript
pulic void method(final int a){
    //a不可修改,a=0;报错
}
```

