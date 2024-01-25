1.创建一个maven，webapp项目，配置tomcat上下文为/ws

2.添加依赖

```javascript
<dependency>
  <groupId>org.apache.axis2</groupId>
  <artifactId>axis2-jaxws</artifactId>
  <version>1.7.9</version>
</dependency>
<dependency>
  <groupId>org.apache.axis2</groupId>
  <artifactId>axis2-adb</artifactId>
  <version>1.7.9</version>
</dependency>
<dependency>
  <groupId>org.apache.axis2</groupId>
  <artifactId>axis2-transport-local</artifactId>
  <version>1.7.9</version>
</dependency>
```

3. web.xml

```javascript
<servlet>
    <servlet-name>AxisServlet</servlet-name>
    <servlet-class>org.apache.axis2.transport.http.AxisServlet</servlet-class>
    <load-on-startup>1</load-on-startup>
</servlet>
<servlet-mapping>
    <servlet-name>AxisServlet</servlet-name>
    <url-pattern>/services/*</url-pattern>
</servlet-mapping>
```

4.写一个服务类