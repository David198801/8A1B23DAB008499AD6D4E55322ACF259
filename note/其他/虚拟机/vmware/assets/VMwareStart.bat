netsh interface set interface "VMware Network Adapter VMnet8" enabled
net start VMAuthdService
net start VMnetDHCP
net start "VMware NAT Service"
net start VMUSBArbService
net start VMwareHostd
explorer "D:\P\VMware\VMware Workstation\vmware.exe"