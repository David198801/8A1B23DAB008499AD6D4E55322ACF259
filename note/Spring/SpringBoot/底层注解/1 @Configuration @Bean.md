1.创建bean

1.1.创建一个配置类

1.2.在配置类中用@Bean创建对象

```javascript
@Configuration
public class SpringConfig {
    @Bean
    public User user(){
        return new User("张三",18);
    }
}
```

1.3.MainApplication从容器中获取属性

```javascript
ConfigurableApplicationContext run = SpringApplication.run(MainApplication.class, args);

Object user = run.getBean("user");
System.out.println(user);//User{name='张三', age=18}
```



2.配置类本身也会创建一个bean对象

获取配置类的对象，可以通过其中定义的方法获取定义的bean



注解参数：

proxyBeanMethods，是否代理bean方法，true则会保持bean单实例，false则多次调用返回不同的实例，默认为true

但是getBean()不受影响，仍为单实例，因为容器只自动调用了一次bean方法

```javascript
@Configuration(proxyBeanMethods = false)
```



```javascript
User user1 = bean.user();
User user2 = bean.user();
System.out.println(user1==user2);//false
```



3.组件依赖，如user中的属性为pet，则可以说user依赖了pet

proxyBeanMethods = true时，在bean方法user()中调用pet()可以方便地解决组件依赖，在外部获取的pet和user中的是同一个

proxyBeanMethods = false时，pet会创建多个，不能用上述的方式



proxyBeanMethods = true，调用bean方法前会检查是否存在，效率较低，称为Full配置，用于组件被其他组件依赖的情况

proxyBeanMethods = false，不用检查，效率较高，称为Lite配置