1.linux下的相互转换

ppk转换为OpenSSH格式

安装putty工具（以ubuntu为例）

```javascript
sudo apt-get install putty-tools
```

已知PuTTY格式的ppk密钥puttykey.ppk，获取私钥id_rsa

```javascript
puttygen /path/to/puttykey.ppk -O private-openssh -o ~/.ssh/id_rsa
```

获取公钥id_rsa.pub

```javascript
puttygen /path/to/puttykey.ppk -O public-openssh -o ~/.ssh/id_rsa.pub
```



OpenSSH格式转换为ppk

安装putty工具（以ubuntu为例）



sudo apt-get install putty-tools

已知OpenSSH格式的私钥keyname，转换为keyname.ppk



puttygen keyname -o keyname.ppk





2.windows下的相互转换

puttygen，Conversions--import key，save私钥(ppk),Conversions--Export OpenSSH key



