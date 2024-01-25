Linux rpm 命令

https://www.runoob.com/linux/linux-comm-rpm.html

Linux rpm 命令用于管理套件。

rpm（英文全拼：redhat package manager） 原本是 Red Hat Linux 发行版专门用来管理 Linux 各项套件的程序，由于它遵循 GPL 规则且功能强大方便，因而广受欢迎。逐渐受到其他发行版的采用。RPM 套件管理方式的出现，让 Linux 易于安装，升级，间接提升了 Linux 的适用度。

语法

rpm [-acdhilqRsv][-b<完成阶段><套间档>+][-e<套件挡>][-f<文件>+][-i<套件档>][-p<套件档>＋][-U<套件档>][-vv][--addsign<套件档>+][--allfiles][--allmatches][--badreloc][--buildroot<根目录>][--changelog][--checksig<套件档>+][--clean][--dbpath<数据库目录>][--dump][--excludedocs][--excludepath<排除目录>][--force][--ftpproxy<主机名称或IP地址>][--ftpport<通信端口>][--help][--httpproxy<主机名称或IP地址>][--httpport<通信端口>][--ignorearch][--ignoreos][--ignoresize][--includedocs][--initdb][justdb][--nobulid][--nodeps][--nofiles][--nogpg][--nomd5][--nopgp][--noorder][--noscripts][--notriggers][--oldpackage][--percent][--pipe<执行指令>][--prefix<目的目录>][--provides][--queryformat<档头格式>][--querytags][--rcfile<配置档>][--rebulid<套件档>][--rebuliddb][--recompile<套件档>][--relocate<原目录>=<新目录>][--replacefiles][--replacepkgs][--requires][--resign<套件档>+][--rmsource][--rmsource<文件>][--root<根目录>][--scripts][--setperms][--setugids][--short-circuit][--sign][--target=<安装平台>+][--test][--timecheck<检查秒数>][--triggeredby<套件档>][--triggers][--verify][--version][--whatprovides<功能特性>][--whatrequires<功能特性>]

参数说明：

- -a 　查询所有套件。

- -b<完成阶段><套件档>+或-t <完成阶段><套件档>+ 　设置包装套件的完成阶段，并指定套件档的文件名称。

- -c 　只列出组态配置文件，本参数需配合"-l"参数使用。

- -d 　只列出文本文件，本参数需配合"-l"参数使用。

- -e<套件档>或--erase<套件档> 　删除指定的套件。

- -f<文件>+ 　查询拥有指定文件的套件。

- -h或--hash 　套件安装时列出标记。

- -i 　显示套件的相关信息。

- -i<套件档>或--install<套件档> 　安装指定的套件档。

- -l 　显示套件的文件列表。

- -p<套件档>+ 　查询指定的RPM套件档。

- -q 　使用询问模式，当遇到任何问题时，rpm指令会先询问用户。

- -R 　显示套件的关联性信息。

- -s 　显示文件状态，本参数需配合"-l"参数使用。

- -U<套件档>或--upgrade<套件档> 升级指定的套件档。

- -v 　显示指令执行过程。

- -vv 　详细显示指令执行过程，便于排错。

- -addsign<套件档>+ 　在指定的套件里加上新的签名认证。

- --allfiles 　安装所有文件。

- --allmatches 　删除符合指定的套件所包含的文件。

- --badreloc 　发生错误时，重新配置文件。

- --buildroot<根目录> 　设置产生套件时，欲当作根目录的目录。

- --changelog 　显示套件的更改记录。

- --checksig<套件档>+ 　检验该套件的签名认证。

- --clean 　完成套件的包装后，删除包装过程中所建立的目录。

- --dbpath<数据库目录> 　设置欲存放RPM数据库的目录。

- --dump 　显示每个文件的验证信息。本参数需配合"-l"参数使用。

- --excludedocs 　安装套件时，不要安装文件。

- --excludepath<排除目录> 　忽略在指定目录里的所有文件。

