2引入properties，以配置druid为例，直接写

```javascript
<!--配置数据库连接池-->
<bean id="dataSource" class="com.alibaba.druid.pool.DruidDataSource">
    <property name="driverClassName" value="com.mysql.jdbc.Driver"/>
    <property name="url" value="jdbc:mysql://localhost:3306/book"/>
    <property name="username" value="root"/>
    <property name="password" value="root"/>
</bean>
```

引入properties文件

1.引入context命名空间

```javascript
<beans xmlns="http://www.springframework.org/schema/beans"
       xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
       xmlns:context="http://www.springframework.org/schema/context"
       xsi:schemaLocation="
       http://www.springframework.org/schema/beans
       http://www.springframework.org/schema/beans/spring-beans.xsd
       http://www.springframework.org/schema/context
       http://www.springframework.org/schema/context/spring-context.xsd"
>
```

2.使用<context:property-placeholder>引入properties文件

```javascript
<context:property-placeholder location="classpath:druid.properties"/>
```

3.value属性使用EL表达式引入(properties文件中为防止冲突key加上了prop.前缀)

```javascript
<!--配置数据库连接池-->
<context:property-placeholder location="classpath:druid.properties"/>
<bean id="dataSource" class="com.alibaba.druid.pool.DruidDataSource">
    <property name="driverClassName" value="${prop.driverClassName}"/>
    <property name="url" value="${prop.url}"/>
    <property name="username" value="${prop.username}"/>
    <property name="password" value="${prop.password}"/>
</bean>
```

