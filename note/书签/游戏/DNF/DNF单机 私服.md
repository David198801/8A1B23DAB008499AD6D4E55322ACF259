架设教程

https://bbs.aladedalu.com/thread-422-1-1.html



1.远程登录阿里云服务器

打开putty，填写服务器IP地址



![](assets/DNF单机%20私服_image_0.png)





点open



![](assets/DNF单机%20私服_image_1.png)





输入root，回车，你的密码，回车，显示登录成功了



![](assets/DNF单机%20私服_image_2.png)







2.安装支持库和数据库（一行为一条命令，输完命令要回车）

#关闭防火墙和防火墙自启

1. service iptables stop

1. chkconfig iptables off

复制代码







![](assets/DNF单机%20私服_image_3.png)



#更新源，很多人yum安装出问题，就是没更新源

1. wget -O /etc/yum.repos.d/CentOS-Base.repo http://mirrors.aliyun.com/repo/Centos-5.repo

1. yum makecache

复制代码



![](assets/DNF单机%20私服_image_4.png)





#安装支持库

1. yum -y install glibc.i686

1. yum -y install xulrunner.i686

1. yum -y install libXtst.i686

1. yum -y install gcc gcc-c++ make zlib-devel

复制代码





#安装数据库

1. yum install -y mysql mysql-server mysql-devel

复制代码



#数据库开机启动

1. chkconfig mysqld on

复制代码



2.下载服务端文件并解压

#下载服务端打包文件，用winscp上传到服务器/root/目录下（该文件请自己下载保存，链接将在2018年7月10日前失效）

服务端文件打包DNFServer.tar.gz：http://pan.baidu.com/s/1gfAy171

上传完成后再执行下面的步骤。



#解压下载的文件

1. tar -zxvf ./DNFServer.tar.gz -C /

复制代码



3.设置对应权限

#home root mysql文件夹全部权限

1. chmod -R 777 /home

1. chmod -R 777 /root

1. chmod -R 777 /var/lib/mysql

1. chown -R mysql.mysql /var/lib/mysql/

复制代码





#重启数据库，如果最后一个是绿色的OK，就表示数据库装成功了。

1. service mysqld restart

复制代码



![](assets/DNF单机%20私服_image_5.png)





4.编译GeoIP

1. cd /home/GeoIP-1.4.8/

1. ./configure

1. make && make check && make install

复制代码





![](assets/DNF单机%20私服_image_6.png)





5.修改对应位置的外网IP

用Navicat打开数据库d_taiwan的db_connect表，把db_ip字段所有的192.168.200.131改成你的外网IP。



![](assets/DNF单机%20私服_image_7.png)





#服务端程序里面的用命令改，注意把“你的外网IP”替换成你的外网IP

1. cd /home/dxf/

1. sed -i "s/192.168.200.131/你的外网IP/g" `find . -type f -name "*.tbl"`

1. sed -i "s/192.168.200.131/你的外网IP/g" `find . -type f -name "*.cfg"`

复制代码





#挂载虚拟内存，默认大小为8G

1. mkdir /swap

1. dd if=/dev/zero of=/swap/mySwap bs=1M count=8192

1. mkswap /swap/mySwap

1. swapon /swap/mySwap

复制代码





#添加开机自动挂载

1. sed -i '$a /swap/mySwap swap swap default 0 0' /etc/fstab

复制代码



![](assets/DNF单机%20私服_image_8.png)





到这里架设就结束了，接下来用winscp把Script.pvf上传到服务器/home/dxf/game/目录下。

![](assets/DNF单机%20私服_image_9.png)





右边就是服务器目录，切换到/home/dxf/game/目录，把pvf拖进去

![](assets/DNF单机%20私服_image_10.png)





#启动服务端程序

1. cd ~

1. ./run

复制代码





等到出现下面的信息就可以进游戏了。



![](assets/DNF单机%20私服_image_11.png)



游客，如果您要查看本帖隐藏内容请回复