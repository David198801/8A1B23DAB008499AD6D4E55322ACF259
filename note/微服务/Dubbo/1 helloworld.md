1. 注册中心 https://cn.dubbo.apache.org/zh-cn/overview/mannual/java-sdk/reference-manual/registry/zookeeper/

   zookeeper测试可以随便起一个，复制conf/zoo_sample.cfg改为zoo.cfg配置下数据路径，启动zkServer即可



2. 管理平台(可以不装)

https://github.com/apache/dubbo-admin

按说明编译、启动，编译时注意powershell带-D参数需要加引号

访问http://localhost:38080，用户名密码都是root

```shell
1.1 Compile from source
Download code: git clone https://github.com/apache/dubbo-admin.git
Change dubbo-admin-server/src/main/resources/application.properties configuration to make Admin points to the designated registries, etc.
Build
mvn clean package -Dmaven.test.skip=true
Start
mvn --projects dubbo-admin-server spring-boot:run or
cd dubbo-admin-distribution/target; java -jar dubbo-admin-${project.version}.jar
Visit http://localhost:38080, default username and password are root
Security Notice: Please remember to change the admin.check.signSecret, admin.root.user.name and admin.root.user.password value before you deploy to production environment.
```

3. 测试项目

   项目代码： [dubbo.rar](../assets/dubbo.rar) 

   项目结构：

   com.lzb.dubbo 总项目

   com.lzb.dubbo.api 公共类模块

   com.lzb.dubbo.userservice 用户服务模块，作为provider

   com.lzb.dubbo.orderservice 订单服务模块，作为consumer

   orderservice中initOrder()调用userservice的getAllAddressesOfUser()

4. 配置依赖

   ```xml
   <dependency>
           <groupId>org.apache.dubbo</groupId>
           <artifactId>dubbo</artifactId>
           <version>${dubbo.version}</version>
       </dependency>
   
       <dependency>
           <groupId>org.apache.dubbo</groupId>
           <artifactId>dubbo-dependencies-zookeeper</artifactId>
           <version>${dubbo.version}</version>
           <type>pom</type>
       </dependency>
   </dependencies>
   ```

   

5. 生产方配置

   provider.xml，新建spring配置xml，参考手册https://cn.dubbo.apache.org/zh-cn/overview/mannual/java-sdk/reference-manual/registry/zookeeper/

```
<?xml version="1.0" encoding="UTF-8"?>
<beans xmlns="http://www.springframework.org/schema/beans"
          xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance" xmlns:dubbo="http://code.alibabatech.com/schema/dubbo"
          xsi:schemaLocation="http://www.springframework.org/schema/beans http://www.springframework.org/schema/beans/spring-beans.xsd http://code.alibabatech.com/schema/dubbo http://code.alibabatech.com/schema/dubbo/dubbo.xsd">
   
       <!-- 1.指定服务名称，不能重复 -->
       <dubbo:application name="userservice-provider" />
   
       <!-- 2.指定注册中心地址 -->
       <dubbo:registry address="zookeeper://127.0.0.1:2181" />
   
       <!-- 3.指定通信协议和端口 -->
       <dubbo:protocol name="dubbo" port="20880" />
   
       <!-- 4.暴露服务 -->
       <bean id="userServiceImpl" class="com.lzb.dubbo.userservice.impl.UserServiceImpl" />
       <dubbo:service interface="com.lzb.dubbo.api.service.UserService" ref="userServiceImpl" />
</beans>
```

   写一个main充当启动类

```java
public static void main(String[] args) throws Exception {
    ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("provider.xml");
    context.start();

    System.in.read();
}
```

此时能在http://localhost:38080控制台看到UserService

6. 消费方配置

   consumer.xml

```xml
<?xml version="1.0" encoding="UTF-8"?>
<beans xmlns="http://www.springframework.org/schema/beans"
       xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance" xmlns:dubbo="http://code.alibabatech.com/schema/dubbo"
       xmlns:context="http://www.springframework.org/schema/context"
       xsi:schemaLocation="http://www.springframework.org/schema/beans http://www.springframework.org/schema/beans/spring-beans.xsd http://code.alibabatech.com/schema/dubbo http://code.alibabatech.com/schema/dubbo/dubbo.xsd http://www.springframework.org/schema/context https://www.springframework.org/schema/context/spring-context.xsd">


    <context:component-scan base-package="com.lzb" />

    <!-- 1.指定服务名称，不能重复 -->
    <dubbo:application name="orderservice-consumer" />

    <!-- 2.指定注册中心地址 -->
    <dubbo:registry address="zookeeper://127.0.0.1:2181" />

    <!-- 3.引用服务 -->
    <dubbo:reference interface="com.lzb.dubbo.api.service.UserService" />
</beans>
```

   实现类中直接自动注入即可

```java
@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    UserService userService;
    @Override
    public String initOrder(String userId) {
        //获取用户订单
        List<Address> addresses = userService.getAllAddressesOfUser("1");

        return addresses.toString();
    }
}
```

main

```java
public static void main(String[] args) {
    ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("consumer.xml");

    OrderService orderService =  context.getBean(OrderService.class);
    String s = orderService.initOrder("1");

    System.out.println(s);
}
```

此时可以看到输出，实体类必须实现Serializable，注意改完得重启provider

