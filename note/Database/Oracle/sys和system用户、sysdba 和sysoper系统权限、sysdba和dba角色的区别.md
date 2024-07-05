Oracle sys和system用户、sysdba 和sysoper系统权限、sysdba和dba角色的区别

原文链接：https://blog.csdn.net/Raitim/article/details/17539471



sys和system用户区别

1）最重要的区别，存储的数据的重要性不同

sys所有oracle的数据字典的基表和视图都存放在sys用户中，这些基表和视图对于oracle的运行是至关重要的，由数据库自己维护，任何用户都不能手动更改。sys用户拥有dba，sysdba，sysoper等角色或权限，是oracle权限最高的用户。

 

system用户用于存放次一级的内部数据，如oracle的一些特性或工具的管理信息。system用户拥有普通dba角色权限。

  

2）其次的区别，权限的不同

system用户只能用normal身份登陆em，除非你对它授予了sysdba的系统权限或者syspoer系统权限。
sys用户具有“SYSDBA”或者“SYSOPER”系统权限，登陆em也只能用这两个身份，不能用normal。

以sys用户登陆Oracle，执行select * from V_$PWFILE_USERS;可查询到具有sysdba权限的用户，如：

SQL> select * fromV_$PWFILE_USERS;
USERNAME SYSDBA SYSOPER
SYS TRUE TRUE

 

Sysdba和sysoper两个系统权限区别

normal 、sysdba、 sysoper有什么区别
normal 是普通用户
另外两个，你考察他们所具有的权限就知道了
sysdba拥有最高的系统权限，登陆后是 sys
sysoper主要用来启动、关闭数据库，sysoper 登陆后用户是 public

sysdba和sysoper属于systemprivilege，也称为administrative privilege，拥有例如数据库开启关闭之类一些系统管理级别的权限sysdba和sysoper具体的权限可以看下表：

 

系统权限

sysdba

sysoper

区别

Startup(启动数据库)

startup

Shutdown(关闭数据库)

shutdown

alter database open/mount/backup

alter database open/mount/backup

改变字符集

none

create database(创建数据库)

None不能创建数据库

drop database(删除数据库)

none

create spfile

create spfile

alter database archivelog(归档日志)

alter database archivelog

alter database recover(恢复数据库)

只能完全恢复，不能执行不完全恢复

拥有restricted session(会话限制)权限

拥有restricted session权限

可以让用户作为sys用户连接

可以进行一些基本的操作，但不能查看用户数据

登录之后用户是sys

登录之后用户是public

 

 

system如果正常登录，它其实就是一个普通的dba用户，但是如果以as sysdba登录，其结果实际上它是作为sys用户登录的，这一点类似Linux里面的sudo的感觉，从登录信息里面我们可以看出来。因此在as sysdba连接数据库后，创建的对象实际上都是生成在sys中的。其他用户也是一样，如果 as sysdba登录，也是作为sys用户登录的，看以下实验：

SQL> create user strong identified by strong;

用户已创建。

SQL> conn strong/strong@magick as sysdba;

已连接。

SQL> show user;

USER 为 "SYS"

SQL> create table test(a int);

表已创建。

SQL> select owner from dba_tables wheretable_name='test';

未选定行 //因为创建表时oracle自动转为大写，所以用小写查的时候是不存在的；

SQL> select owner from dba_tables wheretable_name='TEST';

OWNER

------------------------------

SYS

 

dba和sysdba的区别

dba、sysdba这两个系统角色有什么区别呢

在说明这一点之前我需要说一下oracle服务的创建过程

创建实例→·启动实例→·创建数据库(system表空间是必须的)

启动过程

实例启动→·装载数据库→·打开数据库

sysdba，是管理oracle实例的，它的存在不依赖于整个数据库完全启动，只要实例启动了，他就已经存在，以sysdba身份登陆，装载数据库、打开数据库。只有数据库打开了，或者说整个数据库完全启动后，dba角色才有了存在的基础！

 

默认密码：

   sys  change_on_install   （意思是安装时改变，我改成zxsz4084）
system   manager     

scott  tiger

 

在10g里面，sys用户必须以sysdba/sysoper身份登陆数据库。解决办法 sqlplus sys/zxsz4084 as sysdba
再说后者。一看你的system用户密码就是错误得，你说你在安装时候改过密码，应该就是和你的sys用户密码一样得，应该是zxsz4084，而不是默认得密码manager，你要是是对system用户进行解锁的话，系统自然要提醒你修改密码，所以密码应该不是manager。

概念：

SYS用户是Oracle中权限最高的用户，而SYSTEM是一个用于数据库管理的用户。在数据库安装完之后，应立即修改SYS,SYSTEM这两个用户的口令，以保证数据库的安全。

可以用三种方法修改口令：
sqlplus / as sysdba;
1.sql> alter user sys identified by 123456

2.sql>grantconnect to sys identified by 123456

3.sql>passwordsystem （注：此命令只适用于SYSTEM）

SYS和SYSTEM用户之间可以相互修改口令

但是请注意，将修改完口令修改成123456后，按以下几种方法登录：
sqlplus / as sysdba;
sqlplus sys/abcde as sysdba;
sqlplus sys/ as sysdba;
sqlplus sys as sysdba;
都可以登录成功，然后查看当前用户：
sql>show user
显示 user is 'SYS'。
这是为什么呢，为什么修改了口令没有效果，不用口令或者随便用什么口令都可以进入呢。
答案是：认证方法。

 

 oracle的口令认证

SYS口令认证分为操作系统认证和Oracle认证方法。

1.在操作系统认证方式下，

对于如果是Unix操作系统，只要旧以DBA组中的用户登录的操作系统，就可以以SYSDBA的身份登录数据库，不会验证SYS的口令。
对于windows操作系统，在oracle数据库安装后，会自动在操作系统中安装一个名为ORA_DBA的用户组，只要是该组中的用户，即可以SYSDBA的身份登录数据库而不会验证SYS的口令。也可以创建名为ORA_SID_DBA(SID为实例名)的用户组，属于该用户组的用户也可以具备以上特权。

 

 如何修改认证方式

接下说一说，如何修改认证方式为操作系统认证或oracle认证。(windows，unix平台有大同小异)

要将认证方式设置为操作系统认证：
1.修改参数REMOTE_LOGIN_PASSWORDFILE为NONE
2.修改SQLNET.ORA文件，此文件所在目录为：...\oracle\product\10.1.0\db_1\network\admin\sqlnet.ora。在其中添加这一行：SQLNET.AUTENTICATION_SERVICES=(NTS)
3.重新启动数据库。

要将认证方式设置为oracle认证(口令文件认证)：
1.修改参数REMOTE_LOGIN_PASSWORDFILE为EXCLUSIVE或SHARED。其中，exclusive表示仅有一个实例可以使用口令文件。shared表口令文件可以供多个实例使用。
2.修改SQLNET.ORA文件，在SQLNET.AUTENTICATION_SERVICES=(NTS)前加#号，即#SQLNET.AUTENTICATION_SERVICES=(NTS)
3.重新启动数据库。

 如果发生sys口令丢失的情况，怎么办？

 1.使用system用户进行口令更改

 2.如果存在密码文件，则删除它（一般路径在..\oracle\product\xx.x.x\db_1\database下） 然后创建密码文件--cmd下输入
orapwd file=FILEPATH\pwd<sid> password=PASSWORD entries=N
其中FILEPATH表示密码文件路径，密码文件的格式为pwd<sid>,sid是数据库实例名。

在unix下为orapwd<sid>。

