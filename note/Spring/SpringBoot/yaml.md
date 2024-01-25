键值对，注意有空格

```javascript
key: value
```



大小写敏感

缩进表示层级

空格缩进

#注释

字符串可以直接写，也可以用引号，双引号的会解析\n等转义字符，单引号不解析



表示集合

```javascript
k: [v1,v2,v3]
#或
k2:
  - v1
  - v2
  - v3
```

表示对象/Map

```javascript
k: {k1: v1,k2: v2}
#或
kk:
  k1: v1
  k2: v2
```



yaml提示，需要配置Configuring the Annotation Processor

maven

```javascript
<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-configuration-processor</artifactId>
    <optional>true</optional>
</dependency>
```

	

If you are using AspectJ in your project, you need to make sure that the annotation processor runs only once. There are several ways to do this. With Maven, you can configure the maven-apt-plugin explicitly and add the dependency to the annotation processor only there. You could also let the AspectJ plugin run all the processing and disable annotation processing in the maven-compiler-plugin configuration, as follows:

```javascript
<plugin>
    <groupId>org.apache.maven.plugins</groupId>
    <artifactId>maven-compiler-plugin</artifactId>
    <configuration>
        <proc>none</proc>
    </configuration>
</plugin>
```

