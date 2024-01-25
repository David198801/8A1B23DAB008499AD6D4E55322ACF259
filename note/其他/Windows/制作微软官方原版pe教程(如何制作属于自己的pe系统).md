制作微软官方原版pe教程(如何制作属于自己的pe系统)

什么是Windows PE？

1.1简要介绍

windowspreinstallationenvironment (windows PE )是专为windows安装而设计的最小操作系统。 可以用于启动没有操作系统的计算机、硬盘分区和格式化、复制磁盘映像、从网络共享启动windows安装程序。

微软的意图是PE只进行系统的维护，设置各种各样的限制。 PE是Windows系统的超级紧凑版，作为Wim的文件存在，可以简单理解为在内存中运行。 特别是，最有价值的，PE用系统system账户登录，这意味着超级权限。 PE是一个强大的系统维护工具，因为它可以针对安装系统、无法访问系统、修复系统、分区等问题访问和操作PE。

微软原创的PE只有“命令行”窗口。 网络上出现的各种版本都是“nxdst们”修改后的，有些PE可以作为系统使用。 这偏离了微软的意图。 虽然为了表现“技术”不主张“随意扩展PE的功能”，但是“操作界面的可视化”、“常用功能的整合”等是必要且必须的。

版本号1.2

简要介绍Windows PE的命名约定[省略windows server 2003/2008/2012 ] :

Windows PE 1.x代表Windows XP内核。

Windows PE 2.x代表Windows Vista内核。

Windows PE 3.x代表Windows 7内核。

Windows PE 4.x代表Windows 8内核。

x表示系统版本，如SP1 (带service pack2)

示例：PE 1.3表示Windows XP SP3内核中的PE。

2 Windows PE的启动过程

windows PE4. x (类似于3.x2. x )启动过程(省略1.x ) :

2.1详细流程(微软ADK/AIK手册) :

Windows PE将引导扇区加载到特定介质上。 系统将控制传递到Bootmgr.Bootmgr，从引导配置数据(BCD )中提取基本引导信息，将控制传递到Boot.wim文件中包含的Winload.exe文件，然后将Winload.exe文件中加载系统注册表配置单元和必要的启动驱动程序. Winload.exe，加载完成后，准备运行内核Ntoskrnl.exe的环境。

该环境运行Ntoskrnl.exe文件。 然后，Ntoskrnl.exe完成环境设定。 系统将控制传递给会话管理器(SMSS )。

SMSS加载注册表的其余部分，然后配置运行Win32子系统(Win32k.sys )的环境及其各种进程。 SMSS加载Winlogon进程以创建用户会话，然后启动服务、其他不需要的设备驱动程序和安全子系统) LSASS )。

Winlogon.exe基于HKEY _ local _ machine\system\setup\cmdline注册表值执行设置。 Winpeshl.exe启动%系统驱动器% \源\源。winpe SHL.exe APP到%系统根目录% \系统32\winpe SHL.ini文件中如果文件没有指定APP，Winpeshl.exe将运行cmd/k %系统根目录% \系统根目录将Wpeinit.exe文件启动到Windows PE

Wpeinit.exe退出后，将显示命令提示符窗口。 出现命令提示符窗口后，Windows PE的启动过程将终止。

2 .如果2.23360是磁盘引导，则引导文件(例如：pe.bif ) —— bootmgr —— BCD —— boot.WIM ——引导PE。

3 Windows操作系统的启动过程

除了了解WinPE的启动过程外，系统的启动过程也非常重要，将来要准备在本地系统分区上安装WinPE，更重要的是要加深系统维护和安装机的了解。

http://bbs.wuyou.net/forum.php？ mod=视图读取标识符=254880外部=页面=1

制作Win8PE，Win7PE的方法

4.1 Windows PE的来源

winpe.wim

ADK/AIK——被称为“微软官方PE”，是最纯粹的版本，可以访问CMD操作界面

winre.wim

系统安装光盘或iso\sources\install.WIM\windows \系统32 \恢复\ winre.WIM ——可以进入恢复环境

boot.wim

安装系统安装光盘或ISO\Sources\boot.wim——定制版的Windows PE时，将启动setup.exe并安装系统

重点：以上的三个文件虽然有不同的用途，但本质上都是PE，都可以为我们使用，深入加工，做出更人性化、更符合要求的Windows PE。

4.2常见的Windows PE制作路线的分析

winpe.wim

可用于创建ADK/AIK版winpe ——。 安装ADK/AIK时附带winpe.wim，可以使用DISM添加组件，但最终没有桌面环境，只有CMD操作界面。

winre.wim

如果winpe ——不足，无法用于制作Winbuilder版，可以直接从install.wim复制，获取所需的功能。

关键点：

WinRE.WIM比boot.wim分卷2多一个winpeshl.ini文件，所以Winbuilder大多利用boot.wim分卷2； 另外，install.wim的几个卷划分表示不同的Windows版本，越往后的版本越高，所以复制文件时选择最后的卷即可。

boot.wim

boot.wim的卷#1 Windows PE相当于winpe.wim； 卷#2windows设置相当于winre.wim

4.3制作windows PE的新方法

PE是超小型的Windows系统，相关文件以Wim的形式存在，例如WinPE.wim、winre.wim、boot.wim .但install.wim上有Windows系统的几乎所有功能

但是，ADK/AIK/Winbuilder/MakePE没有让我们学到真正的东西。 那个到底怎么能做到呢？ 有更快更简单的制作方法吗？ 答案是肯定的。 这个帖子就是为此而诞生的。 我教你如何用boot.wim镜像制作自己的WinPE。 让你做清楚，体验其中的乐趣。 有了这个基础，也可以使用winre.wim或winpe.wim进行尝试。 虽然操作相同，但请注意细微的差异。