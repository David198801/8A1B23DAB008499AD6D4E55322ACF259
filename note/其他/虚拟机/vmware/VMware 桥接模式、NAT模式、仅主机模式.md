https://www.jianshu.com/p/85d41c49fdcd

https://blog.csdn.net/qq_42968686/article/details/105641468



1.桥接模式：

虚拟机网卡直接连接宿主机所在的外部网络，相当于外部网络中再添加一台机器

外部网络可以直接访问虚拟机

![](assets/VMware%20桥接模式、NAT模式、仅主机模式_image_0.png)



2.NAT模式：

虚拟机与宿主机通信：虚拟机网卡与宿主机虚拟网卡Vmnet8连接至同一网段

虚拟机连接外网：虚拟机网卡通过虚拟NAT设备映射到宿主机连接外网的网卡

外部网络其他ip访问虚拟机，需要设置NAT端口映射，通过宿主机网卡的映射访问虚拟机

![](assets/VMware%20桥接模式、NAT模式、仅主机模式_image_1.png)



3.仅主机模式：

虚拟机与宿主机通信：虚拟机网卡与宿主机虚拟网卡Vmnet1连接至同一网段

虚拟机连接外网：无法直接访问外网，需要宿主机通过虚拟网卡Vmnet1分享网络

![](assets/VMware%20桥接模式、NAT模式、仅主机模式_image_2.png)

