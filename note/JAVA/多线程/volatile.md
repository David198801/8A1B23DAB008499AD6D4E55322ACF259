

```javascript
public class VolatileBarrierExample {
    private static boolean stop = false;

    public static void main(String[] args) throws InterruptedException {
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                while (!stop) {
                }
            }
        });

        thread.start();
        Thread.sleep(1000);
        stop = true;
        thread.join();
    }
}
```





https://www.jianshu.com/p/195ae7c77afe?from=timeline&isappinstalled=0



原理

https://www.cnblogs.com/dolphin0520/p/3920373.html



volatile保证可见性、有序性，无法保证原子性



synchronized区别

https://blog.csdn.net/suchahaerkang/article/details/80456085



应用

https://zhuanlan.zhihu.com/p/112742540

https://www.cnblogs.com/ouyxy/p/7242563.html





内存可见性，指令重排序，JIT。。。。。。从一个知乎问题谈起

https://www.cnblogs.com/stevenczp/p/7978554.html