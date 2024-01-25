服务端

1.maven依赖

```javascript
<dependencies>
    <!-- 要进行jaxws 服务开发 -->
    <dependency>
        <groupId>org.apache.cxf</groupId>
        <artifactId>cxf-rt-frontend-jaxws</artifactId>
        <version>3.0.1</version>
    </dependency>
    <!-- 内置jetty web服务器 -->
    <dependency>
        <groupId>org.apache.cxf</groupId>
        <artifactId>cxf-rt-transports-http-jetty</artifactId>
        <version>3.0.1</version>
    </dependency>
    <!-- 日志实现 -->
    <dependency>
        <groupId>org.slf4j</groupId>
        <artifactId>slf4j-log4j12</artifactId>
        <version>1.7.12</version>
    </dependency>
    <dependency>
        <groupId>junit</groupId>
        <artifactId>junit</artifactId>
        <version>4.10</version>
        <scope>test</scope>
    </dependency>
</dependencies>
```

2.服务类

```javascript
package com.lzb.cxf;

import javax.jws.WebService;

@WebService
public class WsService {

    public String sayHello(String name) {
        return "Hello," + name;
    }
}
```

3.发布

```javascript
package com.lzb.cxf;

import org.apache.cxf.interceptor.LoggingInInterceptor;
import org.apache.cxf.interceptor.LoggingOutInterceptor;
import org.apache.cxf.jaxws.JaxWsServerFactoryBean;

public class CxfTest {
    public static void main(String[] args) {
        //发布服务的工厂
        JaxWsServerFactoryBean factory = new JaxWsServerFactoryBean();
        //设置服务地址
        factory.setAddress("http://localhost:8010/ws/hello");
        //设置服务类
        factory.setServiceBean(new WsService());
        //添加日志输入、输出拦截器，观察soap请求，soap响应内容
        factory.getInInterceptors().add(new LoggingInInterceptor());
        factory.getOutFaultInterceptors().add(new LoggingOutInterceptor());
        //发布服务
        factory.create();
        System.out.println("发布服务成功，端口8010！！！！");
    }
}
```





客户端

1.生成java文件，可以只使用接口的java文件，去掉多余的注解

```javascript
wsimport  -keep http://localhost:8010/ws/hello?wsdl
```

2.调用

```javascript
package com.lzb.cxf.client;

import org.apache.cxf.jaxws.JaxWsProxyFactoryBean;
public class Client {
    public static void main(String[] args) {
        //服务接口访问地址 http://localhost:8010/ws/hello
        //创建cxf代理工厂
        JaxWsProxyFactoryBean factory = new JaxWsProxyFactoryBean();


        //设置远程访问服务端地址
        factory.setAddress("http://localhost:8010/ws/hello");
        //设置接口类型
        factory.setServiceClass(WsService.class);
        //对接口生成代理对象
        WsService helloService = factory.create(WsService.class);
        // 代理对象 class com.sun.proxy.$Proxy34 Java代理：1静态代理 2动态代理（Jdk接口代理，cglib子类代理） ￥CGLIB
        System.out.println(helloService.getClass());
        //远程访问服务端方法
        String content = helloService.sayHello("XiaoMing");
        System.out.println(content);
    }
}

```