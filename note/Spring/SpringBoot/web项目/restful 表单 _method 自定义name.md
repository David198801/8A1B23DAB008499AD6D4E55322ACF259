开启配置项

```javascript
spring.mvc.hiddenmethod.filter.enabled=true
```

即可识别表单提交的_method参数



要修改_method为自定义的name，可以在容器中注册一个HiddenHttpMethodFilter 

```javascript
@Bean
public HiddenHttpMethodFilter hiddenHttpMethodFilter(){
    HiddenHttpMethodFilter hiddenHttpMethodFilter = new HiddenHttpMethodFilter();
    hiddenHttpMethodFilter.setMethodParam("_m");
    return hiddenHttpMethodFilter;
}
```

