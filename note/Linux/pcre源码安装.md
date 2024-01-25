1）首先去官网下载压缩包文件。

其他的source网站需要墙，点击https://www.pcre.org/，我使用的ftp下载的，https://ftp.pcre.org/pub/pcre/pcre-8.13.tar.gz

```javascript
wget https://ftp.pcre.org/pub/pcre/pcre-8.13.tar.gz
```

2)解压缩

```javascript
tar -xzvf  pcre-8.13.tar.gz
```

3)进入该目录，运行configure

```javascript
cd pcre-8.13
./configure --enable-utf8  
```

4) 执行make命令

```javascript
make && make intall
```

OK !