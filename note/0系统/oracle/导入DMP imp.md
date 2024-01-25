1.导入指定表

imp name/pwd@conn IGNORE=Y tables=table_name file=d:\data.dmp



注意：该命令直接在安装了oracle的机器上，打开cmd窗口运行即可；



name/pwd@conn这个替换为自己连接数据库时候的完整的连接名即可；



ignore参数含义：

ignore = n 表示创建表错误，IMPORT将会越过它执行下一个表

ignore = y 表示表不能被创建时，import也会尽力导入这个表的数据。

如果表已经存在，会先删除存在的表再导入。









2.导入DMP 不同用户名

打开dmp文件 找关键词CONNECT 后面的就是对方的用户名，执行imp userid=用户名/密码@orcl fromuser=原来的用户 touser=新的用户file=d:\xxxx.dmp命令成功导入，已执行检测过。

https://zhidao.baidu.com/question/2272298801450856348.html





如果不知道fromuser，log提示

如果不知道fromuser的话  有一个建议，就是在cmd里直接  输 imp 用户名B/密码 回车  然后把dmp文件拖到cmd里，根据提示来做，会有一步提示你原文件由用户A导出，现在要用户B导入吗这时候就可以看到用户A了就是 fromuser了

