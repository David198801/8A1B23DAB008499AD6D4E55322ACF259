HEXISTS hash field

可用版本： >= 2.0.0

时间复杂度： O(1)

检查给定域 field 是否存在于哈希表 hash 当中。

返回值

HEXISTS 命令在给定域存在时返回 1 ， 在给定域不存在时返回 0 。

代码示例

给定域不存在：

```javascript
redis> HEXISTS phone myphone
(integer) 0
```

给定域存在：

```javascript
redis> HSET phone myphone nokia-1110
(integer) 1

redis> HEXISTS phone myphone
(integer) 1

```

