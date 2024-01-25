报错：(error) READONLY You can't write against a read only slave.

报错原因：

服务为slave模式，并且slave-read-only为yes

解决方案：

1.

2.进入redis.conf配置文件，修改配置文件的slave-read-only为no，那么从节点也就可以进行写的操作了，代码不会报错