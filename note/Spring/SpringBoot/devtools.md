maven

```javascript
<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-devtools</artifactId>
    <optional>true</optional>
</dependency>
```

开启只要build会自动重启应用



若要使用真正的热加载，可以使用收费插件JRebel



1.JRebel加载的速度优于devtools

2.JRebel不仅仅局限于Spring Boot项目，可以用在任何的Java项目中。

3.devtools 方式的热部署在功能上有限制，方法内的修改可以实现热部署，但新增的方法或者修改方法参数之后热部署是不生效的。