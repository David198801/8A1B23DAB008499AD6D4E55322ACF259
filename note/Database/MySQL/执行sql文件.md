方法一，在 Windows 下使用 cmd 命令执行（或 Unix 或 Linux 控制台下）

【Mysql的bin目录】\mysql –u用户名 –p密码 –D数据库<【sql脚本文件路径全名】，示例：

```javascript
C:\MySQL\bin\mysql –uroot –p123456 -Dtest<C:\test.sql
```





注意：

A、如果在 sql 脚本文件中使用了 use 数据库，则 -D数据库 选项可以忽略

B、如果【Mysql的bin目录】中包含空格，则需要使用“”包含，如：“C:\Program Files\MySQL\bin\mysql” –u用户名 –p密码 –D数据库<【sql脚本文件路径全名】

C、如果 sql 没有创建数据库的语句，而且数据库管理中也没有该数据库，那么必须先用命令创建一个空的数据库。



方法二，进入 MySQL 控制台（如：MySQL 5.5 Command Line Client），使用 source 命令执行

Mysql>source 【sql脚本文件的路径全名】 或 Mysql>\. 【sql脚本文件的路径全名】，示例：

source C:\test.sql 或者 \. C:\test.sql

打开 MySQL Command Line Client，输入数据库密码进行登录，然后使用 source 命令或者 \.