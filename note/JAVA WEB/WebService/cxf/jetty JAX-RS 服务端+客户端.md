服务端

1.pom

```javascript
<properties>
    <maven.compiler.source>8</maven.compiler.source>
    <maven.compiler.target>8</maven.compiler.target>
    <project.build.sourceEncoding>UTF-8</project.build.sourceEncoding>
    <cxf.version>3.4.10</cxf.version>
</properties>

<dependencies>
    <!-- jaxrs 支持包 -->
    <dependency>
        <groupId>org.apache.cxf</groupId>
        <artifactId>cxf-rt-frontend-jaxrs</artifactId>
        <version>${cxf.version}</version>
    </dependency>
    <!-- 内置的jetty服务器 -->
    <dependency>
        <groupId>org.apache.cxf</groupId>
        <artifactId>cxf-rt-transports-http-jetty</artifactId>
        <version>${cxf.version}</version>
    </dependency>
    <!-- 添加wadl -->
    <dependency>
        <groupId>org.apache.cxf</groupId>
        <artifactId>cxf-rt-rs-service-description</artifactId>
        <version>${cxf.version}</version>
    </dependency>
    <dependency>
        <groupId>org.slf4j</groupId>
        <artifactId>slf4j-log4j12</artifactId>
        <version>1.7.12</version>
    </dependency>
    <!-- 客户端调用时候使用的包(WebClient工具类调用服务端) -->
    <dependency>
        <groupId>org.apache.cxf</groupId>
        <artifactId>cxf-rt-rs-client</artifactId>
        <version>${cxf.version}</version>
    </dependency>
    <!-- 基于restful风格的webservice，客户端与服务端之间可以传递json，这个就是json支持相关包 -->
    <dependency>
        <groupId>org.apache.cxf</groupId>
        <artifactId>cxf-rt-rs-extension-providers</artifactId>
        <version>${cxf.version}</version>
    </dependency>
    <!-- json转换的库 -->
    <dependency>
        <groupId>org.codehaus.jettison</groupId>
        <artifactId>jettison</artifactId>
        <version>1.3.7</version>
    </dependency>
    <dependency>
        <groupId>junit</groupId>
        <artifactId>junit</artifactId>
        <version>4.13</version>
        <scope>test</scope>
    </dependency>
</dependencies>
```

2.代码

实体类

@XmlRootElement 指定根元素，作用：客户端与服务端传递对象数据时候，序列化为xml或json的根元素的名称

```javascript
package com.lzb.rs;


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

接口

@Produces指定响应支持的MIME类型

@Consumes指定可以接受的请求MIME类型

也可以用在方法上单独指定

```javascript
package com.lzb.rs;

import org.apache.cxf.jaxrs.model.wadl.Description;
import org.apache.cxf.jaxrs.model.wadl.DocTarget;

import javax.ws.rs.*;
import java.util.List;

@Path("/userService") // 路径；访问当前服务接口时候的路径。
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
package com.lzb.rs;

import java.util.ArrayList;
import java.util.List;

public class UserServiceImpl implements UserService{
    @Override
    public void saveUser(User user) {
        System.out.println("保存用户:"+user.getName());
    }

    @Override
    public void updateUser(User user) {
        System.out.println("更新用户:"+user.getName());
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
        System.out.println("delete user by id:"+id);
    }
}
```

3.发布

服务端用junit的话无法等待子进程，用main测试

```javascript
package com.lzb.rs;

import org.apache.cxf.interceptor.LoggingInInterceptor;
import org.apache.cxf.interceptor.LoggingOutInterceptor;
import org.apache.cxf.jaxrs.JAXRSServerFactoryBean;

public class ServerTest {
    public static void main(String[] args) {
        //1.创建服务工厂
        JAXRSServerFactoryBean factory = new JAXRSServerFactoryBean();
        //2.设置服务地址、
        factory.setAddress("http://localhost:8001/rs");
        //3.实例化服务类、
        factory.setServiceBean(new UserServiceImpl());
        // 添加日志拦截器
        factory.getInInterceptors().add(new LoggingInInterceptor());
        factory.getOutInterceptors().add(new LoggingOutInterceptor());
        //4.创建服务
        factory.create();
        System.out.println("发布服务成功..8001");
    }
}
```

此时可以访问http://localhost:8001/rs/userService?_wadl

对资源做各种操作则访问http://localhost:8001/rs/userService/user

测试时可以根据Content-Type和Accept来控制xml或者json



客户端

1.pom同服务端

2.下载cxf的二进制包https://archive.apache.org/dist/cxf/3.4.10/，wadl2java用生成java

```javascript
./bin/wadl2java -p com.example.service -d /path/to/output/dir /path/to/my-service.wadl
```

报错No message body writer则手动加上@XmlRootElement(name = "User")，对应<xs:element name="User" 

3.调用

```javascript
import com.lzb.cxf.rs.client.User;
import org.apache.cxf.jaxrs.client.WebClient;
import org.junit.Test;
import javax.ws.rs.core.MediaType;
import java.util.Collection;
public class ClientTest {
    @Test
    public void save() throws Exception {
        User user = new User();
        user.setId("11");
        user.setName("aaaaa");
        user.setAge(54);
        // 基于restful风格的webservice开发的客户端调用，直接通过一个类：WebClient类完成
        WebClient
                .create("http://localhost:8001/rs/userService/user") // 地址
                .type(MediaType.APPLICATION_JSON) // 请求数据格式是json
                .post(user); // 发送请求的类型
    }
    @Test
    public void update() throws Exception {
        User user = new User();
        user.setId("11");
        user.setName("aaaaa");
        user.setAge(54);

        WebClient
                .create("http://localhost:8001/rs/userService/user") // 地址
                .type(MediaType.APPLICATION_JSON) // 请求数据格式是json
                .put(user); // 发送请求的类型
    }
    @Test
    public void delete() throws Exception {
        WebClient
                .create("http://localhost:8001/rs/userService/user/1") // 地址
                .type(MediaType.APPLICATION_JSON) // 请求数据格式是json
                .delete(); // 发送请求的类型
    }
    @Test
    public void findOne() throws Exception {
        User user =
                WebClient
                        .create("http://localhost:8001/rs/userService/user/1") // 地址
                        .accept(MediaType.APPLICATION_JSON) // 响应的数据格式
                        .get(User.class);
        System.out.println(user);
    }
    @Test
    public void findAll() throws Exception {
        Collection<? extends User> collection =
                WebClient
                        .create("http://localhost:8001/rs/userService/user")
                        .accept(MediaType.APPLICATION_JSON)
                        .getCollection(User.class);
        System.out.println(collection);
    }
}
```

