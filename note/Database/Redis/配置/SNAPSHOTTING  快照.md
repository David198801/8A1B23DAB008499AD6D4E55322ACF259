

```javascript
save 900 1
save 300 10
save 60 10000
```

触发持久化的条件

以上设置表示，三种条件之一将触发rdb

900秒(15分钟)内修改1次

300秒(5分钟)内修改10次

60秒内修改10000次

save "" 不自动触发rdb

```javascript
CONFIG SET SAVE ""
```



```javascript
stop-writes-on-bgsave-error yes
```

bgsave快照出错后拒绝写入



```javascript
stop-writes-on-bgsave-error
```

使用LZF算法压缩快照



```javascript
rdbchecksum yes
```

压缩完成后使用crc64校验



```javascript
dbfilename dump.rdb
```

rdb文件名称