- --force 　强行置换套件或文件。

- --ftpproxy<主机名称或IP地址> 　指定FTP代理服务器。

- --ftpport<通信端口> 　设置FTP服务器或代理服务器使用的通信端口。

- --help 　在线帮助。

- --httpproxy<主机名称或IP地址> 　指定HTTP代理服务器。

- --httpport<通信端口> 　设置HTTP服务器或代理服务器使用的通信端口。

- --ignorearch 　不验证套件档的结构正确性。

- --ignoreos 　不验证套件档的结构正确性。

- --ignoresize 　安装前不检查磁盘空间是否足够。

- --includedocs 　安装套件时，一并安装文件。

- --initdb 　确认有正确的数据库可以使用。

- --justdb 　更新数据库，当不变动任何文件。

- --nobulid 　不执行任何完成阶段。

- --nodeps 　不验证套件档的相互关联性。

- --nofiles 　不验证文件的属性。

- --nogpg 　略过所有GPG的签名认证。

- --nomd5 　不使用MD5编码演算确认文件的大小与正确性。

- --nopgp 　略过所有PGP的签名认证。

- --noorder 　不重新编排套件的安装顺序，以便满足其彼此间的关联性。

- --noscripts 　不执行任何安装Script文件。

- --notriggers 　不执行该套件包装内的任何Script文件。

- --oldpackage 　升级成旧版本的套件。

- --percent 　安装套件时显示完成度百分比。

- --pipe<执行指令> 　建立管道，把输出结果转为该执行指令的输入数据。

- --prefix<目的目录> 　若重新配置文件，就把文件放到指定的目录下。

- --provides 　查询该套件所提供的兼容度。

- --queryformat<档头格式> 　设置档头的表示方式。

- --querytags 　列出可用于档头格式的标签。

- --rcfile<配置文件> 　使用指定的配置文件。

- --rebulid<套件档> 　安装原始代码套件，重新产生二进制文件的套件。

- --rebuliddb 　以现有的数据库为主，重建一份数据库。

- --recompile<套件档> 　此参数的效果和指定"--rebulid"参数类似，当不产生套件档。

- --relocate<原目录>=<新目录> 　把本来会放到原目录下的文件改放到新目录。

- --replacefiles 　强行置换文件。

- --replacepkgs 　强行置换套件。

- --requires 　查询该套件所需要的兼容度。

- --resing<套件档>+ 　删除现有认证，重新产生签名认证。

- --rmsource 　完成套件的包装后，删除原始代码。

- --rmsource<文件> 　删除原始代码和指定的文件。

- --root<根目录> 　设置欲当作根目录的目录。

- --scripts 　列出安装套件的Script的变量。

- --setperms 　设置文件的权限。

- --setugids 　设置文件的拥有者和所属群组。

- --short-circuit 　直接略过指定完成阶段的步骤。

- --sign 　产生PGP或GPG的签名认证。

- --target=<安装平台>+ 　设置产生的套件的安装平台。

- --test 　仅作测试，并不真的安装套件。

- --timecheck<检查秒数> 　设置检查时间的计时秒数。

- --triggeredby<套件档> 　查询该套件的包装者。

- --triggers 　展示套件档内的包装Script。

- --verify 　此参数的效果和指定"-q"参数相同。

- --version 　显示版本信息。

- --whatprovides<功能特性> 　查询该套件对指定的功能特性所提供的兼容度。

- --whatrequires<功能特性> 　查询该套件对指定的功能特性所需要的兼容度。

实例

安装软件

# rpm -hvi dejagnu-1.4.2-10.noarch.rpm 
警告：dejagnu-1.4.2-10.noarch.rpm: V3 DSA 签名：NOKEY, key ID db42a60e
准备...           
########################################### [100%]

显示软件安装信息

# rpm -qi dejagnu-1.4.2-10.noarch.rpm

【第1次更新 教程、类似命令关联】