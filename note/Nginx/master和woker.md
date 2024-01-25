nginx默认启动两个进程，master和woker，woker可以有多个

master接受客户端请求，分配任务，woker争抢任务后调用tomcat



master+多个woker的好处：

1.可以reload热部署

2.每个woker进程独立，一个woker出现问题，其他woker继续工作



设置多少个woker？

woker数一般和服务器cpu核心数量一致

设置woker数量

nginx.confg

```javascript
worker_processes  1;
```



woker连接数

```javascript
events {
    worker_connections  1024;
}
```

一个请求，使用2或4个连接，静态资源2个，调用tomcat4个

最大并发数，如4个woker，连接数1024，静态为2048，动态为1024