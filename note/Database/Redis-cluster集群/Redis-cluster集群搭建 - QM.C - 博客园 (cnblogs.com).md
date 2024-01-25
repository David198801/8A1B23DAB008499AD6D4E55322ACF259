Redis-cluster集群搭建 - QM.C - 博客园 (cnblogs.com)









1 解压Redis-x64-3.2.100 .zip,在redis安装目录下创建

	7000/redis.windows-service.conf （可按端口分配多个集群节点）

	修改redis.windows.conf的配置文件



	每一个节点都穿线一个配置文件，修改以下内容 

	bind 修改成本机ip地址

	appendonly yes

	cluster-enabled yes

	cluster-node-timeout 15000

	#每个节点的配置文件

	cluster-config-file nodes-7000.conf

	cluster-slave-validity-factor 10

	cluster-require-full-coverage yes

	cluster-migration-barrier 1

	

	

	节点配置完后运行命令安装redis服务

	最好一条一条执行

	start redis-server.exe --service-install 7001/redis.windows-service.conf --service-name Redis7001

	start redis-server.exe --service-install 7002/redis.windows-service.conf --service-name Redis7002

	start redis-server.exe --service-install 7003/redis.windows-service.conf --service-name Redis7003

	start redis-server.exe --service-install 7004/redis.windows-service.conf --service-name Redis7004

	start redis-server.exe --service-install 7005/redis.windows-service.conf --service-name Redis7005

	start redis-server.exe --service-install 7000/redis.windows-service.conf --service-name Redis7000



	

2、安装ruby



3、拷贝redis-trib.rb文件到redids 的安装目录下， 拷贝redis-4.1.2.gem 到 ruby安装目录下



4、运行gem install -l C:\Ruby26-x64\redis-4.1.2.gem 安装redis依赖

	-- 目录不许有空格

   运行gem install redis 



5、搭建redis集群命令

	redis-trib.rb create --replicas 1 127.0.0.1:7001 127.0.0.1:7002 127.0.0.1:7003 127.0.0.1:7004 127.0.0.1:7005 127.0.0.1:7000



	redis集群卸载

	redis-server --service-uninstall --service-name redis7001

	redis-server --service-uninstall --service-name redis7002

	redis-server --service-uninstall --service-name redis7003

	redis-server --service-uninstall --service-name redis7004

	redis-server --service-uninstall --service-name redis7005

	redis-server --service-uninstall --service-name redis7000