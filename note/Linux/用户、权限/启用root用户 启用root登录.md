ubuntu root用户默认无密码，设置密码启用root用户

```javascript
sudo passwd root
```



你可以通过移除密码来再次锁定 root 用户：

```javascript
sudo passwd -dl root
```







启用root登录

1.修改.profile

```javascript
sudo vim /root/.profile
```

第九行注释，新增

```javascript
#mesg n 2＞ /dev/null || true
tty -s&&mesg n || true
```



2.修改ssh服务

```javascript
sudo vim /etc/ssh/sshd_config
```

开启root登录

```javascript
#PermitRootLogin prohibit-password
PermitRootLogin yes
```

重启ssh服务

```javascript
service ssh restart
```





桌面版需要修改

1.修改50-ubuntu.conf

```javascript
sudo vim /usr/share/lightdm/lightdm.conf.d/50-ubuntu.conf
```



```javascript
greeter-show-manual-login=true
all-guest=false
```

2.修改gdm-autologin

```javascript
sudo vim /etc/pam.d/gdm-autologin
```

第三行注释掉

```javascript
#auth required  pam_succeed_if.so user != root quiet_success
```

3.修改gdm-password

```javascript
sudo vim /etc/pam.d/gdm-password
```

第三行注释

```javascript
#auth required pam_succeed_if.so user != root quiet_success
```

