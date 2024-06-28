1、问题现象
服务器nginx配置SSL证书后：
```
server {
    listen       443 ssl;
    server_name  wxiaoxin.top;
    ssl on;

    ssl_certificate      /software/data/3913039_www.wxiaoxin.top.pem;
    ssl_certificate_key  /software/data/3913039_www.wxiaoxin.top.key;
     
    ssl_session_cache    shared:SSL:1m;
    ssl_session_timeout  5m;
    ssl_ciphers ECDHE-RSA-AES128-GCM-SHA256:ECDHE:ECDH:AES:HIGH:!NULL:!aNULL:!MD5:!ADH:!RC4;
     
    ssl_protocols TLSv1 TLSv1.1 TLSv1.2;
    ssl_prefer_server_ciphers  on;
     
    location / {
        proxy_pass http://wxiaoxin.top:8443;
        # root   html;
        # index  index.html index.htm;
    }
    	
    error_page   500 502 503 504  /50x.html;
        location = /50x.html {
        root   html;
    }
}
```
启动报错：

nginx: [emerg] the "ssl" parameter requires ngx_http_ssl_module in /usr/local/nginx/conf/nginx.conf:240
原因：nginx缺少http_ssl_module模块，编译安装的时候带上--with-http_ssl_module配置就行了，但是现在的情况是我的nginx已经安装过了，怎么添加模块，其实也很简单，往下看：

1、如在/usr/local/nginx下添加安装包 nginx-1.17.9.tar.gz，并解压：tar zxvf nginx-1.17.9.tar.gz

2、切换到源码包：cd /usr/local/nginx/nginx-1.17.9

3、执行：`./configure --prefix=/usr/local/nginx --with-http_stub_status_module --with-http_ssl_module`

4、编译完成后 : make (这里不要进行make install，否则就是覆盖安装)

5、备份原来nginx配置文件 cp /usr/local/nginx/sbin/nginx /usr/local/nginx/sbin/nginx.bak

6、保证nginx是关闭状态 nginx -s stop

7、复制刚编译生成的配置覆盖原有的配置：cp ./objs/nginx /usr/local/nginx/sbin/

8、进入sbin文件夹，启动nginx就可以了（./nginx）

./nginx(启动)

./nginx -s reload（重启）

./nginx -s stop（停止）
原文链接：https://blog.csdn.net/tgw2822/article/details/131571604