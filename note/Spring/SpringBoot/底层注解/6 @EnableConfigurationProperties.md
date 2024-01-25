使@ConfigurationProperties的类生效，即将@ConfigurationProperties标注的bean注册到spring容器



可以使@ConfigurationProperties的类不用类似@Component进行注册，而是使用时注册



另外如果@ConfigurationProperties是在第三方包中，那么不能使用@component注入到容器，只能使用@EnableConfigurationProperties



```javascript
@EnableConfigurationProperties({ WebMvcProperties.class, ResourceProperties.class })
```



https://www.jianshu.com/p/7f54da1cb2eb