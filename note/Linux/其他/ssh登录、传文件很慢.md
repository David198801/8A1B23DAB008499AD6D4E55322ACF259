ssh登录很慢、传文件很慢

编辑 /etc/ssh/sshd_config 文件，将 useDNS 中的 yes 改为 no ，关闭UseDNS加速：

**记得去掉注释**

```shell
vim /etc/ssh/sshd_config
```

```javascript
UseDNS no
```
```shell
systemctl restart sshd
```

