master 6379关闭

```javascript
127.0.0.1:6379> shutdown
not connected>
```

6380 slaveof no one

```javascript
127.0.0.1:6380> slaveof no one
```

则6380的role变为master



其他redis可以slaveof 127.0.0.1 6380，形成新的主从关系，原来就是6380从机的，正常工作