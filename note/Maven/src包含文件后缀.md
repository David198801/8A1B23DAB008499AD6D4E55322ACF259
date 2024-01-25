

```javascript
<build>
    <resources>
        <resource>
            <directory>src/main/java</directory>
            <includes>
                <include>**/*.properties</include>
                <include>**/*.xml</include>
            </includes>
            <!-- 是否替换资源中的属性-->
            <filtering>false</filtering>
        </resource>
    </resources>
</build>
```

