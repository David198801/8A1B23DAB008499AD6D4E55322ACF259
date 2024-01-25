@echo off

color 5f

title 快速定位注册表

@echo 例如：HKEY_LOCAL_MACHINE\SOFTWARE\Microsoft\Windows\CurrentVersion\Run

set /p temp=请输入路径:

cmd /c reg add "HKEY_CURRENT_USER\Software\Microsoft\Windows\CurrentVersion\Applets\Regedit" /v "LastKey" /d %temp% /f&&start regedit.exe