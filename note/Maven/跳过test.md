

```javascript
<plugin>  
    <groupId>org.apache.maven.plugins</groupId>  
    <artifactId>maven-surefire-plugin</artifactId>  
    <configuration>  
        <skipTests>true</skipTests>  
    </configuration>  
</plugin>
```

或

```javascript
mvn package -Dmaven.test.skip=true 
```

