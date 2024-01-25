package org.springframework.boot.context.properties;



读取application.properties里的的相应配置，封装到java bean中





application.properties

```javascript
server.port=8888

mycar.brand=bmw
mycar.price=1000000
```

java bean中使用@ConfigurationProperties，指定配置项中的前缀，注意必须在spring容器中创建才能使用@ConfigurationProperties

```javascript
@Component
@ConfigurationProperties(prefix = "mycar")
public class Car {
    private String brand;
    private Integer price;
```



```javascript
Object carProp = run.getBean("carProp");
System.out.println(carProp);//CarProp{brand='bmw', price=1000000}
```

