这个示例中使用jackson作为json序列化的库，不会识别@XmlRootElement，如果使用jettison的话，需要参照jetty JAX-RS的内容。

根据风格需要看是否使用jackson，需要动态判断的话自己实现Serializer。



使用jackson处理的话需要：

  1. 服务端：在启动类上声明一个bean, 注入JacksonJaxbJsonProvider

  2. 客户端：在WebClient调用create()方法时，指定转json的provider



客户端

1.pom

```javascript
<properties>
    <spring.version>5.2.24.RELEASE</spring.version>
    <cxf.version>3.4.10</cxf.version>
</properties>

<dependencies>
    <!-- CXF WS开发 -->
    <dependency>
        <groupId>org.apache.cxf</groupId>
        <artifactId>cxf-rt-frontend-jaxrs</artifactId>
        <version>${cxf.version}</version>
    </dependency>
    <dependency>
        <groupId>org.apache.cxf</groupId>
        <artifactId>cxf-rt-transports-http</artifactId>
        <version>${cxf.version}</version>
    </dependency>
    <!-- 添加wadl -->
    <dependency>
        <groupId>org.apache.cxf</groupId>
        <artifactId>cxf-rt-rs-service-description</artifactId>
        <version>${cxf.version}</version>
    </dependency>
    <!-- 客户端调用时候使用的包(WebClient工具类调用服务端) -->
    <dependency>
        <groupId>org.apache.cxf</groupId>
        <artifactId>cxf-rt-rs-client</artifactId>
        <version>${cxf.version}</version>
    </dependency>
    <!-- 基于restful风格的webservice，客户端与服务端之间可以传递json，这个就是json支持相关包 -->
    <dependency>
        <groupId>com.fasterxml.jackson.core</groupId>
        <artifactId>jackson-databind</artifactId>
        <version>2.15.1</version>
    </dependency>
    <dependency>
        <groupId>com.fasterxml.jackson.jaxrs</groupId>
        <artifactId>jackson-jaxrs-json-provider</artifactId>
        <version>2.15.1</version>
    </dependency>
    <dependency>
        <groupId>org.slf4j</groupId>
        <artifactId>slf4j-log4j12</artifactId>
        <version>2.0.5</version>
    </dependency>
    <dependency>
        <groupId>junit</groupId>
        <artifactId>junit</artifactId>
        <version>4.13.2</version>
    </dependency>
    <dependency>
        <groupId>org.springframework</groupId>
        <artifactId>spring-web</artifactId>
        <version>${spring.version}</version>
    </dependency>
    <dependency>
        <groupId>org.springframework</groupId>
        <artifactId>spring-webmvc</artifactId>
        <version>${spring.version}</version>
    </dependency>

    <dependency>
        <groupId>org.springframework</groupId>
        <artifactId>spring-orm</artifactId>
        <version>${spring.version}</version>
    </dependency>
    <dependency>
        <groupId>org.springframework</groupId>
        <artifactId>spring-tx</artifactId>
        <version>${spring.version}</version>
    </dependency>

    <dependency>
        <groupId>org.springframework</groupId>
        <artifactId>spring-jms</artifactId>
        <version>${spring.version}</version>
    </dependency>
    <dependency>
        <groupId>org.springframework</groupId>
        <artifactId>spring-context-support</artifactId>
        <version>${spring.version}</version>
    </dependency>
    <dependency>
        <groupId>org.springframework</groupId>
        <artifactId>spring-websocket</artifactId>
        <version>${spring.version}</version>
    </dependency>
    <dependency>
        <groupId>org.springframework</groupId>
        <artifactId>spring-instrument</artifactId>
        <version>${spring.version}</version>
    </dependency>

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
    <dependency>
        <groupId>javax.el</groupId>
        <artifactId>javax.el-api</artifactId>
        <version>3.0.1-b06</version>
        <scope>provided</scope>
    </dependency>
</dependencies>
```

2.web.xml

```javascript
<web-app xmlns="http://xmlns.jcp.org/xml/ns/javaee"
         xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
         xsi:schemaLocation="http://xmlns.jcp.org/xml/ns/javaee http://xmlns.jcp.org/xml/ns/javaee/web-app_4_0.xsd"
         version="4.0">
    <display-name>spring-cxf-rs-test</display-name>

    <servlet>
        <servlet-name>DispacherServlet</servlet-name>
        <servlet-class>org.springframework.web.servlet.DispatcherServlet</servlet-class>
        <!--初始化参数，配置xml路径-->
        <init-param>
            <param-name>contextConfigLocation</param-name>
            <param-value>classpath:spring/spring-mvc.xml</param-value>
        </init-param>
        <load-on-startup>1</load-on-startup>
    </servlet>

    <!-- cxfservlet配置 -->
    <servlet>
        <servlet-name>cxfServlet</servlet-name>
        <servlet-class>org.apache.cxf.transport.servlet.CXFServlet</servlet-class>
        <load-on-startup>1</load-on-startup>
    </servlet>


    <servlet-mapping>
        <servlet-name>cxfServlet</servlet-name>
        <url-pattern>/services/*</url-pattern>
    </servlet-mapping>
    <servlet-mapping>
        <servlet-name>DispacherServlet</servlet-name>
        <url-pattern>/</url-pattern>
    </servlet-mapping>

    <!--初始化参数，配置xml路径-->
    <context-param>
        <param-name>contextConfigLocation</param-name>
        <param-value>
            classpath:spring/spring-application.xml
            classpath:spring/spring-cxf.xml
        </param-value>
    </context-param>
    <listener>
        <listener-class>org.springframework.web.context.ContextLoaderListener</listener-class>
    </listener>

</web-app>
```

