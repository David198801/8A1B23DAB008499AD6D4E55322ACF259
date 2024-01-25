https://blog.csdn.net/lyn631579741/article/details/123400651



因为默认的源是 http 的，但是准备使用的镜像源是 https 的，所以需要额外的安装有关 HTTPS 的包

所以安装这两个包之后就可以正常拉取https的源了：

```javascript
sudo apt install apt-transport-https
```



```javascript
sudo apt install ca-certificates
```

