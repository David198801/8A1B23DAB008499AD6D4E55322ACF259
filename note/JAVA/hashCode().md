规定：

1.一致性（consistent），在程序的一次执行过程中，对同一个对象必须一致地返回同一个整数。

2.如果两个对象通过equals(Object)比较，结果相等，那么对这两个对象分别调用hashCode方法应该产生相同的整数结果。（PS：这里equals和hashCode说的都是Object类的）

3.如果两个对象通过java.lang.Object.equals(java.lang.Ojbect)比较，结果不相等，不必保证对这两个对象分别调用hashCode也返回两个不相同的整数。





OpenJDK，hotspot实现

hashCode()返回的并不一定是内存地址，而是在不同情况下根据不同算法生成值

0：PRNG(Park-Miller RNG)随机数算法

1：对象指针与随机数进行位运算

2：固定值1

3：自增变量

4：对象指针

5：Marsaglia XOR-shift随机数算法

https://blog.csdn.net/zhxdick/article/details/108627063

JVM相关 - SafePoint 与 Stop The World 全解(基于OpenJDK 11版本)

https://blog.csdn.net/xusiwei1236/article/details/45152201/

https://blog.csdn.net/bluetjs/article/details/52610414

https://blog.csdn.net/u013256816/article/details/117915069

测试

https://blog.csdn.net/weixin_38869158/article/details/107518219