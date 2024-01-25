Hibernate Validator是jsr303的一个实现，除了标准的注解外还支持一些额外注解



文档

https://docs.jboss.org/hibernate/stable/validator/reference/en-US/html_single/#validator-gettingstarted-uel



maven

validation-api 2.0.1.Final无法识别hibernate-validator7.0.1.Final，需要用6.2.0.Final或

```javascript
<dependency>
    <groupId>org.hibernate</groupId>
    <artifactId>hibernate-validator</artifactId>
    <version>6.2.0.Final</version>
</dependency>
<dependency>
    <groupId>org.hibernate.validator</groupId>
    <artifactId>hibernate-validator-cdi</artifactId>
    <version>6.2.0.Final</version>
</dependency>
```

还需要导入javax.validation并使用javax.validation.Valid注解

```javascript
<dependency>
    <groupId>javax.validation</groupId>
    <artifactId>validation-api</artifactId>
    <version>2.0.1.Final</version>
</dependency>
```

如果容器没有支持，还需要导入EL

```javascript
<dependency>
    <groupId>org.glassfish</groupId>
    <artifactId>jakarta.el</artifactId>
    <version>4.0.0</version>
</dependency>
```

若tomcat自带EL版本过低(如tomcat7)，可能出现NoSuchMethodException，可以删除或替换tomcat自带的EL