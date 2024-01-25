https://cxf.apache.org/docs/migration-guides.html

包含部分cxf和spring的对应关系

cxf 4.0对应Spring 6

cxf 3.5对应Spring 5.3.x

cxf 3.4对应Spring 5.2.x



1.maven

```javascript
<properties>
    <spring.version>5.2.24.RELEASE</spring.version>
    <cxf.version>3.4.10</cxf.version>
</properties>

<dependencies>
    <!-- CXF WS开发 -->
    <dependency>
        <groupId>org.apache.cxf</groupId>
        <artifactId>cxf-rt-frontend-jaxws</artifactId>
        <version>${cxf.version}</version>
    </dependency>
    <dependency>
        <groupId>org.apache.cxf</groupId>
        <artifactId>cxf-rt-transports-http</artifactId>
        <version>${cxf.version}</version>
    </dependency>
    <dependency>
        <groupId>junit</groupId>
        <artifactId>junit</artifactId>
        <version>4.12</version>
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
</dependencies>
```



2.web.xml

注意spring-cxf.xml不能在DispacherServlet中创建，否则会创建2个cxf的bus容器，应该放到全局容器中

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

spring-cxf.xml

```javascript
<?xml version="1.0" encoding="UTF-8"?>
<beans xmlns="http://www.springframework.org/schema/beans"
       xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
       xmlns:jaxws="http://cxf.apache.org/jaxws"
       xsi:schemaLocation="http://www.springframework.org/schema/beans
        http://www.springframework.org/schema/beans/spring-beans.xsd
        http://cxf.apache.org/jaxws http://cxf.apache.org/schemas/jaxws.xsd"
>
    <jaxws:server address="/hello">
        <jaxws:serviceBean>
            <bean class="com.lzb.cxf.ws.HelloServiceImpl"></bean>
        </jaxws:serviceBean>
    </jaxws:server>

</beans>
```

4.接口和实现 （也可以直接写实现类，@WebService放实现类上）

```javascript
package com.lzb.cxf.ws;

import javax.jws.WebService;

@WebService
public interface HelloService {

    public String sayHello(String name);
}
```



```javascript
package com.lzb.cxf.ws;

public class HelloServiceImpl implements HelloService{
    public String sayHello(String name) {
        return "Hello," + name;
    }
}
```

访问http://localhost:8080/ws/services/hello?wsdl





客户端

1.pom同服务端

2.生成java文件，可以只使用接口的java文件，去掉多余的注解

```javascript
wsimport  -keep http://localhost:8080/ws/services/hello?wsdl
```

3.代码

```javascript
import com.lzb.cxf.ws.HelloService;
import org.apache.cxf.jaxws.JaxWsProxyFactoryBean;

public class ClientTest {

    public static void main(String[] args) {
        //服务接口访问地址 http://localhost:8010/ws/hello
        //创建cxf代理工厂
        JaxWsProxyFactoryBean factory = new JaxWsProxyFactoryBean();


        //设置远程访问服务端地址
        factory.setAddress("http://localhost:8080/ws/services/hello");
        //设置接口类型
        factory.setServiceClass(HelloService.class);
        //对接口生成代理对象
        HelloService helloService = factory.create(HelloService.class);
        // 代理对象 class com.sun.proxy.$Proxy34 Java代理：1静态代理 2动态代理（Jdk接口代理，cglib子类代理） ￥CGLIB
        System.out.println(helloService.getClass());
        //远程访问服务端方法
        String content = helloService.sayHello("XiaoMing");
        System.out.println(content);
    }
}
```



也可以配置为spring bean

```javascript
<jaxws:client
id="helloService"
serviceClass="com.yu.service.HelloService"
address="http://localhost:8080/ws/hello"></jaxws:client>```

使用时直接注入

```javascript
public class Client {
// 注入远程访问服务端的接口的代理对象。
@Resource
private HelloService helloService;
@Test
public void remote(){
//查看接口的代理对象类型 class com.sun.proxy.$Proxy45
System.out.println(helloService.getClass());
//远程访问服务端方法 JerryWelcome to yu
System.out.println(helloService.sayHello("Jerry"));
}
}
```

