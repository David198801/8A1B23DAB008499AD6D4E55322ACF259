在settings.xml中全局设置

```javascript
<profiles>
  <profile>
    <id>jdk-1.8</id>
    <activation>
      <jdk>1.8</jdk>
      <activeByDefault>true</activeByDefault>
    </activation>
    <properties>
      <project.build.sourceEncoding>UTF-8</project.build.sourceEncoding>
      <project.reporting.outputEncoding>UTF-8</project.reporting.outputEncoding>
      <maven.compiler.encoding>UTF-8</maven.compiler.encoding>
      <maven.compiler.source>1.8</maven.compiler.source>
      <maven.compiler.target>1.8</maven.compiler.target>
      <maven.compiler.compilerVersion>1.8</maven.compiler.compilerVersion>
    </properties>
  </profile>
</profiles>
```



在pom.xml中设置

方法1.在properties中设置

```javascript
 <properties>
        <maven.compiler.source>1.8</maven.compiler.source>
        <maven.compiler.target>1.8</maven.compiler.target>
</properties>
```

方法2.在build -> plugins 中设置编译插件

```javascript
　<build>
        <plugins>
            <plugin>
                <groupId>org.apache.maven.plugins</groupId>
                <artifactId>maven-compiler-plugin</artifactId>
                <version>3.8.1</version>
                <configuration>
                    <source>1.8</source>
                    <target>1.8</target>
            </configuration>
            </plugin>
        </plugins>
    </build>

```