3.spring

spring-application.xml

```javascript
<?xml version="1.0" encoding="UTF-8"?>
<beans xmlns="http://www.springframework.org/schema/beans"
       xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
       xmlns:context="http://www.springframework.org/schema/context"
       xsi:schemaLocation="
       http://www.springframework.org/schema/beans
       http://www.springframework.org/schema/beans/spring-beans.xsd
       http://www.springframework.org/schema/context
       http://www.springframework.org/schema/context/spring-context.xsd"
>

    <!--组件扫描-->
    <context:component-scan base-package="com.lzb"/>

</beans>
```

spring-cxf.xml

```javascript
<?xml version="1.0" encoding="UTF-8"?>
<beans xmlns="http://www.springframework.org/schema/beans"
       xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
       xmlns:cxf="http://cxf.apache.org/core"
       xmlns:jaxrs="http://cxf.apache.org/jaxrs"
       xsi:schemaLocation="http://www.springframework.org/schema/beans
        http://www.springframework.org/schema/beans/spring-beans.xsd
        http://cxf.apache.org/core
        http://cxf.apache.org/schemas/core.xsd
        http://cxf.apache.org/jaxrs
        http://cxf.apache.org/schemas/jaxrs.xsd"
>
    <bean id="jacksonObjectMapper" class="com.fasterxml.jackson.databind.ObjectMapper">
    </bean>

    <bean id="jsonProvider" class="com.fasterxml.jackson.jaxrs.json.JacksonJsonProvider">
        <property name="mapper" ref="jacksonObjectMapper"/>
    </bean>


    <!--
        Spring整合ApacheCXF，发布jaxws服务：
        1. 服务地址
        2. 服务bean
        完整服务地址：
        http://localhost:8080/ws/services/userService
    -->
    <jaxrs:server address="/userService">
        <jaxrs:serviceBeans>
            <bean class="com.lzb.cxf.rs.UserServiceImpl"></bean>
        </jaxrs:serviceBeans>
        <jaxrs:inInterceptors>
            <bean class="org.apache.cxf.interceptor.LoggingInInterceptor"/>
        </jaxrs:inInterceptors>
        <jaxrs:outInterceptors>
            <bean class="org.apache.cxf.interceptor.LoggingOutInterceptor"/>
        </jaxrs:outInterceptors>
        <jaxrs:providers>
            <ref bean="jsonProvider" />
        </jaxrs:providers>
    </jaxrs:server>
</beans>


```

spring-mvc.xml

```javascript
<?xml version="1.0" encoding="UTF-8"?>
<beans xmlns="http://www.springframework.org/schema/beans"
       xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
       xmlns:mvc="http://www.springframework.org/schema/mvc"
       xsi:schemaLocation="http://www.springframework.org/schema/beans
       http://www.springframework.org/schema/beans/spring-beans.xsd
       http://www.springframework.org/schema/mvc
       https://www.springframework.org/schema/mvc/spring-mvc.xsd"
>

    <!--配置视图解析器，把handler方法返回值解析为实际的物理视图-->
    <bean class="org.springframework.web.servlet.view.InternalResourceViewResolver" >
        <property name="prefix" value="/WEB-INF/views/"/>
        <property name="suffix" value=".jsp"/>
    </bean>

    <mvc:annotation-driven/>
    <mvc:default-servlet-handler/>


</beans>
```

3.代码

实体类

```javascript
package com.lzb.cxf.rs;


import javax.xml.bind.annotation.XmlRootElement;

/**
 * @XmlRootElement 指定根元素，作用：客户端与服务端传递对象数据时候，序列化为xml或json的根元素的名称
 */

@XmlRootElement(name = "User")
public class User {

    private String id;


    private String name;
    private Integer age;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}


```

接口，这里路径由spring-cxf.xml配置了，不要重复配置

