导入jackson包

```javascript
<dependency>
    <groupId>com.fasterxml.jackson.core</groupId>
    <artifactId>jackson-databind</artifactId>
    <version>2.11.4</version>
</dependency>
```

使用@ResponseBody注解，handler方法返回一个对象或集合、map即可返回json

```javascript
@ResponseBody
@GetMapping("testJson")
public List<User> testJson(){
    List<User> list = new ArrayList<>();
    list.add(new User(1, "aaa", "aaa", "a@a.a"));
    list.add(new User(2, "bbb", "bbb", "b@b.b"));
    return list;
}
```





https://blog.csdn.net/zbajie001/article/details/79738181

原理：

HttpMessageConverter<T>是Spring3.0添加的一个接口，将对象转换为输出信息



|   |   |   |   |
| - | - | - | - |
| 请求报文→HttpInputMessage→ | HttpMessageConverter | →JAVA对象→ | SpringMVC |




HttpMessageConverter有很多实现类，默认6个，导入jackson后自动添加一个



使用HttpMessageConverter有两种方式

1.使用@RequestBody或@ResponseBody

2.使用HttpEntity<T>或ResponseEntity<T>作为handler方法的入参或返回值

SpringMVC会根据request header的accept，结合handler方法的返回或参数类型，来匹配使用的HttpMessageConverter，匹配不到会报错











IE不认识json的content type的问题

原因：首先说说@ResponseBody的作用，这个注解将返回值转化为json字符串，而且在返回时添加response头信息，类似于设置 response.setContentType("application/json; charset=UTF-8");")，但是json这玩意儿IE不认识，就当做文件处理了，也就会弹出“是否下载”的对话框来。



既然是@ResponseBody注解上的问题，也就是说，在@ResponseBody对应的controller里面设置的头部信息肯定会在外面被覆盖掉。还好Spring确实考虑了这一点。

```javascript
<mvc:annotation-driven ignoreDefaultModelOnRedirect="true">
<!-- 这里配置IE的json格式兼容性 -->
<mvc:message-converters register-defaults="false">
   <bean class="org.springframework.http.converter.ByteArrayHttpMessageConverter" />
   <bean class="org.springframework.http.converter.StringHttpMessageConverter">
      <property name="supportedMediaTypes" value="text/plain;charset=UTF-8" />
   </bean>
   <bean class="org.springframework.http.converter.json.MappingJackson2HttpMessageConverter">
      <property name="supportedMediaTypes" value="text/plain;charset=UTF-8" />
   </bean>
</mvc:message-converters>
</mvc:annotation-driven>
```





