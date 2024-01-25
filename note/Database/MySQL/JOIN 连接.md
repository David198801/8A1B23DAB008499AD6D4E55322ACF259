标准：

sql92:用类似select t1.a from table1 t1,table2 t2 where t1.id=t2.id连接，仅支持内连接

sql99:内连接、外连接、交叉连接

```javascript
-- 使用内连接取交集
-- INNER JOIN简写为JOIN
select a.id,b.id
from num1_7 as a inner join num5_10 as b
on a.id=b.id;

-- 非等值连接，则取笛卡尔积
SELECT a.id,b.id
FROM num1_7 AS a,num5_10 AS b;
-- 等值连接,取交集
SELECT a.id,b.id
FROM num1_7 AS a,num5_10 AS b
where a.id=b.id;

-- 外连接
-- LEFT JOIN返回A中所有的数据，若B中没有则返回NULL
SELECT a.id,b.id
FROM num1_7 AS a LEFT  JOIN num5_10 AS b
ON a.id=b.id;
-- RIGHT JOIN返回B中所有的数据，若A中没有则返回NULL
SELECT a.id,b.id
FROM num1_7 AS a RIGHT JOIN num5_10 AS b
ON a.id=b.id;
-- 多表连接
SELECT s.studentno,s.studentname,sj.subjectname,r.studentresult
FROM resultinfo AS r JOIN studentinfo AS s ON r.studentno=s.studentno
JOIN subjectinfo AS sj ON r.subjectno=sj.subjectno;


-- 使用外连接取差集
-- A-B,LEFT JOIN取B值为NULL的
SELECT a.id
FROM num1_7 AS a left JOIN num5_10 AS b
ON a.id=b.id
WHERE b.id IS null;
-- B-A
-- A-B,RIGHT JOIN取A值为NULL的
SELECT b.id
FROM num1_7 AS a RIGHT JOIN num5_10 AS b
ON a.id=b.id
WHERE a.id IS null;

-- mysql中没有FULL OUTER JOIN，用left+right union代替
SELECT a.id
FROM num1_7 AS a LEFT JOIN num5_10 AS b
ON a.id=b.id
UNION
SELECT b.id
FROM num1_7 AS a RIGHT JOIN num5_10 AS b
ON a.id=b.id;


/*
SELECT a.id
FROM num1_7 AS a FULL JOIN num5_10 AS b
ON a.id=b.id
WHERE a.id IS NULL OR b.id IS NULL

SELECT a.id
FROM num1_7 AS a left JOIN num5_10 AS b
ON a.id=b.id
WHERE b.id IS null;
UNION
SELECT b.id
FROM num1_7 AS a RIGHT JOIN num5_10 AS b
ON a.id=b.id
WHERE a.id IS null;


*/

```

