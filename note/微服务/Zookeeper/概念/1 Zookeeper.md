Zookeeper是开源的为分布式提供协调服务的apache项目



工作模式：基于观察者模式，负责储存和管理大家关心的数据，注册观察者，一旦数据发生变化，则通知注册的观察者。



Zookeeper=文件系统+通知机制



- Zookeeper集群分为leader和follower，follower负责client

- 集群中只要有半数以上节点存活，就可以正常服务。

- 全局数据一致，每个server保留相同的副本，client连接到任意server数据都是一致的

- 对client的更新请求按顺序执行

- 数据更新具有原子性

- 实时性，同步时间很短（数据量很小）





数据结构：树形结构，与常见的文件系统类似。节点称为ZNode，每个ZNode默认能储存1MB数据，ZNode可以通过路径唯一确定



