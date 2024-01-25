序言：中文录入失败，报错：Incorrect string value: '\xCC\xEC\xB2\xC5'，如下所示：

MySQL> set names utf8;

Query OK, 0 rows affected (0.00 sec)

mysql> insert into t select 2 as a ,'天才' as b;

ERROR 1366 (HY000): Incorrect string value: '\xCC\xEC\xB2\xC5' for column 'b' at row 1

mysql>

mysql>

1，去查看数据库的字符集，是utf8，显示正常的。

mysql> show variables like '%char%';

+--------------------------+----------------------------------+

| Variable_name            | Value                            |

+--------------------------+----------------------------------+

| character_set_client    | utf8                            |

| character_set_connection | utf8                            |

| character_set_database  | utf8                            |

| character_set_filesystem | binary                          |

| character_set_results    | utf8                            |

| character_set_server    | utf8                            |

| character_set_system    | utf8                            |

| character_sets_dir      | /usr/local/mysql/share/charsets/ |

+--------------------------+----------------------------------+

8 rows in set (0.00 sec)

mysql>

2，去查看连接客户端的字符集，

vim /etc/my.cnf

#----------------- UTF-8 -----------------#

skip-character-set-client-handshake

init-connect='SET NAMES utf8'

character-set-server=utf8

#----------------- UTF-8 -----------------#

看到connect连接有utf8设置，也保证了，通过mysql命令行登录看到的是utf8编码的。

3，通过sqlyog远程工具操作，在Query窗口执行如下命令：

SET NAMES utf8;

INSERT INTO t SELECT 3  AS a ,'第二梦'  AS b;

是成功的，执行界面显示如下：

2 queries executed, 2 success, 0 errors, 0 warnings

Query: set names utf8

0 row(s) affected

Execution Time : 0 sec

Transfer Time  : 0 sec

Total Time    : 0.001 sec

--------------------------------------------------

Query: insert into t select 3 as a ,'第二梦' as b

1 row(s) affected

Execution Time : 0.002 sec

Transfer Time  : 0 sec

Total Time    : 0.002 sec

在sqlyog窗口查询t表，也正常显示中文字符，如下图所示：

但是在linux后面使用mysql窗口命令登录，还是乱码，如下所示：

mysql> SELECT * FROM t;

+---+-----------+

| a | b        |

+---+-----------+

| 1 | bb        |

| 1 | ??      |

| 2 | 骞村.    |

| 3 | 绗.?姊?|

+---+-----------+

4 rows in set (0.00 sec)

mysql>

那么以此推断，问题在哪里呢，客户端sqlyog能正常录入中文显示中文，而linux的mysql窗口不行，得去检查os层的linux操作系统的字符集设置。

4，检查mysql服务器所在的linux os的字符集

[root@data01 ~]# cat /etc/sysconfig/i18n

LANG="zh_CN.GB18030"

LANGUAGE="zh_CN.GB18030:zh_CN.GB2312:zh_CN"

SUPPORTED="zh_CN.UTF-8:zh_CN:zh:en_US.UTF-8:en_US:en"

SYSFONT="lat0-sun16"

modified by timman on 2015/03/03

看到这里面不是GB18030，不是utf8，所以需要重新设置下，从线上copy一下/etc/sysconfig/i18n 的内容，将此改成如下

[root@data01 ~]# cat /etc/sysconfig/i18n

# LANG="zh_CN.GB18030"

# LANGUAGE="zh_CN.GB18030:zh_CN.GB2312:zh_CN"

# SUPPORTED="zh_CN.UTF-8:zh_CN:zh:en_US.UTF-8:en_US:en"

# SYSFONT="lat0-sun16"

# modified by timman on 2015/03/03

LANG="zh_CN.UTF-8"

然后重启linux，重启mysql，或者export LANG="zh_CN.UTF-8";

[root@data01 ~]# service mysqld5612 restart

Shutting down MySQL......... SUCCESS!

Starting MySQL..................................................................... SUCCESS!

[root@data01 ~]#