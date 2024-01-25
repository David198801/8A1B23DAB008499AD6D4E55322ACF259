-- 外键
-- 方式一，创建子表时指定
CREATE TABLE main (
	id INT AUTO_INCREMENT,
	NAME VARCHAR(50),
	PRIMARY KEY(id)
);
CREATE TABLE sub (
	main_id INT AUTO_INCREMENT,
	score INT,
	CONSTRAINT FK_main_id FOREIGN KEY(main_id) REFERENCES main(id)
);
-- 方式二，创建后修改
CREATE TABLE main2 (
	id INT,
	NAME VARCHAR(50),
	PRIMARY KEY(id)
);
CREATE TABLE sub2 (
	main_id INT,
	score INT
);
ALTER TABLE sub2 ADD CONSTRAINT FK_main2_id FOREIGN KEY(main_id) REFERENCES main2(id);

-- 删除外键
-- 删除索引
ALTER TABLE sub DROP FOREIGN KEY FK_main_id;
ALTER TABLE sub DROP INDEX FK_main_id;



-- 添加数据
INSERT INTO `subject` VALUES(51,'数学',100,13);

-- 修改数据
UPDATE `subject` SET `ClassHour`=110 WHERE `SubjectNo`=51;

-- 删除数据
DELETE FROM `subject` WHERE `SubjectNo`==5 OR `SubjectNo`=98;

-- 清空表
TRUNCATE `subject`;
-- 或
TRUNCATE TABLE `subject`;



-- SELECT字段
SELECT `id` FROM `student`;
SELECT `id`,`pwd` FROM `student`;
-- 可重复
SELECT id,id FROM `student`;
-- 所有字段
SELECT * FROM `student`;


-- SELECT常量
SELECT 100;
SELECT 'abc';

-- SELECT表达式
SELECT 100%98;

-- SELECT函数
SELECT VERSION();

-- 起别名
-- 使用AS
SELECT `id` AS '编号' FROM `student`;
-- 使用空格
SELECT 100 '数值';


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

SELECT * FROM student WHERE id BETWEEN 1 AND 5;
SELECT * FROM student WHERE id NOT BETWEEN 1 AND 5;
-- 顺序不能颠倒
-- 等价于WHERE id>=5 AND id<=1;
SELECT * FROM student WHERE id BETWEEN 5 AND 1; -- 空表

-- 模糊条件
/*
LIKE使用通配符
% 任意字符，类似*
_ 单个字符，类似?
*/

SELECT * FROM student WHERE pwd LIKE '15%';
SELECT * FROM student WHERE pwd LIKE '15_';

-- 转义
SELECT * FROM student WHERE pwd LIKE '_$_a%' ESCAPE '$'; -- $后面的_不作为通配符

-- IN
-- 括号中值类型必须相同或兼容
SELECT * FROM student WHERE id IN (1,2,999,9999);
SELECT * FROM student WHERE id IN (1,'2',999,9999); -- 同上

-- 安全等于，既可以判断数值，也可以判断NULL
SELECT * FROM student WHERE id <=> '1';
SELECT * FROM student WHERE birthday <=> NULL;


-- 默认升序 ASC
SELECT * FROM student ORDER BY id;
SELECT * FROM student ORDER BY id ASC; -- 同上
-- 降序 DESC
SELECT * FROM student ORDER BY id DESC;

-- 按别名排序
SELECT LENGTH(pwd) AS 密码长度,pwd,id FROM student ORDER BY 密码长度 DESC;

-- 按多个字段排序
SELECT LENGTH(pwd) AS 密码长度,pwd,id FROM student ORDER BY 密码长度 DESC,id ASC;


