MySQL中有很多的基本命令，show命令也是其中之一，在很多使用者中对show命令的使用还容易产生混淆，本文汇集了show命令的众多用法。

1. show tables或show tables from database_name; -- 显示当前数据库中所有表的名称。

2. show databases; -- 显示mysql中所有数据库的名称。 

3. show columns from table_name from database_name; 或show columns from database_name.table_name; -- 显示表中列名称。

4. show grants for user_name; -- 显示一个用户的权限，显示结果类似于grant 命令。

5. show index from table_name; -- 显示表的索引。

6. show status; -- 显示一些系统特定资源的信息，例如，正在运行的线程数量。

7. show variables; -- 显示系统变量的名称和值。

8. show processlist; -- 显示系统中正在运行的所有进程，也就是当前正在执行的查询。大多数用户可以查看他们自己的进程，但是如果他们拥有process权限，就可以查看所有人的进程，包括密码。

9. show table status; -- 显示当前使用或者指定的database中的每个表的信息。信息包括表类型和表的最新更新时间。

10. show privileges; -- 显示服务器所支持的不同权限。

11. show create database database_name; -- 显示create database 语句是否能够创建指定的数据库。

12. show create table table_name; -- 显示create database 语句是否能够创建指定的数据库。

13. show engines; -- 显示安装以后可用的存储引擎和默认引擎。

14. show innodb status; -- 显示innoDB存储引擎的状态。

15. show logs; -- 显示BDB存储引擎的日志。

16. show warnings; -- 显示最后一个执行的语句所产生的错误、警告和通知。

17. show errors; -- 只显示最后一个执行语句所产生的错误。

18. show [storage] engines; --显示安装后的可用存储引擎和默认引擎。



 



 



示例：



 



1 显示该数据库连接使用的字符集的情况



show variables like '%char%';



 



2 查看MySQL服务器运行的各种状态值



show global status;



 



3 连接数



01

mysql> show variables like 'max_connections';

02

+-----------------+-------+

03

| Variable_name   | Value |

04

+-----------------+-------+

05

| max_connections | 500   |

06

+-----------------+-------+

07



08

mysql> show global status like 'max_used_connections';

09

+----------------------+-------+

10

| Variable_name        | Value |

11

+----------------------+-------+

12

| Max_used_connections | 498   |

13

+----------------------+-------+

设置的最大连接数是500，而响应的连接数是498

max_used_connections / max_connections * 100% = 99.6% （理想值 ≈ 85%）



 



4 key_buffer_size

key_buffer_size是对MyISAM表性能影响最大的一个参数, 不过数据库中多为Innodb



01

mysql> show variables like 'key_buffer_size';

02

+-----------------+----------+

03

| Variable_name   | Value    |

04

+-----------------+----------+

05

| key_buffer_size | 67108864 |

06

+-----------------+----------+

07



08

mysql> show global status like 'key_read%';

09

+-------------------+----------+

10

| Variable_name     | Value    |

11

+-------------------+----------+

12

| Key_read_requests | 25629497 |

13

| Key_reads         | 66071    |

14

+-------------------+----------+

一共有25629497个索引读取请求，有66071个请求在内存中没有找到直接从硬盘读取索引，计算索引未命中缓存的概率：

key_cache_miss_rate ＝ Key_reads / Key_read_requests * 100% =0.27%

需要适当加大key_buffer_size



1

mysql> show global status like 'key_blocks_u%';

2

+-------------------+-------+

3

| Variable_name     | Value |

4

+-------------------+-------+

5

| Key_blocks_unused | 10285 |

6

| Key_blocks_used   | 47705 |

7

+-------------------+-------+

Key_blocks_unused表示未使用的缓存簇(blocks)数，Key_blocks_used表示曾经用到的最大的blocks数

Key_blocks_used / (Key_blocks_unused + Key_blocks_used) * 100% ≈ 18% （理想值 ≈ 80%）



 



 



 



max_used_connections / max_connections * 100% = 99.6% （理想值 ≈ 85%）



 



