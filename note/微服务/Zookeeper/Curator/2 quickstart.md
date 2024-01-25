可重入，可以多次进入，多次释放

```javascript
package curator;

import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.framework.recipes.locks.InterProcessMutex;
import org.apache.curator.retry.ExponentialBackoffRetry;

public class CuratorTest extends Thread{

    public CuratorTest(String name) {
        super(name);
    }

    @Override
    public void run() {
        try {
            //创建分布式锁
            InterProcessMutex lock = new InterProcessMutex(getCuratorFramework(), "/locks");

            lock.acquire();
            System.out.println(Thread.currentThread().getName() + "获取到锁");
            lock.acquire();
            System.out.println(Thread.currentThread().getName() + "再次获取到锁");

            Thread.sleep(200);
            System.out.println(Thread.currentThread().getName() + "处理业务");

            lock.release();
            System.out.println(Thread.currentThread().getName() + "释放锁");
            lock.release();
            System.out.println(Thread.currentThread().getName() + "再次释放锁");
        } catch (Exception e) {
            e.printStackTrace();
        }

    }


    public static void main(String[] args) {
        for (int i = 0; i < 3; i++) {
            new CuratorTest("线程"+i).start();
        }
    }

    private static CuratorFramework getCuratorFramework() {
        ExponentialBackoffRetry policy = new ExponentialBackoffRetry(3000, 3);
        CuratorFramework framework = CuratorFrameworkFactory.builder().connectString("centos128,centos129,centos130")
                .connectionTimeoutMs(2000)
                .sessionTimeoutMs(2000)
                .retryPolicy(policy).build();
        framework.start();
        System.out.println("客户端" + Thread.currentThread().getName() + "启动成功");
        return framework;
    }
}
```

