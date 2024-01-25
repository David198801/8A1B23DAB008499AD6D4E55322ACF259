可以对属性的属性赋值，需要User类中有group属性的get方法

```javascript
<bean id="user" class="com.lzb.spring.bean.User">
    <property name="id" value="1"/>
    <property name="name" value="user1"/>
    <property name="group">
        <bean id="group1" class="com.lzb.spring.bean.Group">
            <property name="id" value="1"/>
            <property name="name" value="group1"/>
        </bean>
    </property>
    <property name="group.name" value="group2"/>
</bean>
```