5 open table 的情况



1

mysql> show global status like 'open%tables%';

2

+---------------+-------+

3

| Variable_name | Value |

4

+---------------+-------+

5

| Open_tables   | 1024  |

6

| Opened_tables | 1465  |

7

+---------------+-------+

Open_tables 表示打开表的数量，Opened_tables表示打开过的表数量，如果Opened_tables数量过大，说明配置中 table_cache(5.1.3之后这个值叫做table_open_cache)值可能太小，我们查询一下服务器table_cache值



1

mysql> mysql> show variables like 'table_cache';

2

+---------------+-------+

3

| Variable_name | Value |

4

+---------------+-------+

5

| table_cache   | 1024  |

6

+---------------+-------+

Open_tables / Opened_tables * 100% =69% 理想值 （>= 85%）

Open_tables / table_cache * 100% = 100% 理想值 (<= 95%)



 



6 进程使用情况



1

mysql> show global status like 'Thread%';

2

+-------------------+-------+

3

| Variable_name     | Value |

4

+-------------------+-------+

5

| Threads_cached    | 31    |

6

| Threads_connected | 239   |

7

| Threads_created   | 2914  |

8

| Threads_running   | 4     |

9

+-------------------+-------+





如果我们在MySQL服务器配置文件中设置了thread_cache_size，当客户端断开之后，服务器处理此客户的线程将会缓存起来以响应 下一个客户而不是销毁（前提是缓存数未达上限）。Threads_created表示创建过的线程数，如果发现Threads_created值过大的 话，表明 MySQL服务器一直在创建线程，这也是比较耗资源，可以适当增加配置文件中thread_cache_size值，查询服务器 thread_cache_size配置：



1

mysql> show variables like 'thread_cache_size';

2

+-------------------+-------+

3

| Variable_name     | Value |

4

+-------------------+-------+

5

| thread_cache_size | 32    |

6

+-------------------+-------+

9, 查询缓存(query cache)



01

mysql> show global status like 'qcache%';

02

+-------------------------+----------+

03

| Variable_name           | Value    |

04

+-------------------------+----------+

05

| Qcache_free_blocks      | 2226     |

06

| Qcache_free_memory      | 10794944 |

07

| Qcache_hits             | 5385458  |

08

| Qcache_inserts          | 1806301  |

09

| Qcache_lowmem_prunes    | 433101   |

10

| Qcache_not_cached       | 4429464  |

11

| Qcache_queries_in_cache | 7168     |

12

| Qcache_total_blocks     | 16820    |

13

+-------------------------+----------+

Qcache_free_blocks：缓存中相邻内存块的个数。数目大说明可能有碎片。FLUSH QUERY CACHE会对缓存中的碎片进行整理，从而得到一个空闲块。

Qcache_free_memory：缓存中的空闲内存。

Qcache_hits：每次查询在缓存中命中时就增大

Qcache_inserts：每次插入一个查询时就增大。命中次数除以插入次数就是不中比率。

Qcache_lowmem_prunes：缓存出现内存不足并且必须要进行清理以便为更多查询提供空间的次数。这个数字最好长时间来看；如果这 个数字在不断增长，就表示可能碎片非常严重，或者内存很少。（上面的          free_blocks和free_memory可以告诉您属于哪种情况）

Qcache_not_cached：不适合进行缓存的查询的数量，通常是由于这些查询不是 SELECT 语句或者用了now()之类的函数。

Qcache_queries_in_cache：当前缓存的查询（和响应）的数量。

Qcache_total_blocks：缓存中块的数量。



我们再查询一下服务器关于query_cache的配置：



01

mysql> show variables like 'query_cache%';

02

+------------------------------+----------+

03

| Variable_name                | Value    |

04

+------------------------------+----------+

05

| query_cache_limit            | 33554432 |

06

| query_cache_min_res_unit     | 4096     |

07

| query_cache_size             | 33554432 |

08

| query_cache_type             | ON       |

09

