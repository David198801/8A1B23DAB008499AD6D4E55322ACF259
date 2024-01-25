

```javascript
package zk;

import org.apache.zookeeper.*;

import java.io.IOException;
import java.util.concurrent.CountDownLatch;

public class ZkClient {
    private String connectSting = "centos128,centos129,centos130";
    private int sessionTimeout = 2000;
    private ZooKeeper zooKeeper;
    private CountDownLatch connectedLatch = new CountDownLatch(1);


    public void init() throws IOException, InterruptedException {
        zooKeeper = new ZooKeeper(connectSting, sessionTimeout, new Watcher() {
            @Override
            public void process(WatchedEvent watchedEvent) {
                if (watchedEvent.getState() == Event.KeeperState.SyncConnected) {
                    connectedLatch.countDown(); /* ZK连接成功时，计数器由1减为0 */
                }
            }
        });
        waitUntilConnected();
    }

    public void create() throws KeeperException, InterruptedException {
        zooKeeper.create("/study2","s1-1".getBytes(), ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.PERSISTENT);
    }

    public ZooKeeper getZooKeeper() {
        return this.zooKeeper;
    }

    public void waitUntilConnected() {
        if (zooKeeper.getState() == ZooKeeper.States.CONNECTING) {
            try {
                connectedLatch.await();  //此函数会一直阻塞当前进程，直到计数器的值为0为止
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }



}
```



```javascript
@Test
public void t1() throws Exception {
    ZkClient zkClient = new ZkClient();
    zkClient.init();
    zkClient.create();
}
```





old

```javascript
package zk;

import org.apache.zookeeper.*;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.concurrent.CountDownLatch;

public class ZkClient {
    private String connectSting = "192.168.84.129:2181,192.168.84.130:2181,192.168.84.131:2181";
    private int sessionTimeout = 2000;
    private ZooKeeper zooKeeper;


    public void init() throws IOException, InterruptedException {
        CountDownLatch sampleLatch = new CountDownLatch(1);
        Watcher sampleWatcher = new ConnectedWatcher(sampleLatch);
        zooKeeper = new ZooKeeper(connectSting, sessionTimeout, sampleWatcher);
        waitUntilConnected(zooKeeper, sampleLatch);
    }

    public void create() throws KeeperException, InterruptedException {
        zooKeeper.create("/study2","s1-1".getBytes(), ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.PERSISTENT);
    }

    static class ConnectedWatcher implements org.apache.zookeeper.Watcher {
        private CountDownLatch connectedLatch;

        ConnectedWatcher(CountDownLatch connectedLatch) {
            this.connectedLatch = connectedLatch;  /* CountDownLatch实例初始化时设为1即可 */
        }

        @Override
        public void process(WatchedEvent event) {
            if (event.getState() == Event.KeeperState.SyncConnected) {
                connectedLatch.countDown(); /* ZK连接成功时，计数器由1减为0 */
            }
        }
    }


    public static void waitUntilConnected(ZooKeeper testZooKeeper, CountDownLatch testLatch) {
        if (testZooKeeper.getState() == ZooKeeper.States.CONNECTING) {
            try {
                testLatch.await();  //此函数会一直阻塞当前进程，直到计数器的值为0为止
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
    
    public ZooKeeper getZooKeeper() {
        return this.zooKeeper;
    }
}
```

