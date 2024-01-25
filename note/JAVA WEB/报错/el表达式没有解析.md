检查web.xml的根节点命名空间

```javascript
<?xml version="1.0" encoding="UTF-8"?>
<web-app xmlns="http://xmlns.jcp.org/xml/ns/javaee"
         xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
         xsi:schemaLocation="http://xmlns.jcp.org/xml/ns/javaee http://xmlns.jcp.org/xml/ns/javaee/web-app_4_0.xsd"
         version="4.0">
```

检查jsp或web.xml中是否设置了忽略EL表达式

```javascript
  <%@ page isELIgnored＝"false"%>  
```



```javascript
<jsp-config>
    <jsp-property-group>
        <url-pattern>*.jsp</url-pattern>
        <el-ignored>false</el-ignored>
        <page-encoding>utf-8</page-encoding>
    </jsp-property-group>
</jsp-config>
```



