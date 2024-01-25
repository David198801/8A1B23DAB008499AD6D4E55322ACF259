flashback默认开启

```javascript
-- 查询历史数据
select * from T_CITI_COMMAND as of timestamp to_timestamp('20210728 21:00:00','YYYYMMDD HH24:MI:SS');

-- 开启行移动
alter table T_CITI_COMMAND enable row movement;

-- 恢复数据
flashback table T_CITI_COMMAND to timestamp to_timestamp('20210728 21:00:00','YYYYMMDD HH24:MI:SS'); 
```



flashback设置

https://www.cnblogs.com/fiberhome/p/7273915.html



恢复误删表，对应用户

```javascript
flashback table 表名 to before drop;
```

Oracle误删除数据和表的恢复办法包括truncate - 风行__雄关漫道 - 博客园 (cnblogs.com)