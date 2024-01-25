DispatcherServlet创建的IOC容器会自动将全局容器设为父容器





```javascript
<context-param>
  <param-name>contextConfigLocation</param-name>
  <param-value>
    classpath:spring/spring-application.xml
  </param-value>
</context-param>
<listener>
  <listener-class>org.springframework.web.context.ContextLoaderListener</listener-class>
</listener>
```

这个配置会创建全局容器WebApplicationContext(param-value配置多行xml也在同一个容器中)

```javascript
<servlet>
  <servlet-name>DispacherServlet</servlet-name>
  <servlet-class>org.springframework.web.servlet.DispatcherServlet</servlet-class>
  <!--初始化参数，配置xml路径-->
  <init-param>
    <param-name>contextConfigLocation</param-name>
    <param-value>
      classpath:spring/spring-mvc.xml
    </param-value>
  </init-param>
  <load-on-startup>1</load-on-startup>
</servlet>
```

这个配置创建容器时会自动将WebApplicationContext设为父容器



Spring容器与SpringMVC的容器的联系与区别

https://zhuanlan.zhihu.com/p/69029697