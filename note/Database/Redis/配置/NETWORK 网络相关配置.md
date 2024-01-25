网络相关配置



```javascript
port 6379
```

端口



```javascript
tcp-backlog 511
```

设置tcp的backlog，backlog是一个连接队列，backlog队列总和=未完成3次握手队列+已完成3次握手队列，高并发下需要配置backlog来避免客户端连接慢的问题

这个值受到linux的/proc/sys/net/core/somaxconn的限制，所以要确认somaxconn和tcp_max_syn_backlog的值



```javascript
bind 192.168.1.100 10.0.0.1
```

绑定ip和端口



```javascript
timeout 0
```

空闲关闭连接的时间



```javascript
tcp-keepalive 300
```

keepalive检测的间隔建议设置为60

tcp长连接会检测连接是否正常