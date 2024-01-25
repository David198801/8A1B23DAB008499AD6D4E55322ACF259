类似SpringMVC+CXF，区别在于spring-cxf的xml中手动创建bus，用SmartLifecycle在容器初始化完成后执行，动态发布



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

    <bean class="com.lzb.cxf.spring.CustomInit" />

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
    <bean id="cxf" class="org.apache.cxf.bus.spring.SpringBus">
    </bean>
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

4.代码

动态发布

```javascript
package com.lzb.cxf.spring;

import org.apache.cxf.bus.spring.SpringBus;
import org.apache.cxf.jaxws.JaxWsServerFactoryBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.SmartLifecycle;

import javax.jws.WebService;

public class CustomInit implements SmartLifecycle {

    @Autowired
    private SpringBus bus;

    @Autowired
    private ApplicationContext applicationContext;

    /**
     * 容器启动后调用
     */
    @Override
    public void start() {
        // 启动逻辑
        isRunning = true;

        // 在容器初始化完成后执行的操作
        System.out.println("容器初始化完成，执行操作...");

        publishWebService();
    }

    private void publishWebService() {
        String[] beanNames = applicationContext.getBeanNamesForAnnotation(WebService.class);

        for (String beanName : beanNames) {
            Object bean = applicationContext.getBean(beanName);
            JaxWsServerFactoryBean factory = new JaxWsServerFactoryBean();
            factory.setServiceClass(bean.getClass());
            factory.setAddress("/"+beanName);
            factory.setServiceBean(bean);
            factory.setBus(bus);
            factory.create();
        }


    }

    private boolean isRunning = false;

    @Override
    public boolean isAutoStartup() {
        return true;
    }

    /**
     * 容器停止时调用
     */
    @Override
    public void stop(Runnable callback) {
        // 停止逻辑
        isRunning = false;
        callback.run();
    }

    @Override
    public void stop() {
        // 停止逻辑
        isRunning = false;
    }

    @Override
    public boolean isRunning() {
        return isRunning;
    }

    /**
     * 阶段值   越小越靠前执行start()方法，越靠后执行stop()方法
     *
     * @return
     */
    @Override
    public int getPhase() {
        return 0; // 返回一个非负整数表示生命周期阶段
    }

}
```

实体类

```javascript
package com.lzb.cxf.auto.pojo;

import java.math.BigDecimal;

public class Item {
    public Item(BigDecimal price, String name) {
        this.price = price;
        this.name = name;
    }

    private BigDecimal price;
    private String name;

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
```



```javascript
package com.lzb.cxf.auto.pojo;

public class User {
    public User(String name, Integer age) {
        this.name = name;
        this.age = age;
    }

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
}
```

接口

```javascript
package com.lzb.cxf.auto.service;

import javax.jws.WebService;
import java.math.BigDecimal;

@WebService
public interface ItemService {

    public BigDecimal getPriceByName(String name);
}
```



```javascript
package com.lzb.cxf.auto.service;

import com.lzb.cxf.auto.pojo.User;

import javax.jws.WebService;
import java.util.List;

@WebService
public interface UserService {

    public User getUserByName(String name);

    public List<User> getAllUsers();
}
```

实现类

```javascript
package com.lzb.cxf.auto.service.impl;

import com.lzb.cxf.auto.service.ItemService;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Component("itemService")
public class ItemServiceImpl implements ItemService {
    @Override
    public BigDecimal getPriceByName(String name) {
        return new BigDecimal("8848.88");
    }
}
```



```javascript
package com.lzb.cxf.auto.service.impl;

import com.lzb.cxf.auto.pojo.User;
import com.lzb.cxf.auto.service.UserService;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;

@Component("userService")
public class UserServiceImpl implements UserService {
    @Override
    public User getUserByName(String name) {
        return new User("XiaoMing", 18);
    }

    @Override
    public List<User> getAllUsers() {
        return Arrays.asList(
                new User("XiaoMing", 18),
                new User("LiHua", 19),
                new User("ZhangSan", 18)
        );
    }
}
```



访问http://localhost:8080/auto/services