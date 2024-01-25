手动装配

```javascript
<bean id="group" class="com.lzb.spring.bean.Group">
    <property name="id" value="1"/>
    <property name="name" value="group1"/>
</bean>

<bean id="user" class="com.lzb.spring.bean.User" scope="prototype">
    <property name="id" value="1"/>
    <property name="name" value="user1"/>
    <property name="group" ref="group"/>
</bean>
```

自动装配

使用<bean>的autowrite属性，值

byName，根据属性同名的id自动注入

byType，注入类型匹配的对象，只有一个对象匹配是才能用

```javascript
<bean id="group" class="com.lzb.spring.bean.Group">
    <property name="id" value="1"/>
    <property name="name" value="group1"/>
</bean>

<bean id="user" class="com.lzb.spring.bean.User" scope="prototype" autowire="byName">
    <property name="id" value="1"/>
    <property name="name" value="user1"/>
</bean>
```

