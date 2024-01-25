KEYS pattern

可用版本： >= 1.0.0

时间复杂度： O(N)， N 为数据库中 key 的数量。

查找所有符合给定模式 pattern 的 key ， 比如说：

- KEYS * 匹配数据库中所有 key 。

- KEYS h?llo 匹配 hello ， hallo 和 hxllo 等。

- KEYS h*llo 匹配 hllo 和 heeeeello 等。

- KEYS h[ae]llo 匹配 hello 和 hallo ，但不匹配 hillo 。

特殊符号用 \ 转义。

```javascript
127.0.0.1:6379> keys ?\?
1) "k?"
```



KEYS 的速度非常快，但在一个大的数据库中使用它仍然可能造成性能问题，如果你需要从一个数据集中查找特定的 key ，你最好还是用 Redis 的集合结构(set)来代替。