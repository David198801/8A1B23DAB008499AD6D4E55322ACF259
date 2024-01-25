https://dev.mysql.com/doc/refman/5.7/en/select.html

```javascript
SELECT
    [ALL | DISTINCT | DISTINCTROW ]
    [HIGH_PRIORITY]
    [STRAIGHT_JOIN]
    [SQL_SMALL_RESULT] [SQL_BIG_RESULT] [SQL_BUFFER_RESULT]
    [SQL_CACHE | SQL_NO_CACHE] [SQL_CALC_FOUND_ROWS]
    select_expr [, select_expr] ...
    [into_option]
    [FROM table_references
      [PARTITION partition_list]]
    [WHERE where_condition]
    [GROUP BY {col_name | expr | position}
      [ASC | DESC], ... [WITH ROLLUP]]
    [HAVING where_condition]
    [ORDER BY {col_name | expr | position}
      [ASC | DESC], ...]
    [LIMIT {[offset,] row_count | row_count OFFSET offset}]
    [PROCEDURE procedure_name(argument_list)]
    [into_option]
    [FOR UPDATE | LOCK IN SHARE MODE]

into_option: {
    INTO OUTFILE 'file_name'
        [CHARACTER SET charset_name]
        export_options
  | INTO DUMPFILE 'file_name'
  | INTO var_name [, var_name] ...
}
```



```javascript
-- SELECT字段
SELECT `id` FROM `student`;
SELECT `id`,`pwd` FROM `student`;
-- 可重复
SELECT id,id FROM `student`;
-- 所有字段
SELECT * FROM `student`;
SELECT *,id FROM `student`; -- *要放在前面，放后面要指定表名
SELECT r.studentno,r.* FROM resultinfo AS r;


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
SELECT id FROM city LIMIT 5 OFFSET 2; -- id：3、4、5、6、7
SELECT id FROM city LIMIT 2,5; -- 同上


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

```







