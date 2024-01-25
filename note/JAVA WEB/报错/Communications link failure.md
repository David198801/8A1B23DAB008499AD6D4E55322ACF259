com.mysql.jdbc.exceptions.jdbc4.CommunicationsException:Communications link failure



可能

1.url未关闭useSSL，但没有配置SSL，url去掉useSSL=true或使用useSSL=false显式关闭

其他可能

2.未开启mysql服务

https://blog.csdn.net/qq_27471405/article/details/80921846

https://blog.csdn.net/zouxucong/article/details/53924414