Spring中Bean默认为单例，一个id对应一个对象

也可以设置为多实例，通过同一个id获取多次，获得不同的对象

多实例：使用<bean>的scope属性，值

singleton：单例，默认值，读取配置文件时就会创建

prototype：多实例，调用getBean()时才会创建

request：放到request中

session：放到session中



```javascript
<bean id="user" class="com.lzb.spring.bean.User" scope="prototype" />
```



```javascript
@Test
public void test5(){
    ApplicationContext context = new ClassPathXmlApplicationContext("spring.xml");

    User user1 = context.getBean("user", User.class);
    User user2 = context.getBean("user", User.class);

    System.out.println(user1.hashCode());//832279283
    System.out.println(user2.hashCode());//265119009

}
```

