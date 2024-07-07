https://zhuanlan.zhihu.com/p/411165246

https://2heng.xin/2017/09/19/pixiv/



https://github.com.cnpmjs.org/mashirozx/Pixiv-Nginx.git



1.自签证书

安装openssl；或安装msys2，自带openssl。

http://slproweb.com/products/Win32OpenSSL.html

[crt.rar](assets/crt.rar)

```javascript
#生成证书、根证书key
openssl genrsa -out my.key 2048
openssl genrsa -out rootCA.key 2048
#生成根证书
openssl req -new -x509 -key rootCA.key -out rootCA.crt -days 3650 -config config_rootCA.txt
#生成证书
openssl req -new -sha256 -key my.key -out my.csr -config config_childCA.txt
#认证
openssl ca -config config_signCA.txt -in my.csr -out my.crt
```

添加域名：删除my.csr、my.crt，编辑config_childCA，重新执行生成证书和认证

删除证书：certmgr.msc



2.安装根证书rootCA.crt到系统



3.nginx配置反代并使用证书

http://nginx.org/en/download.html

3.1复制my.pem、my.key到nginx的conf路径

3.2 nginx.conf

```javascript
    upstream github-com {
		server 140.82.114.4:443;
		server 140.82.112.3:443;
		server 140.82.112.4:443;
		server 140.82.113.3:443;
		server 140.82.113.4:443;
	}

    server {
        listen       443 ssl;
        server_name  github.com *.github.com;

        ssl_certificate      my.pem;
        ssl_certificate_key  my.key;

        location / {
            proxy_pass https://github-com;
			proxy_set_header Host $http_host;
			proxy_set_header X-Forwarded-For $proxy_add_x_forwarded_for;
			proxy_set_header X-Real_IP $remote_addr;
			proxy_set_header User-Agent $http_user_agent;
			proxy_set_header Accept-Encoding '';
			proxy_buffering off;
        }
    }
```

这样就没有SNI信息，因为SNI要用ssl_preread on开启(需要自己编译的版本)



4.hosts github.com指向127.0.0.1



其他

git windows使用系统根证书

```javascript
git config --global http.sslBackend schannel
```



```javascript
upstream pixiv-net {
		server 210.140.92.187:443;
		server 210.140.92.181:443;
	}
	
	server {
		access_log  logs/pixiv-net.access.log main;
		
		listen       443 ssl;
		server_name  pixiv.net *.pixiv.net;

		ssl_certificate      my.pem;
		ssl_certificate_key  my.key;

		location / {
			proxy_pass https://pixiv-net;
			proxy_set_header Host $http_host;
			proxy_set_header X-Forwarded-For $proxy_add_x_forwarded_for;
			proxy_set_header X-Real_IP $remote_addr;
			proxy_set_header User-Agent $http_user_agent;
			proxy_set_header Accept-Encoding '';
			proxy_buffering off;
		}
	}
```

