脚本执行一直不成功



systemctl status keepalived -l

发现exited due to signal 15



问题1：Keepalived执行chk_haproxy.sh检测脚本时出现exited due to signal 15



解决1：vrrp_script{}中的interval时间需大于脚本中的sleep时间



问题2：通过查看日志发现slave机器启动后也进入master状态



解决2：主/备节点轮流在对外发布vrrp通告，理论上备节点如果收到主节点的通告，通告中优先级高于自己，就不会主动对外发送通告。查看iptables，默认没有允许vrrp或者组播流量，导致备节点收不到主节点的通告，认为主节点故障，切换状态，发布VIP

https://blog.csdn.net/user_rcy/article/details/77931451