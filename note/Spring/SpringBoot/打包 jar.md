jar，打包成可执行文件，包含所有需要的库，可以用java直接运行，被称为fat jar

1.引入依赖

```javascript
<build>
    <plugins>
        <plugin>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-maven-plugin</artifactId>
        </plugin>
    </plugins>
</build>
```

2.执行maven执行package，生成jar

3.使用java -jar xxx.jar即可运行，或者关联了jre直接双击会自动在后台运行



cmd不要用快速编辑模式，启动中点击会卡住