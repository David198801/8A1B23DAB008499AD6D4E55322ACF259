-- SELECT字段
SELECT `id` FROM `student`;
SELECT `id`,`pwd` FROM `student`;
-- 可重复
SELECT id,id FROM `student`;
-- 所有字段
SELECT * FROM `student`;
SELECT *,id FROM `student`; -- *可以在前面，不能在后面


-- SELECT常量
SELECT 100;
SELECT 'abc';

-- SELECT表达式
SELECT 100%98;

-- SELECT函数
SELECT VERSION(); -- 查看版本
SELECT DATABASE(); -- 查看在USE 哪个数据库

-- 使用LIMIT
/*
LIMIT行数 OFFSET偏移量 OFFSET必须和limit共用 limit 1 offset 2 可以写成limit 2,1
[LIMIT N][ OFFSET M]
*/
select id from city limit 5 offset 2; -- id：3、4、5、6、7
select id from city limit 2,5; -- 同上

-- 去重
-- 使用DISTINCT
SELECT DISTINCT `pwd` FROM `student`;
-- 使用GROUP BY
SELECT DISTINCT `id`,`pwd` FROM `student` GROUP BY `pwd`;

-- MySQL中+号只作为运算符，+字符串会将字符串转换为数值，转换失败则将字符转换为0
-- 运算中一方为NULL，则结果为NULL
SELECT '1' + 1; -- 2
SELECT 'abc' + 1; -- 1
SELECT NULL + 1; -- NULL

-- 连接字符串 CONCAT(str1,str2,...)
SELECT CONCAT('123','abc','你好') AS 字符串;

-- 替换NULL IFNULL(字段名,替换值)
SELECT IFNULL(address,0) FROM student; -- 显示类名为ifnull(address,0)


-- 起别名
-- 使用AS
SELECT `id` AS '编号' FROM `student`;
-- 省略AS
SELECT 100 '数值';


-- 默认升序 ASC
SELECT * FROM student ORDER BY id;
SELECT * FROM student ORDER BY id ASC; -- 同上
-- 降序 DESC
SELECT * FROM student ORDER BY id DESC;

-- 按别名排序
SELECT LENGTH(pwd) AS 密码长度,pwd,id FROM student ORDER BY 密码长度 DESC;

-- 按多个字段排序
SELECT LENGTH(pwd) AS 密码长度,pwd,id FROM student ORDER BY 密码长度 DESC,id ASC;



SELECT * FROM student WHERE id=1;
SELECT * FROM student WHERE id = '1'; -- 同上
-- 字符串比较用=
SELECT * FROM student WHERE pwd='123456';
SELECT * FROM student WHERE id<>1;
SELECT * FROM student WHERE id>5;
SELECT * FROM student WHERE id<=4;

SELECT * FROM student WHERE id<>1 AND birthday IS NULL;
SELECT * FROM student WHERE id<>1 OR birthday IS NULL;
SELECT * FROM student WHERE birthday IS NOT NULL;
SELECT * FROM student WHERE NOT birthday IS NULL; -- 同上
SELECT * FROM student WHERE NOT (id<>1 AND birthday IS NULL);

-- 使用BETWEEN 
SELECT * FROM student WHERE id BETWEEN 1 AND 5;
SELECT * FROM student WHERE id NOT BETWEEN 1 AND 5;

-- 顺序不能颠倒
-- 等价于WHERE id>=5 AND id<=1;
SELECT * FROM student WHERE id BETWEEN 5 AND 1; -- 空表




SELECT * FROM student WHERE pwd LIKE '15%';
SELECT * FROM student WHERE pwd LIKE '15_';

-- 转义
SELECT * FROM student WHERE pwd LIKE '_$_a%' ESCAPE '$'; -- $后面的_不作为通配符

-- IN
-- 括号中值类型必须相同或兼容
SELECT * FROM student WHERE id IN (1,2,999,9999);
SELECT * FROM student WHERE id IN (1,'2',999,9999); -- 同上
SELECT * FROM student WHERE id NOT IN (1,2,999,9999);

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
