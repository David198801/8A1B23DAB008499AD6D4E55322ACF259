

```javascript
-- 起别名
-- 使用AS
SELECT `id` AS '编号' FROM `student`;
-- 省略AS
SELECT 100 '数值';
```



表的别名

```javascript
SELECT w.name, w.url, a.count, a.date
FROM Websites AS w, access_log AS a
WHERE a.site_id=w.id and w.name="菜鸟教程";
```

