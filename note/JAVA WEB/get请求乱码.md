方法一： get方式提交的参数编码，只支持iso8859-1编码。因此，如果里面有中文。在后台就需要转换编码，如下

```javascript
String bname = request.getParameter("bname");
bname = new String(bname .getBytes("iso8859-1"),"utf-8");
```

前提是你页面编码就是utf-8，如果是gbk，那上面那句代码后面就改成gbk。

But修改后我的问题仍没解决，继续。。。



方法二：在客户端使用 URL对中文参数进行编码，在服务器端需要进行解码

```javascript
java.net.URLDecoder.decode(name, "UTF-8")
```

比较麻烦！



方法三：修改tomcat的server.xml文件：



<Connector connectionTimeout="20000" port="8080" protocol="HTTP/1.1" redirectPort="8443" URIEncoding="UTF-8" useBodyEncodingForURI="true"/>



添加URIEncoding="UTF-8" useBodyEncodingForURI="true"这一句。我使用的tomcat8,之前没有添加useBodyEncodingForURI="true"，所以还是乱码，添加后问题解决！