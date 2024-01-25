https://zhuanlan.zhihu.com/p/66789473



在controller上使用@CrossOrigin开启CORS跨域

```javascript
@CrossOrigin(origins = "*",maxAge = 3600)
```



AbstractHandlerMapping的内部类CorsInterceptor拦截请求并处理跨域



实际处理在这个类中

org.springframework.web.cors.DefaultCorsProcessor

processRequest()处理请求

检查是否为跨域请求，不是则直接return true

调用return handleInternal()，如果满足跨域条件则添加响应并return true





全局处理跨域

https://blog.csdn.net/weixin_44917045/article/details/114404204

```javascript
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class CorsConfig implements WebMvcConfigurer {
    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
                .allowedHeaders("*")
                .allowedMethods("*")
                .allowedOriginPatterns("*")
                .allowCredentials(true);
    }
}
```

