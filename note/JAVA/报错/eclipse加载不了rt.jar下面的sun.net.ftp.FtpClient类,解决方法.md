在做ftp，代码中引用

import sun.net.ftp.FtpClient;

报如下错误

Access restriction: The type FtpClient is not accessible due torestriction on required library D:\ProgramFiles\Java\jre6\lib\rt.jar





1.



确定 Java TM 是版本6 而不是 版本7 (因为现在从官网在线安装java时默认为TM7了，这样eclipse默认使用jre7下面的jar包，而FtpClient在jre7中被修改了，其构造函数FtpClient()被定义为private类型，FtpClientftp=new FtpClient()会报错：cannont instantiate the type FtpClient)



2.

sun.net包里的类，在eclipse里默认是不让用的。解决办法是自定义accessrules



工程上右键->工程属性->java builderpath->Libraries标签，点击JRE System Library里面的Accessrules，add sun/** 为accessible，如果该项存在，就edit。





3.



另外给大家推荐一个找jar包的网址 http://www.findjar.com非常方便，能根据你输入的类查找包含该类的jar包，