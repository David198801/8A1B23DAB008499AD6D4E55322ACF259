https://blog.csdn.net/v123411739/article/details/78996181

https://snailclimb.gitee.io/javaguide/#/docs/java/collection/HashMap(JDK1.8)%E6%BA%90%E7%A0%81+%E5%BA%95%E5%B1%82%E6%95%B0%E6%8D%AE%E7%BB%93%E6%9E%84%E5%88%86%E6%9E%90?id=hashmap-%e7%ae%80%e4%bb%8b



key和value允许为null



数据结构：

数组+链表



默认初始大小16

如果用户通过构造函数指定了一个数字作为容量，那么Hash会选择大于该数字的第一个2的幂作为容量。(3->4、7->8、9->16)

jdk1.8中，在调用HashMap的构造函数定义HashMap的时候，就会进行容量的设定。而在Jdk 1.7中，要等到第一次put操作时才进行这一操作。



负载因子0.75，大于等于这个值时，会扩容至2倍





JDK1.8 当链表长度大于阈值（默认为 8）且数组长度超过 64会将链表转为红黑树。







