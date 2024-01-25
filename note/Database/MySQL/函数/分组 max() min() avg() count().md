https://www.cnblogs.com/bigbigbigo/p/10952895.html



```javascript
-- 简单使用
SELECT SUM(i) FROM num;
SELECT MAX(i) FROM num;
SELECT MIN(i) FROM num;
SELECT AVG(i) FROM num;
-- COUNT(),返回行数
SELECT COUNT(i) FROM num;

-- ! 全部忽略NULL值
-- ! SUM(),AVG()一般用于数值
-- ! MAX(),MIN(),COUNT()可以用于任何类型

-- 传入字符串，根据字符串长度
SELECT MAX(email) FROM student; -- 返回最长的字符串
SELECT MIN(loginpwd) FROM student; -- 返回最短的字符串，有空则返回空

-- 传入日期，根据日期的新老
SELECT MAX(borndate) FROM student; -- 返回最新的日期
SELECT MIN(borndate) FROM student; -- 返回最老的日期

-- 和DISTINCT搭配，去重
SELECT SUM(DISTINCT salary) FROM employees;
SELECT COUNT(gradeid) FROM student; -- 14
SELECT COUNT(DISTINCT gradeid) FROM student; -- 5

-- COUNT()常用，避免单列忽略NULL
SELECT COUNT(*) FROM employees;
SELECT COUNT(1) FROM employees; -- 相当于加了一列1
/*
效率
MYISAM,COUNT(*)最高
Inoodb,COUNT(*)和COUNT(1)差不多，比COUNT(字段)效率高
一般使用COUNT(*)
*/
```