```javascript
package com.lzb.cxf.rs;

import org.apache.cxf.jaxrs.model.wadl.Description;

import javax.ws.rs.*;
import java.util.List;

@Produces("*/*")
@Consumes({"application/xml", "application/json"})
@Description(value = "展示资源详情")
public interface UserService {
    @POST
    @Path("/user")
        // 路径； 访问当前服务接口的方法路径
    void saveUser(User user);

    @PUT
    @Path("/user")
    void updateUser(User user);

    @GET
    @Path("/user")
    List<User> findAllUsers();

    @GET
    @Path("/user/{id}")
    User finUserById(@PathParam("id") Integer id);

    @DELETE
    @Path("/user/{id}")
    void deleteUser(@PathParam("id") Integer id);
}

```

实现类

```javascript
package com.lzb.cxf.rs;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;

public class UserServiceImpl implements UserService {

    private static final Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);
    @Override
    public void saveUser(User user) {
        logger.info("保存用户:"+user.getName());
    }

    @Override
    public void updateUser(User user) {
        logger.info("更新用户:"+user.getName());
    }

    @Override
    public List<User> findAllUsers() {
        List<User> users = new ArrayList<>();
        User user = new User();
        user.setId("11");
        user.setAge(18);
        user.setName("Ace");
        users.add(user);
        user = new User();
        user.setId("22");
        user.setAge(19);
        user.setName("Bob");
        users.add(user);
        return users;
    }

    @Override
    public User finUserById(Integer id) {
        User user = new User();
        user.setAge(18);
        user.setName("Ace");
        return user;
    }

    @Override
    public void deleteUser(Integer id) {
        logger.info("delete user by id:"+id);
    }
}
```

日志

```javascript
log4j.rootLogger=INFO, console

log4j.appender.console=org.apache.log4j.ConsoleAppender
log4j.appender.console.layout=org.apache.log4j.PatternLayout
log4j.appender.console.layout.ConversionPattern=%-5p %d{yyyy-MM-dd HH:mm:ss} %c{1} - %m%n
```

访问http://localhost:8080/rs/services/userService?_wadl





客户端

1.pom同服务端

2.下载cxf的二进制包https://archive.apache.org/dist/cxf/3.4.10/，wadl2java用生成java

```javascript
./bin/wadl2java -p com.example.service -d /path/to/output/dir /path/to/my-service.wadl
```

报错No message body writer则手动加上@XmlRootElement(name = "User")，对应<xs:element name="User" 

3.调用

```javascript
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.jaxrs.json.JacksonJsonProvider;
import com.lzb.cxf.rs.client.User;
import org.apache.cxf.jaxrs.client.WebClient;
import org.junit.Test;

import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class ClientTest {


    private List<JacksonJsonProvider> providers;
    {
        ObjectMapper objectMapper = new ObjectMapper();
        JacksonJsonProvider jacksonJsonProvider = new JacksonJsonProvider();
        jacksonJsonProvider.setMapper(objectMapper);
        providers = new ArrayList<>();
        providers.add(jacksonJsonProvider);
    }
    @Test
    public void save() throws Exception {
        User user = new User();
        user.setId("11");
        user.setName("aaaaa");
        user.setAge(54);
        // 基于restful风格的webservice开发的客户端调用，直接通过一个类：WebClient类完成
        WebClient client =WebClient
                .create("http://localhost:8080/rs/services/userService/user",providers) // 地址
                .type(MediaType.APPLICATION_JSON); // 请求数据格式是json

        // 发送DELETE请求并获取Exchange对象
        Response response = client.post(user); // 发送请求的类型

        System.out.println(response.getStatus());
    }
    @Test
    public void update() throws Exception {
        User user = new User();
        user.setId("11");
        user.setName("aaaaa");
        user.setAge(54);

        WebClient client = WebClient
                .create("http://localhost:8080/rs/services/userService/user",providers) // 地址
                .type(MediaType.APPLICATION_JSON); // 请求数据格式是json


        // 发送DELETE请求并获取Exchange对象
        Response response = client.put(user); // 发送请求的类型

        System.out.println(response.getStatus());
    }
    @Test
    public void delete() throws Exception {
        WebClient client = WebClient
                .create("http://localhost:8080/rs/services/userService/user/1", providers) // 地址
                .type(MediaType.APPLICATION_JSON);// 请求数据格式是json

        // 发送DELETE请求并获取Exchange对象
        Response response = client.delete();

        System.out.println(response.getStatus());
    }
    @Test
    public void findOne() throws Exception {
        User user =
                WebClient
                        .create("http://localhost:8080/rs/services/userService/user/1",providers) // 地址
                        .accept(MediaType.APPLICATION_JSON) // 响应的数据格式
                        .get(User.class);
        System.out.println(user);
    }
    @Test
    public void findAll() throws Exception {
        Collection<? extends User> collection =
                WebClient
                        .create("http://localhost:8080/rs/services/userService/user",providers)
                        .accept(MediaType.APPLICATION_JSON)
                        .getCollection(User.class);
        System.out.println(collection);
    }
}
```

