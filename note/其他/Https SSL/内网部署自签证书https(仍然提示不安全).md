第一步：生成私钥
```shell
mkdir /etc/ssl/certs/aaabbb.com
cd /etc/ssl/certs/aaabbb.com
openssl genrsa -des3 -out server.key 2048
```
输入一个4位以上的密码
确认密码
第二步：生成CSR(证书签名请求)
```shell
openssl req -new -key server.key -out server.csr -subj "/C=CN/ST=JiLin/L=ChangChun/O=gsafety/OU=gsafety/CN=aaabbb.com"
```
第三步：去除私钥中的密码
在第1步创建私钥的过程中，由于必须要指定一个密码。而这个密码会带来一个副作用，那就是在每次启动Web服务器时，都会要求输入密码
这显然非常不方便。要删除私钥中的密码，操作如下：
```shell
openssl rsa -in server.key -out server.key
```
第四步：生成自签名SSL证书
days 证书有效期-天
```shell
openssl x509 -req -days 3650 -in server.csr -signkey server.key -out server.crt
```







nginx配置

```javascript
server {
        listen 443 ssl;
        server_name aaabbb.com;
        ssl_certificate /etc/ssl/certs/aaabbb.com/server.crt;
        ssl_certificate_key /etc/ssl/certs/aaabbb.com/server.key;

        ssl_session_cache    shared:SSL:1m;
        ssl_session_timeout  5m;

        ssl_ciphers  HIGH:!aNULL:!MD5;
        ssl_prefer_server_ciphers  on;

        location / {
            root   /root/aaabbb.com;
            index  index.html index.htm;
        }
    }
```

