设定properties内的标签，即可用EL表达式使用

```javascript
<properties>
    <!-- spring版本 -->
    <spring.version>5.2.9.RELEASE</spring.version>
</properties>
```



```javascript
<dependency>
    <groupId>org.springframework</groupId>
    <artifactId>spring-core</artifactId>
    <version>${spring.version}</version>
</dependency>
```

