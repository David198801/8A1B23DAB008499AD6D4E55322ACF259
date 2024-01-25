https://www.jianshu.com/p/f1645471bac0



oracle 导出dmp文件

- 通常我都是导出固定用户对应的整个数据库，其导出语句如下：

```javascript
exp username/password@orcl file=e:\backup.dmp log=e:\backup.log full=y
```

- 如果创建数据库时实例名称不是orcl，那就在此处填写设置的实例名称。

- 导出log日志是很有用的一步，它可以告诉你在导出过程中有哪些表因为没有数据而没有被导出。

- 导出库中的某几张表

```javascript
exp username/password@orcl file=e:\backup.dmp log=e:\backup.log tables=(table1,table2,table3,···)
```

- 导出库中固定表的筛选固定条件字段的数据

```javascript
exp username/password@orcl file=e:\backup.dmp log=e:\backup.log tables=(table1) 
query=\" where column1 like &apos;%值%&apos;\"
```

