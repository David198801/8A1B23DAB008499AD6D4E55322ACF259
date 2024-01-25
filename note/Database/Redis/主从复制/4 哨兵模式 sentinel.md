哨兵模式：监控master是否正常，master关闭后根据票数自动确定新的master



一主二从模式下



1.在工作目录建立

sentinel.conf

```javascript
# sentinel monitor 名称 ip地址 端口号 票数
# 主机关闭后，票数多于1则成为主机
sentinel monitor myhots6379 127.0.0.1 6379 1
```

2.其他终端启动哨兵，阻塞监控

```javascript
redis-sentinel /redis/sentinel.conf
22373:X 27 Apr 18:43:34.318 # WARNING: The TCP backlog setting of 511 cannot be enforced because /proc/sys/net/core/somaxconn is set to the lower value of 128.
22373:X 27 Apr 18:43:34.323 # Sentinel ID is 5274a5d19de36374b2810980b3638d8c56d32a28
22373:X 27 Apr 18:43:34.323 # +monitor master myhots6379 127.0.0.1 6379 quorum 1
22373:X 27 Apr 18:43:34.327 * +slave slave 127.0.0.1:6381 127.0.0.1 6381 @ myhots6379 127.0.0.1 6379
22373:X 27 Apr 18:43:34.328 * +slave slave 127.0.0.1:6380 127.0.0.1 6380 @ myhots6379 127.0.0.1 6379
```

3.

6379 shutdown，哨兵自动选出master，并变更其他slave的master

4.

6379恢复，哨兵自动将其变为slave，指向新的master