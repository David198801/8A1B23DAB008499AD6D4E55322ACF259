换源

```javascript
apt install apt-transport-https
apt install ca-certificates
```

手工安装

```javascript
curl -O http://archive.ubuntu.com/ubuntu/pool/main/c/ca-certificates/ca-certificates_20230311ubuntu0.23.04.1_all.deb

dpkg -i ./ca-certificates_20230311ubuntu0.23.04.1_all.deb
```



清华源

22

```javascript
echo -e "deb https://mirrors.tuna.tsinghua.edu.cn/ubuntu-ports/ jammy main restricted universe multiverse\ndeb https://mirrors.tuna.tsinghua.edu.cn/ubuntu-ports/ jammy-updates main restricted universe multiverse\ndeb https://mirrors.tuna.tsinghua.edu.cn/ubuntu-ports/ jammy-backports main restricted universe multiverse\ndeb https://mirrors.tuna.tsinghua.edu.cn/ubuntu-ports/ jammy-security main restricted universe multiverse" > /etc/apt/sources.list
```

23

mantic

```javascript
echo -e "deb https://mirrors.tuna.tsinghua.edu.cn/ubuntu-ports/ mantic main restricted universe multiverse\ndeb https://mirrors.tuna.tsinghua.edu.cn/ubuntu-ports/ mantic-updates main restricted universe multiverse\ndeb https://mirrors.tuna.tsinghua.edu.cn/ubuntu-ports/ mantic-backports main restricted universe multiverse\ndeb https://mirrors.tuna.tsinghua.edu.cn/ubuntu-ports/ mantic-security main restricted universe multiverse" > /etc/apt/sources.list
```



阿里云源



echo -e "deb https://mirrors.aliyun.com/ubuntu-ports/ focal main restricted universe multiverse\ndeb https://mirrors.aliyun.com/ubuntu-ports/ focal-updates main restricted universe multiverse\ndeb https://mirrors.aliyun.com/ubuntu-ports/ focal-backports main restricted universe multiverse\ndeb https://mirrors.aliyun.com/ubuntu-ports/ focal-security main restricted universe multiverse" > /etc/apt/sources.list



更新

```javascript
apt update && apt upgrade
```



如果 udisks2无法正确配置。



Since udisks2 is not usable by proot. remove the corresponding file:



rm -rf /var/lib/dpkg/info/udisks2.postinst



And



dpkg --configure -a



Udisks2 manages the Disk which is not useable in proot and would cause permission denials

