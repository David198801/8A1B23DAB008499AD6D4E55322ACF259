https://blog.csdn.net/RedaTao/article/details/100893444



被扫描弱端口，关闭密码登录或更换ssh端口



关闭密码登录

```javascript
vim /etc/ssh/sshd_config
```



```javascript
PasswordAuthentication no
```

重启服务

```javascript
systemctl restart sshd.service
```







> 好久没弄自己的VPS, 今天登录ssh 发现下面的警告

> There were 16888 failed login attempts since the last successful login.

> 难道被攻击了？？？ 其实就是一种入侵检测 可用免密公钥私钥的方式登录 并将停掉输入密码验证的方式

> 补充 想看看哪些人没事来你家试着开锁吗

> grep "Failed password for invalid user" /var/log/secure | awk '{print $13}' | sort | uniq -c | sort -nr | more