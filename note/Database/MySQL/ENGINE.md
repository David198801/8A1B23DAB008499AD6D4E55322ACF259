MyISAM、InnoDB 、HEAP、BOB、CSV等

| 名称 | MyISAM | InnoDB |
| - | - | - |
| 事务处理 | 不支持 | 支持 |
| 数据行锁定 | 不支持 | 支持 |
| 外键约束 | 不支持 | 支持 |
| 全文索引 | 支持 | 不支持 |
| 表空间大小 | 较小 | 较大,约2倍 |




```javascript
-- 指定引擎
CREATE TABLE IF NOT EXISTS student (
	id INT(4) PRIMARY KEY AUTO_INCREMENT COMMENT '主键，学号',
	pwd VARCHAR(20) NOT NULL DEFAULT '123456' COMMENT '密码',
	sex VARCHAR(2) NOT NULL DEFAULT '男',
	birthday DATETIME,
	address VARCHAR(100),
	email VARCHAR(50)
)ENGINE=INNODB DEFAULT CHARSET=utf8 COMMENT '学生表';

-- 修改引擎
ALTER TABLE tableName ENGINE=MYISAM;

-- 修改默认引擎
SET default_storage_engine=INNODB;
/*
或配置文件
[mysqld]
default-storage-engine = innodb
*/
```

