原文出于:https://www.jianshu.com/p/20a63d144551；https://blog.csdn.net/heylun/article/details/78732451

什么是静态资源路径

静态资源路径是指系统可以直接访问的路径，且路径下所有文件均可被用户直接读取。

在springboot中默认的静态资源路劲有:classpath:/META-INF/resources/ ,classpath:/static/ ,classpath:/public/ ,classpath:/resources/

从这里看出这里的静态资源都在classpath下





那么问题来了，如果上传的文件放在上述的文件夹中会有怎样的后果？

1 网站的数据和代码不能有效分离

2 当项目打成jar包，上传的图片会增加jar的大小，运行效率降低

3 网站数据备份变得复杂





此时可以将静态资源路径设置到磁盘的某个目录

1 在springboot中可以直接在配置文件中覆盖默认的静态资源路径的配置信息：

application.properties配置如下:

```javascript
web.upload-path=D:/temp/images/
springboot.mvc.static-path-pattern=/**
spring.resources.static-locations=classpath:/META-INF/resources/,classpath:/static,classpath:/resources/,file:{web.upload-path}
```

注意：这个web.upload-path是属于自定义的一个属性，指定一个路径，注意要以/结尾；

spring.mvc.static-path-parttern=/** 表示所有的访问经过静态资源路径

spring.resources.static-locations在这里配置静态资源路径，前面说了这里的配置是覆盖默认配置，所以需要加上默认的，否则static，public这些路径

将不能被当作静态资源路径，在这个末尾的file:{web.upload-path}之所以加file：是因为指定的是一个具体的硬盘路径，classpath值系统环境变量









上述是通过配置文件实现的，也可以通过静态资源配置类实现，继承WebMvcConfigurerAdapter

```javascript
package com.sam.demo.conf;


import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;


/**
 * 配置静态资源映射
 * @author sam
 * @since 2017/7/16
 */
@Configuration
public class WebMvcConfig extends WebMvcConfigurerAdapter {


    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        //将所有/static/** 访问都映射到classpath:/static/ 目录下
        registry.addResourceHandler("/static/**").addResourceLocations("classpath:/static/");
    }
}
```





重启项目，访问：http://localhost:8080/static/c.jpg 能正常访问static目录下的c.jpg图片资源。

















springboot项目获取文件的绝对路径

```javascript
//获取跟目录
File path=new File(ResourceUtils.getURL("classpath:").getPath());
if(!path.exists()){
path=new File("");
}
//如果上传目录为/static/images/upload/,则可以如下获取
File upload=new File(path.getAbsolutePath(),"static/images/uplaod/");
if(!upload.exists()){
 upload.mkdirs();
 System.out.println(upload.getAbsolutePath());
 //在开发测试模式时，得到地址为：{项目跟目录}/target/static/images/upload/
 //在打成jar正式发布时，得到的地址为:{发布jar包目录}/static/images/upload/


}
```





另外使用以上代码需要注意，因为以jar包发布时，我们存储的路径是与jar包同级的static目录，因此我们需要在jar包目录的application.properties配置文件中设置静态资源路径，如下所示：

#设置静态资源路径

```javascript
spring.resources.static-locations=classpath:static/,file:static/
```

 