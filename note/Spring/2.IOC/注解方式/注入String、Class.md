多此一举。。。

```javascript
@Configuration
public class Config {
    @Bean
    public String sb(){
        return "aaa";
    }
}
```



```javascript
@Autowired
public HelloWorldController(String clazz){
    System.out.println(clazz);
}
```



Class

```javascript
@Configuration
public class Config {
    @Bean
    public Class<User> clazz(){
        return User.class;
    }
}
```



```javascript
@Autowired
public HelloWorldController(Class<User> clazz){
    System.out.println(clazz);
}
```

