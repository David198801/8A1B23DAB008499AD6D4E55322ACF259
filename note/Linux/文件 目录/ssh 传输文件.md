前提条件：



上传，服务器要开启写入权限；



本地和服务器都要安装有 scp 包；



 



如何传输：



1. 从服务器上下载文件；



scp username@servername:远程目录/文件名 本地目录

例：scp root@192.168.0.101:/var/www/test.txt /var/www/local_dir



(把 192.168.0.101 上的 /var/www/test.txt 文件下载到本地目录 /var/www/local_dir)



 



2. 从服务器上下载目录；



```javascript
scp -r username@servername:远程目录 本地目录
```

例：scp -r root@192.168.0.101:/var/www/test /var/www/



(把 192.168.0.101 上的 /var/www/test 目录下载到本地目录 /var/www)



 



3. 上传本地文件到目标服务器；



scp /本地目录/文件名 username@servername:远程目录

例：scp /var/www/test.txt root@192.168.0.101:/var/www/



(把本地目录 /var/www/ 下的 test.txt 文件上传到 192.168.0.101 的 /var/www/ 目录中)



 



4. 上传本地目录到目标服务器；



scp -r 本地目录 username@servername:远程目录

例：scp -r /var/www/local_dir root@192.168.0.101:/var/www/



(把本地目录 /var/www/local_dir 上传到服务器的 /var/www/ 目录)