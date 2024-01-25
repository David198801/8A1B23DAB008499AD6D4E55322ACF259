yum安装的redis.service默认使用redis用户启动，没有对/etc的权限



https://blog.csdn.net/faryang/article/details/73430343



这里将该目录其他用户权限改为具有读、写、执行的权限：

```javascript
sudo chmod 757 6379
```

终止redis进程，然后重启redis-server，发现save操作和服务关闭，都可以了。

（1）终止redis进程：

```javascript
ps aux|grep redis
```

pi 12281  1.5  0.4  35788  3988 pts/4    Sl+  04:59   0:05 redis-server *:6379

pi  12388  0.0  0.1   5728  1668 pts/0    S+   05:06   0:00 grep --color=auto redis

```javascript
kill -9 12281
```

（2）redis保存操作：

127.0.0.1:6379> save

OK

（3）redis服务关闭：

12412:signal-handler (1497763338) Received SIGTERM scheduling shutdown...

12412:M 18 Jun 05:22:18.723 # User requested shutdown...

12412:M 18 Jun 05:22:18.723 * Saving the final RDB snapshot before exiting.

12412:M 18 Jun 05:22:18.750 * DB saved on disk

12412:M 18 Jun 05:22:18.751 # Redis is now ready to exit, bye bye...

