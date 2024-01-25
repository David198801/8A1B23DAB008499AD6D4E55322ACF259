打开后安装  pkg install openssh

 输入   sshd   # 开启ssh 



ifconfig # 查询本地局域网ip

手机上termux查看用户名  

whoami 

输入命令 passwd   # termux设置密码



手机上termux开启的sshd服务用的是8022端口，而不是常用的22端口

 

termux设置自动开启ssh

echo "sshd" >> ~/.bashrc 