批处理 wifi热点

```javascript
cd C:\windows\system32\
@echo off
title将无线网卡虚拟成无线AP
color 0A
 
:: 以下为默认设置项
 
set SSIDNAME=Virtual_AP
set PASSWORD=Virtual_PW
:SSTART
cls
echo将无线网卡虚拟成无线AP
echo
echo==============================================
echo.
echo 1. 显示无线网络接口
echo.
echo 2. 安装并启动虚拟AP
echo.
echo 3. 启动虚拟AP
echo.
echo 4. 重启虚拟AP
echo.
echo 5. 重启无线网络(需重新设置虚拟AP)
echo.
echo 6. 关闭虚拟AP
echo.
echo 7. 关闭并卸载虚拟AP
echo.
echo Q. 退出程序
echo.
echo==============================================
ECHO.
set type=
set /P type=请选择 [1],[2],[3],[4],[5],[6],[7] 或 [Q]：
if /I "%type%"=="1" goto :showwlaninterface
if /I "%type%"=="2" goto :install
if /I "%type%"=="3" goto :start
if /I "%type%"=="4" goto :restart
if /I "%type%"=="5" goto :restartwlan
if /I "%type%"=="6" goto :stop
if /I "%type%"=="7" goto :uninstall
if /I "%type%"=="Q" goto :end
 
:showwlaninterface
netsh wlan show drivers
echo加载完成！按任意键返回！
pause>nul
goto :SSTART
 
:install
set /P SSIDNAME=请输入无线网络名称(SSID，默认为"%SSIDNAME%"):
set /P PASSWORD=请输入密码 (默认为"%PASSWORD%"):
netsh wlan set hostednetwork mode=allow ssid=%SSIDNAME% key=%PASSWORD%
netsh wlan start hostednetwork
echo设置完成！按任意键返回！
pause>nul
goto :SSTART
 
:start
netsh wlan start hostednetwork
echo设置完成！按任意键返回！
pause>nul
goto :SSTART
 
:restart
netsh wlan stop hostednetwork
netsh wlan start hostednetwork
echo设置完成！按任意键返回！
pause>nul
goto :SSTART
 
:restartwlan
netsh interface set interface 无线网络连接 disabled
netsh interface set interface 无线网络连接 enable
echo设置完成！按任意键返回！
pause>nul
goto :SSTART
 
:stop
netsh wlan stop hostednetwork
echo设置完成！按任意键返回！
pause>nul
goto :SSTART
 
:uninstall
netsh wlan stop hostednetwork
netsh wlan set hostednetwork mode=disallow
echo设置完成！按任意键返回！
pause>nul
goto :SSTART
 
:end
exit
```

火星wifi

360wifi