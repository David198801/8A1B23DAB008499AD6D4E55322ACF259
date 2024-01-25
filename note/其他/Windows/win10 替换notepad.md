win+R 输入 regedit 打开注册表

```javascript
计算机\HKEY_CLASSES_ROOT\txtfile\shell\open\command
```

进入记事本的注册表



修改默认的值,原来的值是 %SystemRoot%\system32\NOTEPAD.EXE %1 ,改为自己解压后的exe的位置(中间不要有中文和空格)

我改后是 E:\software\Notepad3\Notepad3.exe %1

在路径输入 

```javascript
计算机\HKEY_LOCAL_MACHINE\SOFTWARE\Microsoft\Windows NT\CurrentVersion\Image File Execution Options
```

设置默认值为软件路径,比如我是 E:\software\Notepad3\Notepad3.exe