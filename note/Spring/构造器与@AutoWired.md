总结：优先使用@AutoWired指定的，其余按照注入的参数选择



1. @Controller + 无参构造 + @Autowired有参构造，使用有参构造



2.无参构造 + 有参构造(String)，xml：constructor-args value="xxxx"，使用有参构造(String)



3. 有参构造(String) + 有参构造(Service)，xml：constructor-arg value="xxxx"，使用有参构造(Service)



4. 有参构造(String)，@Autowired有参构造(Service)，xml：constructor-arg value="xxxx"，使用有参构造(Service)



```javascript
@RequestMapping("/helloworld")
public class HelloWorldController {

    private HelloWorldService helloWorldService;

    public HelloWorldController(String str){
        System.out.println(str);
        System.out.println("有参构造器");
    }

    @Autowired
    public HelloWorldController(HelloWorldService helloWorldService){
        System.out.println("有参构造器Service");
        this.helloWorldService = helloWorldService;
    }

    @RequestMapping("/h1")
    public String hello(){
        return "helloworld";
    }
}
```



```javascript
<bean class="com.lzb.mvc.controller.HelloWorldController">
    <constructor-arg value="xxxx"></constructor-arg>
</bean>
```

输出:有参构造器Service



删掉@AutoWired

输出:xxxx

有参构造器String

