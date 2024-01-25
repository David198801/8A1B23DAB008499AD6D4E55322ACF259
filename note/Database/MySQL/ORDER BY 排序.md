

```javascript
-- 默认升序 ASC
SELECT * FROM student ORDER BY id;
SELECT * FROM student ORDER BY id ASC; -- 同上
-- 降序 DESC
SELECT * FROM student ORDER BY id DESC;

-- 按别名排序
SELECT LENGTH(pwd) AS 密码长度,pwd,id FROM student ORDER BY 密码长度 DESC;

-- 按多个字段排序
SELECT LENGTH(pwd) AS 密码长度,pwd,id FROM student ORDER BY 密码长度 DESC,id ASC;

```

