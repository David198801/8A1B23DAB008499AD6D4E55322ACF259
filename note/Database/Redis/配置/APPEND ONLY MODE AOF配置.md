

```javascript
appendonly no
```

默认关闭AOF



```javascript
appendfilename "appendonly.aof"
```

AOF文件名



```javascript
# appendfsync always
appendfsync everysec
# appendfsync no
```

AOF策略

no：不 fsync，让操作系统来决定什么时候进行刷盘。最不会影响 Server 响应。

always：每写入 aof 文件就进行 fsync。影响 Server 响应，但是数据更安全。

everysec：每秒进行 fsync。最稳健的形式。



```javascript
no-appendfsync-on-rewrite no
```

在重写时关闭appendfsync，不向aof文件写入

```javascript
auto-aof-rewrite-percentage 100
auto-aof-rewrite-min-size 64mb
```

重写触发大小（上次重写aof文件的大小的百分比）

首次重写大小（一般在3G以上）