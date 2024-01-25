

[AriaNg-1.3.7-AllInOne.rar](assets/AriaNg-1.3.7-AllInOne.rar)

```javascript
start D:/l/p/AriaNg-1.1.7/index.html
d:
cd D:\l\p\Aria2
aria2c --enable-rpc --rpc-allow-origin-all --conf-path=aria2.conf
```

aria2.conf

注意aria2.session需要自己新建一个空文件

```javascript
quiet=true
disable-ipv6=true
dir=D:\Downloads
log=aria2.log
log-level=notice
max-connection-per-server=16
split=64
continue=true
input-file=aria2.session
save-session=aria2.session
netrc-path=netrc
dht-file-path=dht.dat
dht-file-path6=dht6.dat
save-session-interval=30
pause-metadata=true
bt-tracker=udp://62.138.0.158:6969/announce,udp://185.225.17.100:1337/announce,udp://51.15.4.13:1337/announce,udp://151.80.120.112:2710/announce,udp://208.83.20.20:6969/announce,udp://51.15.76.199:6969/announce,udp://191.96.249.23:6969/announce,udp://184.105.151.164:6969/announce,udp://128.1.203.23:8080/announce,udp://5.206.28.90:6969/announce,udp://188.246.227.212:80/announce,udp://51.15.40.114:80/announce,http://51.38.184.185:6969/announce,udp://89.234.156.205:451/announce,udp://176.31.106.35:80/announce,udp://51.15.103.67:1337/announce,udp://51.15.215.89:6969/announce,udp://95.211.168.204:2710/announce,udp://91.242.163.186:6969/announce,udp://37.235.174.46:2710/announce
```





其他配置详细说明

```javascript
## 文件保存相关 ##
 
# 文件保存目录
dir=/home/naonao/Downloads
# 启用磁盘缓存, 0为禁用缓存, 需1.16以上版本, 默认:16M
disk-cache=32M
# 断点续传
continue=true
 
# 文件预分配方式, 能有效降低磁盘碎片, 默认:prealloc
# 预分配所需时间: none < falloc ? trunc < prealloc
# falloc和trunc则需要文件系统和内核支持
# NTFS建议使用falloc, EXT3/4建议trunc, MAC 下需要注释此项
file-allocation=trunc
 
## 下载连接相关 ##
 
# 最大同时下载任务数, 运行时可修改, 默认:5
#max-concurrent-downloads=100
# 同一服务器连接数, 添加时可指定, 默认:1
# 官方的aria2最高设置为16, 如果需要设置任意数值请重新编译aria2
max-connection-per-server=256
# 整体下载速度限制, 运行时可修改, 默认:0（不限制）
#max-overall-download-limit=0
# 单个任务下载速度限制, 默认:0（不限制）
#max-download-limit=0
# 整体上传速度限制, 运行时可修改, 默认:0（不限制）
#max-overall-upload-limit=0
# 单个任务上传速度限制, 默认:0（不限制）
#max-upload-limit=0
# 禁用IPv6, 默认:false
# disable-ipv6=true
 
# 最小文件分片大小, 添加时可指定, 取值范围1M -1024M, 默认:20M
# 假定size=10M, 文件为20MiB 则使用两个来源下载; 文件为15MiB 则使用一个来源下载
min-split-size=10M
# 单个任务最大线程数, 添加时可指定, 默认:5
# 建议同max-connection-per-server设置为相同值
split=256
 
## 进度保存相关 ##
 
# 从会话文件中读取下载任务
input-file=/etc/aria2/aria2.session
# 在Aria2退出时保存错误的、未完成的下载任务到会话文件
save-session=/etc/aria2/aria2.session
# 定时保存会话, 0为退出时才保存, 需1.16.1以上版本, 默认:0
save-session-interval=60
 
## RPC相关设置 ##
 
# 启用RPC, 默认:false
enable-rpc=true
# 允许所有来源, 默认:false
rpc-allow-origin-all=true
# 允许外部访问, 默认:false
rpc-listen-all=true
# RPC端口, 仅当默认端口被占用时修改
# rpc-listen-port=6800
# 设置的RPC授权令牌, v1.18.4新增功能, 取代 --rpc-user 和 --rpc-passwd 选项
rpc-secret=yourpassword
# 启动SSL
# rpc-secure=true
# 证书文件, 如果启用SSL则需要配置证书文件, 例如用https连接aria2
# rpc-certificate=
# rpc-private-key=
 
## BT/PT下载相关 ##
 
# 当下载的是一个种子(以.torrent结尾)时, 自动开始BT任务, 默认:true
follow-torrent=true
# 客户端伪装, PT需要
peer-id-prefix=-TR2770-
user-agent=Transmission/2.77
# 强制保存会话, 即使任务已经完成, 默认:false
# 较新的版本开启后会在任务完成后依然保留.aria2文件
#force-save=false
# 继续之前的BT任务时, 无需再次校验, 默认:false
bt-seed-unverified=true
# 保存磁力链接元数据为种子文件(.torrent文件), 默认:false
# bt-save-metadata=true
# 单个种子最大连接数, 默认:55 0表示不限制
bt-max-peers=0
# 最小做种时间, 单位:分
# seed-time = 60
# 分离做种任务
bt-detach-seed-only=true
```

