https://blog.csdn.net/wohiusdashi/article/details/89358071



1.启动服务

```javascript
systemctl start mysqld
```



2.获取初次密码

```javascript
grep 'temporary password' /var/log/mysqld.log
```

若没有则删除残留

```javascript
# 删除原来安装过的mysql残留的数据
rm -rf /var/lib/mysql
# 再启动mysql
systemctl start mysqld #启动MySQL
```



3.登录修改密码，必须包含大小写字母、特殊符号、数字，并且长度大于8位，否则报错

```javascript
mysql -uroot -p
```



```javascript
ALTER USER 'root'@'localhost' IDENTIFIED BY 'new password';
```



4.修改编码，配置文件

```javascript
vim /etc/my.cnf
```

[client]下增加

```javascript
default-character-set=utf8
```

[mysqld]下增加

```javascript
character-set-server=utf8
```



5.开启远程访问

执行以下命令开启远程访问限制（注意：下面命令开启的IP是 192.168.19.128，如要开启所有的，用%代替IP）：



```javascript
grant all privileges on *.* to 'root'@'192.168.0.1' identified by '963999@lzbQQ' with grant option;
```

5.1开启防火墙端口3306

```javascript
firewall-cmd --zone=public --add-port=3306/tcp --permanent
```

 #下面3行是参数说明 



          #–zone #作用域   



          #–add-port=80/tcp #添加端口，格式为：端口/通讯协议   



         #–permanent #永久生效，没有此参数重启后失效



      #重启防火墙后看看是否生效



```javascript
firewall-cmd --reload #重启firewall
```



```javascript
firewall-cmd --list-ports #查看已经开放的端口
```



     #如果想永久停止防火墙，执行下面操作

```javascript
systemctl stop firewalld.service         #停止firewall
systemctl disable firewalld.service      #禁止firewall开机启动
```

 

     #查看防火墙状态

```javascript
     firewall-cmd --state        #查看默认防火墙状态（关闭后显示notrunning，开启后显示running）
```

