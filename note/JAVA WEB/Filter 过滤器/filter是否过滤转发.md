在servlet-2.3中，Filter会过滤一切请求，包括服务器内部使用forward转发请求和<%@ includefile="/index.jsp"%>的情况。

到了servlet-2.4+中Filter默认下只拦截外部提交的请求，forward和include这些内部转发都不会被过滤，但是有时候我们需要forward的时候也用到Filter，这样就需要如下配置。

```javascript
<filter>
    <filter-name>TestFilter</filtername>
    <filter-class>anni.TestFilter</filter-class>
</filter>
<filter-mapping>
    <filter-name>TestFilter</filtername>
    <url-pattern>/*</url-pattern>
    <dispatcher>REQUEST</dispatcher>
    <dispatcher>FORWARD</dispatcher>
    <dispatcher>INCLUDE</dispatcher>
    <dispatcher>EXCEPTION</dispatcher>
</filter-mapping>
```

