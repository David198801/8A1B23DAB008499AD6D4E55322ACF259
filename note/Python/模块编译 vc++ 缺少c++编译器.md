Python2

Microsoft Visual C++ Compiler Package for Python 2.7

VCForPython27.msi

官方下载连接已删除



Python3

Microsoft C++ Build Tools

https://visualstudio.microsoft.com/zh-hans/visual-cpp-build-tools/

打开下载器自动选择build tools,勾选【使用C++的桌面开发】，安装即可



https://cloud.tencent.com/developer/ask/220764





使用VS的vc++

https://www.dazhuanlan.com/2020/02/13/5e45028ec859a/

Python中pip安装报错找不到cl.exe的问题改如何解决。



在环境变量中Path 那一项中添加两个路径： F:Microsoft Visual Studio2017CommunityVCToolsMSVC14.10.25017binHostX86x86 F:Microsoft Visual Studio2017CommunityCommon7IDE



在环境变量中新建一个LIB 变量，并添加三个路径（记得加分号）： F:Microsoft Visual Studio2017CommunityVCToolsMSVC14.10.25017libx86; C:Program Files (x86)Windows Kits10Lib10.0.14393.0ucrtx86; C:Program Files (x86)Windows Kits8.1Libwinv6.3umx86;



在环境变量中新建一个INCLUDE 变量，并添加两个路径（记得加分号）： C:Program Files (x86)Windows Kits10Include10.0.14393.0ucrt; F:Microsoft Visual Studio2017CommunityVCToolsMSVC14.10.25017include;



在F:Microsoft Visual Studio2017CommunityVCAuxiliaryBuild这个路径中点击vcvarsall.bat 这个批处理程序。 之后打开命令行，输入cl，如果出现下图，则配置成功……







https://docs.python.org/3/install/



使用GCC

https://stackoverflow.com/questions/16737260/how-to-tell-distutils-to-use-gcc

https://cloud.tencent.com/developer/ask/174993

https://www.cnpython.com/qa/178526