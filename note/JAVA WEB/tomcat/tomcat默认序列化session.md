http://tomcat.apache.org/tomcat-7.0-doc/config/manager.html#Persistence_Across_Restarts

tomcat默认序列化ession，可关闭

关闭：在context.xml中加入

```javascript
<Manager pathname="" />
```

强制检查是否实现Serializable

http://tomcat.apache.org/tomcat-7.0-doc/config/manager.html

在web.xml中加入

```javascript
<distributable />
```





https://stackoverflow.com/questions/26503074/is-normal-keep-value-when-tomcat-restart-after-jsessionid-was-create



设置session cookie path 为"/'会导致invalidate()失效

https://stackoverflow.com/questions/23663629/value-of-jsessionid-is-not-changed-on-invalidating-session