Redis集群的关闭、启动、重启等相关指令_JustryDeng-CSDN博客_redis集群重启



redis cluster 集群重启关闭_haoke-CSDN博客



> 集群只要是你开始搭建成功之后只要把集群的所有节点都启动就可以了。



> 集群建立后，每个节点会自动生成对应的nodes-xxxx.conf 配置文件，集群重新启动的时候，只要一次启动之前的每个节点，redis集群就可以正常使用了，我现在测试的是这样