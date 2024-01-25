CentOS关闭图形界面(x window)2010-06-18 18:211.在图像界面关闭x window:

1.1 shell中运行 init 3  进入文本模式,同时会关闭相关的服务(Xserver 肯定关闭)

1.2 Alt+Ctrl+F1~F6到字符界面，root登陆，ps aux|grep /usr/X11R6/bin/X,得到X进程号，

kill -9 进程号，其实这时已经到文本界面了，没必要关X。（这样太粗鲁了)



2.如果你想一开机后不进入X Window:

编辑/etc/inittab

id:5:initdefault:改成

id:3:initdefault:



3.在文本模式打开图形界面（X window）:

在shell中输入：startx 或者 init 5





关闭服务

1.关闭图形化界面

```javascript
# systemctl set-default multi-user.target
```

 

2.开启图形化界面

```javascript
# systemctl set-default graphical.target
```

 

使用命令后需要reboot重启系统