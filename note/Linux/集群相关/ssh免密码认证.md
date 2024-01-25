ssh-keygen的使用方法及配置authorized_keys两台linux机器相互认证

https://blog.csdn.net/simploving/article/details/79631588



1.执行，生成.ssh隐藏目录，

cd .ssh进入目录

winscp 按CTRL+alt+h可查看隐藏目录

```javascript
ssh-keygen -t rsa -P '' -f ~/.ssh/id_rsa
cd .ssh
```

2.添加到authorized_keys中

```javascript
cat id_rsa.pub >> ~/.ssh/authorized_keys
```

3.将生成的id_rsa.pub中的内容追加到其他服务器的~/.ssh/authorized_keys，没有则创建并保证其权限

设置authorized_keys权限、设置.ssh目录权限

```javascript
chmod 600 ~/.ssh/authorized_keys 
chmod 700 ~/.ssh
```

4.复制cat id_rsa.pub到其他机器上，重复3

```javascript
scp -r /root/.ssh/  root@centos129:/root/
scp -r /root/.ssh/  root@centos130:/root/
```

5.使用ssh 主机名登录，logout退出



登录本机需要密码，可以重新生成

> 解决ssh localhost中root@localhost:要求输入密码问题（已经进行了无密码设置登录）

> 首先删除~/.ssh目录下的3个文件，如下

> id_rsa

> authorized_keys

> id_rsa.pub

> 然后

> exit 　　# 退出刚才的 ssh localhost

> cd ~/.ssh/ 　　 # 若没有该目录，请先执行一次ssh localhost

> ssh-keygen -t rsa 　　 # 会有提示，都按回车就可以

> cat ./id_rsa.pub >> ./authorized_keys 　　 # 加入授权

> 再次启动ssh localhost就行了



ssh认证警告，会自动添加认证

或关闭检查

1、切换root    ,使用：su -   。这里一定要切换root用户，因为普通用户的/etc/ssh/ssh_config下，可能没有#StrictHostKeyChecking ask项。

2、vim  /etc/ssh/ssh_config。 找到#StrictHostKeyChecking ask直接去掉注释即可，如果不行建议并把ask改为no