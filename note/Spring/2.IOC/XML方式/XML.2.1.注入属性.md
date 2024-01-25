2.注入属性



2.1 <bean>内创建<property>标签，相当于通过set方法

```javascript
<bean id="user" class="com.lzb.spring.bean.User">
    <property name="id" value="1" />
    <property name="name" value="root" />
</bean>
```



2.2 <bean>内创建 <constructor-arg>标签，相当于通过有参构造函数

属性使用name或index（从0开始），一般用name

```javascript
<bean id="user" class="com.lzb.spring.bean.User">
    <constructor-arg name="id" value="1"/>
    <constructor-arg name="name" value="root2"/>
</bean>
```



```javascript
<bean id="user" class="com.lzb.spring.bean.User">
    <constructor-arg index="0" value="1"/>
    <constructor-arg index="1" value="root3"/>
</bean>
```



2.3 p命名空间注入，作用：简化基于xml的注入

在xml中添加命名空间p，使用p:属性名="属性值"注入属性

```javascript
<?xml version="1.0" encoding="UTF-8"?>
<beans xmlns="http://www.springframework.org/schema/beans"
       xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
       xsi:schemaLocation="http://www.springframework.org/schema/beans http://www.springframework.org/schema/beans/spring-beans.xsd"
       
       xmlns:p="http://www.springframework.org/schema/p">
       
<bean id="user" class="com.lzb.spring.bean.User" p:id="3" p:name="root5"/>

</beans>
```



