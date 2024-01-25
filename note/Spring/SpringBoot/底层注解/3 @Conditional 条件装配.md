@Conditional，满足条件则注入组件

派生注解

@ConditionalOnBean，容器中存在指定的bean，才执行

@ConditionalOnMissingBean，容器中没有指定的bean，才执行

@ConditionalOnClass，classpath中存在指定类时，才执行

@ConditionalOnClass，classpath不存在指定类时，才执行

@ConditionalOnResource，classpath中存在指定资源时，才执行

@ConditionalOnJava，java版本为指定版本时，才执行

@ConditionalOnWebApplication，当应用是web应用时，才执行

@ConditionalOnNotWebApplication，当应用不是web应用时，才执行

@ConditionalOnSingleCandidate，当容器中只有一个实例或者多实例但有一个主实例(@Primary)时，才执行

@ConditionalOnProperty，当配置了某个属性并等于指定值时，才执行



如@ConditionalOnBean，可以根据类型、name等判断

容器中有pet时，才在容器中创建user

```javascript
@ConditionalOnBean(name = "pet")
@Bean
public User user(){
    return new User("张三",18);
}

public Pet pet(){
    return new Pet("小白");
}
```

pet不存在时，user也没有被创建

```javascript
System.out.println("user:"+run.containsBean("user"));
System.out.println("pet:"+run.containsBean("pet"));
// user:false
// pet:false
```



也可以放在配置类上，则满足条件时，配置类中的组件才会被创建

