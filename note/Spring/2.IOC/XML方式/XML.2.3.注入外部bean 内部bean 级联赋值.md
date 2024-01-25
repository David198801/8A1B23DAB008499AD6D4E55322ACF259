注入外部bean

<bean>的<property>使用ref属性指向其他bean的id，即可



创建UserDao、UserDaoImpl、UserService、UserServiceImpl

在UserServiceImpl中创建UserDao userDao属性和setter、getter，并在业务方法中调用userDao的方法

```javascript
<bean id="userDaoImpl" class="com.lzb.spring.dao.UserDaoImpl"/>
<bean id="userServiceImpl" class="com.lzb.spring.service.UserServiceImpl">
    <property name="userDao" ref="userDaoImpl"/>
</bean>
```

配置完成即可调用







注入内部bean，在<property>内嵌套一个<bean>即可

User中有一个group属性表示所属的group

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
</bean>
```

