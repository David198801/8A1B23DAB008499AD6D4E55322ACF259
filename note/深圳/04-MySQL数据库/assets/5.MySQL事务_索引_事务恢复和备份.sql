-- 事务


-- 自动提交模式，默认开启，使用事务时应该先关闭
-- 开启
SET AUTOCOMMIT=1;
-- 关闭
SET AUTOCOMMIT=0;


-- 创建数据库shop和表account
CREATE DATABASE shop;
USE shop;
CREATE TABLE account(
	id INT(11) NOT NULL AUTO_INCREMENT,
	`name` VARCHAR(32) NOT NULL,
	cash DECIMAL(9,2) NOT NULL,
	PRIMARY KEY(id)
);
-- 插入两条数据
INSERT INTO account(`name`,cash) VALUES("A","2000.00");
INSERT INTO account(`name`,cash) VALUES("B","10000.00");

-- 使用事务模拟转账
-- 关闭自动提交
SET AUTOCOMMIT=0;
-- 开始事务
START TRANSACTION;
UPDATE account SET cash=cash-500 WHERE `name`="A";
UPDATE account SET cash=cash+500 WHERE `name`="B";
-- 提交
COMMIT;
-- 回滚
-- ROLLBACK;
-- 开启自动提交
SET AUTOCOMMIT=1;


-- 索引


-- 建表时添加

-- 主键
CREATE TABLE test1 (
	id INT PRIMARY KEY
);
-- 或
CREATE TABLE test2 (
	id INT,
	PRIMARY KEY(id)
);

-- 唯一索引
CREATE TABLE test3 (
	id INT UNIQUE
);
-- 或
CREATE TABLE test4 (
	id INT,
	UNIQUE KEY test4(id)
);

-- 常规索引
CREATE TABLE test5 (
	id INT,
	INDEX(id)
);
-- 或
CREATE TABLE test6 (
	id INT,
	KEY(id)
);

-- 全文索引
CREATE TABLE test7(
	info VARCHAR(50),
	FULLTEXT(info)
)ENGINE=MYISAM;

-- 修改表添加
ALTER TABLE test ADD PRIMARY KEY(id);
ALTER TABLE test ADD UNIQUE KEY(id);
ALTER TABLE test ADD KEY(id);
ALTER TABLE test ADD FULLTEXT(info);

-- EXPLAIN
EXPLAIN SELECT * FROM studentinfo WHERE studentno=1001;


-- 备份
/*
命令行mysqldump
mysqldump -uroot -proot studb>"d:\stu.sql"
mysqldump -uroot -proot studb studentinfo subjectinfo > "d:\stu.sql"
恢复
source d:\stu2.sql
mysql -uroot -proot shop<d:\stu2.sql
*/
-- 使用SQL语句
-- 导出
SELECT * INTO OUTFILE 'out.sql' FROM account;
-- 导入
CREATE TABLE account2(
	id INT(11) NOT NULL AUTO_INCREMENT,
	`name` VARCHAR(32) NOT NULL,
	cash DECIMAL(9,2) NOT NULL,
	PRIMARY KEY(id)
);
LOAD DATA INFILE 'out.sql' INTO TABLE account2;
CREATE TABLE account3(
	id INT(11) NOT NULL AUTO_INCREMENT,
	`name` VARCHAR(32) NOT NULL,
	cash DECIMAL(9,2) NOT NULL,
	PRIMARY KEY(id)
);
LOAD DATA INFILE 'out.sql' INTO TABLE account3(id,`name`,cash);