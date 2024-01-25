DBSIZE

可用版本： >= 1.0.0

时间复杂度： O(1)

返回当前数据库的 key 的数量。

返回值

当前数据库的 key 的数量。

代码示例

```javascript
redis> DBSIZE
(integer) 5

redis> SET new_key "hello_moto"     # 增加一个 key 试试
OK

redis> DBSIZE
(integer) 6
```

