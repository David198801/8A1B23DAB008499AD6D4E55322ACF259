windows设置别名 cmd 中设置常用命令的别名(alias)



1. 新建bat文件
   在某个目录下（建议在用户根目录）新建文件cmd_auto.bat , 输入自己需要的常用命令的别名。
   下面是我的cmd_auto.bat：

```dos
@echo off
doskey ls=dir /b $*
doskey act=activate tensorflow-gpu $*
doskey tb=tensorboard --logdir $*
doskey pi=pip install $*
doskey nb=jupyter notebook $*
```

> 说明：
> doskey相当于Linux中的alias，等号左边是右边的别名；
> $*表示这个命令还可能有其他参数；



2. 修改注册表，使cmd启动时自动执行该bat文件
   win+r，键入`regedit`，进入地址：`计算机\HKEY_LOCAL_MACHINE\SOFTWARE\Microsoft\Command Processor，`右边空白处右键新建->字符串值。
3. 双击编辑该值，随便起个名字（比如AutoRun），数值数据里填刚才新建的bat文件的路径（我的是C:\Users\lenovo\cmd_auto.bat）
4. 确定后重启cmd，别名就可以用了