| query_cache_wlock_invalidate | OFF      |

10

+------------------------------+----------+

各字段的解释：



query_cache_limit：超过此大小的查询将不缓存

query_cache_min_res_unit：缓存块的最小大小

query_cache_size：查询缓存大小

query_cache_type：缓存类型，决定缓存什么样的查询，示例中表示不缓存 select sql_no_cache 查询

query_cache_wlock_invalidate：当有其他客户端正在对MyISAM表进行写操作时，如果查询在query cache中，是否返回cache结果还是等写操作完成再读表获取结果。



query_cache_min_res_unit的配置是一柄”双刃剑”，默认是4KB，设置值大对大数据查询有好处，但如果你的查询都是小数据查询，就容易造成内存碎片和浪费。



查询缓存碎片率 = Qcache_free_blocks / Qcache_total_blocks * 100%



如果查询缓存碎片率超过20%，可以用FLUSH QUERY CACHE整理缓存碎片，或者试试减小query_cache_min_res_unit，如果你的查询都是小数据量的话。



查询缓存利用率 = (query_cache_size – Qcache_free_memory) / query_cache_size * 100%



查询缓存利用率在25%以下的话说明query_cache_size设置的过大，可适当减小；查询缓存利用率在80％以上而且Qcache_lowmem_prunes > 50的话说明query_cache_size可能有点小，要不就是碎片太多。



查询缓存命中率 = (Qcache_hits – Qcache_inserts) / Qcache_hits * 100%



示例服务器 查询缓存碎片率 ＝ 20.46％，查询缓存利用率 ＝ 62.26％，查询缓存命中率 ＝ 1.94％，命中率很差，可能写操作比较频繁吧，而且可能有些碎片。



 



7 文件打开数(open_files)



01

mysql> show global status like 'open_files';

02

+---------------+-------+

03

| Variable_name | Value |

04

+---------------+-------+

05

| Open_files    | 821   |

06

+---------------+-------+

07



08

mysql> show variables like 'open_files_limit';

09

+------------------+-------+

10

| Variable_name    | Value |

11

+------------------+-------+

12

| open_files_limit | 65535 |

13

+------------------+-------+

比较合适的设置：Open_files / open_files_limit * 100% <= 75％



正常



 



8 表锁情况



1

mysql> show global status like 'table_locks%';

2

+-----------------------+---------+

3

| Variable_name         | Value   |

4

+-----------------------+---------+

5

| Table_locks_immediate | 4257944 |

6

| Table_locks_waited    | 25182   |

7

+-----------------------+---------+

Table_locks_immediate 表示立即释放表锁数，Table_locks_waited表示需要等待的表锁数，如果 Table_locks_immediate / Table_locks_waited > 5000，最好采用InnoDB引擎，因为InnoDB是行锁而MyISAM是表锁，对于高并发写入的应用InnoDB效果会好些.



 



9 表扫描情况



01

mysql> show global status like 'handler_read%';

02

+-----------------------+-----------+

03

| Variable_name         | Value     |

04

+-----------------------+-----------+

05

| Handler_read_first    | 108763    |

06

| Handler_read_key      | 92813521  |

07

| Handler_read_next     | 486650793 |

08

| Handler_read_prev     | 688726    |

09

| Handler_read_rnd      | 9321362   |

10

| Handler_read_rnd_next | 153086384 |

11

+-----------------------+-----------+

各字段解释参见http://hi.baidu.com/thinkinginlamp/blog/item/31690cd7c4bc5cdaa144df9c.html，调出服务器完成的查询请求次数：



1

mysql> show global status like 'com_select';

2

+---------------+---------+

3

| Variable_name | Value   |

4

+---------------+---------+

5

| Com_select    | 2693147 |

6

+---------------+---------+

计算表扫描率：



表扫描率 ＝ Handler_read_rnd_next / Com_select



如果表扫描率超过4000，说明进行了太多表扫描，很有可能索引没有建好，增加read_buffer_size值会有一些好处，但最好不要超过8MB。