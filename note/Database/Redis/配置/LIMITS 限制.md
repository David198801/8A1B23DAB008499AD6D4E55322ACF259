

```javascript
maxclients 10000
```

最大客户端数



```javascript
maxmemory <bytes>
```

最大内存，字节数



```javascript
maxmemory-policy noeviction
```

缓存过期策略

# volatile-lru ->使用LRU算法移除key，只针对过期的key
# allkeys-lru -> 使用LRU算法移除key
# volatile-random -> 在过期的key中随机移除
# allkeys-random -> 随机移除任意key
# volatile-ttl -> 移除最接近过期的key

# noeviction -> 不移除任何key，写入时报错



```javascript
maxmemory-samples 5
```

设置样本的数量，LRU算法和最小TTL都不是精确的算法，而是估算值，所以你可以设置样本的数量，redis会检查指定样本数量的key并选择其中符合过期策略的