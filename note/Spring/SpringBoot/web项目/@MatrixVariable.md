SpringBoot 需要设置启用矩阵变量，设置removeSemicolonContent为false

配置类实现WebMvcConfigurer ，重写configurePathMatch()，set一个关闭removeSemicolonContent的UrlPathHelper 即可

或者也可以放一个@Bean返回WebMvcConfigurer 的匿名实现类对象，其中以同样的方式重写configurePathMatch()即可

```javascript
public class SpringConfig implements WebMvcConfigurer {

    @Override
    public void configurePathMatch(PathMatchConfigurer configurer) {
        UrlPathHelper urlPathHelper = new UrlPathHelper();
        urlPathHelper.setRemoveSemicolonContent(false);
        configurer.setUrlPathHelper(urlPathHelper);
    }
```



矩阵变量

qurey string参数为类似/a/path?id=xxxx&brand=xxx

矩阵变量参数为类似/a/path;id=xxxx;brand=xxx;p3;p4，以分号分隔



@MatrixVariable，从url中获取

/a/path;id=111;brand=xxx;p3;p4

```javascript
@GetMapping("/a/{path}")
public Map test(
    @MatrixVariable("id") Integer id,
    @MatrixVariable("brand") String brand,
    @PathVariable("path") String p
){}
```





如果url中参数重复，可以使用@MatrixVariable的pathVar注解

/a/boos;id=111/emp;id=005

```javascript
@GetMapping("/a/{boos}/{emp}")
public Map test(
    @MatrixVariable(value = "id",pathVar = "boos") Integer id1,
    @MatrixVariable("id",pathVar = "emp") Integer id2
){}
```

