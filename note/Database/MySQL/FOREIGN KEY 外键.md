网址

https://blog.csdn.net/nakiri_arisu/article/details/79718442



http://www.cppblog.com/wolf/articles/69089.html

```javascript
-- 外键
-- 方式一，创建子表时指定
CREATE TABLE main (
	id INT,
	NAME VARCHAR(50),
	PRIMARY KEY(id)
);
CREATE TABLE sub (
	main_id INT,
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
```





外键的使用条件：

1.两个表必须是InnoDB表，MyISAM表暂时不支持外键（据说以后的版本有可能支持，但至少目前不支持）；

2.外键列必须建立了索引，MySQL 4.1.2以后的版本在建立外键时会自动创建索引，但如果在较早的版本则需要显示建立； 

3.外键关系的两个表的列必须是数据类型相似，也就是可以相互转换类型的列，比如int和tinyint可以，而int和char则不可以；

