redis-benchmark，测试各项性能



redis-check-aof，检查/修复aof文件

```javascript
# redis-check-aof appendonly.aof
0x             20d: Expected prefix 's', got: '*'
AOF analyzed: size=596, ok_up_to=525, diff=71
AOF is not valid
```

```javascript
# redis-check-aof --fix appendonly.aof
0x             20d: Expected prefix 's', got: '*'
AOF analyzed: size=596, ok_up_to=525, diff=71
This will shrink the AOF from 596 bytes, with 71 bytes, to 525 bytes
Continue? [y/N]: y
Successfully truncated AOF
```

