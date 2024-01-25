https://superuser.com/questions/472432/memory-usage-by-mapped-files-win7-64bit

https://www.jianshu.com/p/fbc9a67d95d6



rammap

https://docs.microsoft.com/zh-cn/sysinternals/downloads/rammap











RAMMap

下载地址

https://docs.microsoft.com/en-us/sysinternals/downloads/rammap

下载后解析到目录，然后运行，可以得到类似结果如下图：



![](//upload-images.jianshu.io/upload_images/9068163-bfc691bd0a8aa43a.png?imageMogr2/auto-orient/strip|imageView2/2/w/647/format/webp)

当MetaFile或者Mapped File 占用内存较多时，就可以设置占用内存限制。

MetaFile可以理解为系统缓存，Windows server 2008系统中，比如存在大量的文件拷贝等磁盘io操作，系统会自动将其缓存到内存中，这部分被占用的内存在任务管理器的中未体现出来的，所以用户会认为系统的内存占用异常。同时，MetaFile默认是没有限制的，所以系统会无限制占用内存。

内存映射文件（mapped file)，或称“文件映射”、“映射文件”，是一段虚内存逐字节对应于一个文件或类文件的资源，使得应用程序处理映射部分如同访问

当MetaFile消耗系统物理资源较多时，可以通过Dyncache服务来解决。具体设置入下：

下载地址： http://www.microsoft.com/en-us/download/details.aspx?id=9258

设置：

1. 解压后根据不同系统版本中对应的DynCache.exe文件复制到C:\windows\system32中。

1. 以管理员身份打开命令提示符，执行如下命令添加服务：

sc create DynCache binPath= %SystemRoot%\System32\DynCache.exe start= auto type= own DisplayName= "Dynamic Cache Service"

1. 回到DynCache文件夹，找到DynCache.reg的注册表文件导入。

1. 打开注册表，找到HKEY_LOCAL_MACHINE\SYSTEM\CurrentControlSet\Services\DynCache\Parameters

1. 右侧找到MaxSystemCacheMBytes，双击它，这里我们选择“十进制”，在数值里输入要限制最大的缓存数（单位是MB），输入800就是限制缓存最大为800MB，输入0为不限制。

1. 到服务中启动DynCache服务。





作者：阳光_8af8

链接：https://www.jianshu.com/p/fbc9a67d95d6

来源：简书

著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。