general，通用配置



```javascript
daemonize no
```

yes，no

是否以守护线程运行



```javascript
pidfile /var/run/redis_6379.pid
```

以守护线程运行时，pid文件位置



```javascript
loglevel notice
```

日志级别

# debug (a lot of information, useful for development/testing)
# verbose (many rarely useful info, but not a mess like the debug level)
# notice (moderately verbose, what you want in production probably)
# warning (only very important / critical messages are logged)



```javascript
logfile /var/log/redis/redis.log
```

日志位置，给""则为控制台输出



```javascript
syslog-enabled no
```

是否把系统日志输出到syslog中



```javascript
syslog-ident redis
```

指定syslog里日志的标志



```javascript
syslog-facility local0
```

指定syslog的设备，可以是USER 或 LOCAL0-LOCAL7



```javascript
databases 16
```

数据库数量