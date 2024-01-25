使用Listener加载

```javascript
 <context-param>
    <param-name>contextConfigLocation</param-name>
    <param-value>classpath:spring.xml</param-value>
</context-param>
<listener>
    <listener-class>org.springframework.web.context.ContextLoaderListener</listener-class>
</listener>
```

可以加载多个，用逗号隔开

```javascript
<param-value>/WEB-INF/applicationContext.xml,/WEB-INF/action-servlet.xml,/WEB-
INF/jason-servlet.xml</param-value>
```

