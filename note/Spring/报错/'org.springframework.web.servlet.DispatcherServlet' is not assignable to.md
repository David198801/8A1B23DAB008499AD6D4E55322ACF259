'org.springframework.web.servlet.DispatcherServlet' is not assignable to 'javax.servlet.Servlet,jakarta.servlet.Servlet'



识别到的servlet版本不一致

一般是缺少tomcat依赖

tomcat9

```javascript
<dependency>
    <groupId>javax.servlet.jsp</groupId>
    <artifactId>javax.servlet.jsp-api</artifactId>
    <version>2.3.3</version>
    <scope>provided</scope>
</dependency>
<dependency>
    <groupId>javax.servlet</groupId>
    <artifactId>javax.servlet-api</artifactId>
    <version>4.0.1</version>
    <scope>provided</scope>
</dependency>
```

