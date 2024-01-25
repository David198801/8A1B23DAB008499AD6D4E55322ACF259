1. 数据查询语言DQL

数据查询语言DQL基本结构是由SELECT子句，FROM子句，WHERE

子句组成的查询块：

SELECT <字段名表>

FROM <表或视图名>

WHERE <查询条件>



2 .数据操纵语言DML

数据操纵语言DML主要有三种形式：

1) 插入：INSERT

2) 更新：UPDATE

3) 删除：DELETE



3. 数据定义语言DDL

数据定义语言DDL用来创建数据库中的各种对象-----表、视图、

索引、同义词、聚簇等如：

CREATE TABLE/VIEW/INDEX/SYN/CLUSTER

| | | | |

表 视图 索引 同义词 簇

DDL操作是隐性提交的！不能rollback 



4. 数据控制语言DCL

数据控制语言DCL用来授予或回收访问数据库的某种特权，并控制

数据库操纵事务发生的时间及效果，对数据库实行监视等。如：

1) GRANT：授权。



2) ROLLBACK [WORK] TO [SAVEPOINT]：回退到某一点。

回滚---ROLLBACK

回滚命令使数据库状态回到上次最后提交的状态。其格式为：

SQL>ROLLBACK;



3) COMMIT [WORK]：提交。