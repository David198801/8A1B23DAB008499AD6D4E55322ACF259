windows无法启动安装过程

windows无法完成安装 若要在此计算机上安装.. 

原来是win10考虑esp信息冲突，格式化esp再装一遍

原来是旧系统检查是否为mbr分区，需要转为guid





其他情况尝试

按下键盘快捷键Shift+F10这时候，就会弹出命令行窗口。

在弹出的黑色命令行窗口，输入 regedit 并按下回车键，打开注册表。找到HKLocal machine/SYSTEM/SETUP/STATUS/ChildCompletion。把setup.exe的值从1改到3，然后关掉注册表和命令窗，点错误窗口的确定，重启就好了。

或

调出命令行窗口（shift+F10）不同的电脑命令方式可能不一样，我的电脑是

（shift+Fn+F10）

进入oobe目录，输入命令“cd oobe”
在这个目录下输入msoobe.exe，然后回车
接下来，将那个错误页面叉掉，或者不用管，一步一步正常安装即可，安装好之后除了多了一个用户没有其他影响，将这个用户删掉即可。