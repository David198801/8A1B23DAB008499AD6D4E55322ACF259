@echo off

color 5f

title ���ٶ�λע���

@echo ���磺HKEY_LOCAL_MACHINE\SOFTWARE\Microsoft\Windows\CurrentVersion\Run

set /p temp=������·��:

cmd /c reg add "HKEY_CURRENT_USER\Software\Microsoft\Windows\CurrentVersion\Applets\Regedit" /v "LastKey" /d %temp% /f&&start regedit.exe