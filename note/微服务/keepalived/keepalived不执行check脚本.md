1.首先手动执行脚本检查nginx是否启动，确保脚本没有权限、语法等问题



2.关闭selinux，https://github.com/acassen/keepalived/issues/1322

临时关闭

```javascript
setenforce 0
```

永久关闭

```javascript
sed -i "s/^SELINUX=.*/SELINUX=disabled/g" /etc/selinux/config
```



3.

检查vrrp_script中脚本名是否写对

检查vrrp_instance中track_script的内容是否与vrrp_script定义的相同



https://blog.csdn.net/Bb15070047748/article/details/106276491/

