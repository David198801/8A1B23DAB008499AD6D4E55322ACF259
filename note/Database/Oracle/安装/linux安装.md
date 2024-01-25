dbca，助手







Linux下无法启动oracle could not open parameter file 解决方法

方法：



$ORACLE_BASE/admin/(dbname) /pfile目录下的init.ora(.01200923383)文件复制到



$ORACLE_HOME/dbs目录下即可

'/u01/app/oracle/product/11.2.0/db_1/dbs/initzctgdb.ora'







```javascript
mount -t tmpfs shmfs -o size=13G /dev/shm
```







https://blog.csdn.net/zhongweill622/article/details/81748666

https://www.cnblogs.com/xuexin/p/12511130.html



1./lib64/libstdc++.so.5: undefined reference to `memcpy@GLIBC_2.14'

编辑/u01/app/oracle/product/11.2.0/dbhome_1/ctx/lib/ins_ctx.mk

```javascript
ctxhx: $(CTXHXOBJ)
       $(LINK_CTXHX) $(CTXHXOBJ) $(INSO_LINK)
```



```javascript
ctxhx: $(CTXHXOBJ)
       $(LINK_CTXHX) $(CTXHXOBJ) $(INSO_LINK) /usr/lib64/libstdc++.so.6
```

2.nmectlt.c:(.text+0x7f): undefined reference to `B_DestroyKeyObject'

编辑“/u01/app/oracle/product/11.2.0/dbhome_1/sysman/lib/ins_emagent.mk”文件，将

```javascript
$(MK_EMAGENT_NMECTL)
```



改为

```javascript
$(MK_EMAGENT_NMECTL) -lnnz11
```

