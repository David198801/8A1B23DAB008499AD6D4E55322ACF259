

```javascript
netsh interface set interface "VMware Network Adapter VMnet8" enabled
net start VMAuthdService
net start VMnetDHCP
net start "VMware NAT Service"
net start VMUSBArbService
net start VMwareHostd
explorer "D:\P\VMware\VMware Workstation\vmware.exe"
```



```javascript
taskkill /t /im vmware-tray.exe
net stop /y VMAuthdService
net stop /y VMnetDHCP
net stop /y "VMware NAT Service"
net stop /y VMUSBArbService
net stop /y VMwareHostd
netsh interface set interface "VMware Network Adapter VMnet8" disabled
```

   

[VMwareStop.bat](assets/VMwareStop.bat)



[VMwareStart.bat](assets/VMwareStart.bat)



