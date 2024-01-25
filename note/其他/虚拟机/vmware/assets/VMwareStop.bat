taskkill /t /im vmware-tray.exe
net stop /y VMAuthdService
net stop /y VMnetDHCP
net stop /y "VMware NAT Service"
net stop /y VMUSBArbService
net stop /y VMwareHostd
netsh interface set interface "VMware Network Adapter VMnet8" disabled