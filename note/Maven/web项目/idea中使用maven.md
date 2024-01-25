https://zhuanlan.zhihu.com/p/122429605

file，settings，buildxxx，maven，修改目录、配置文件、仓库目录









web项目，需要创建server配置，如果转换为maven项目则旧的artifact会变为target

运行出错，更改设置后运行仍出错，可以删除target重试

1.建立maven项目时勾选archetype，选择maven:archetype-webapp

输入名字、3个坐标

选择maven位置、配置等

import changes

2.目录结构不完整，右键快速创建，或使用自建的archetype

3.web.xml,根标签替换成对应tomcat的web.xml的标签，以下是tomcat9的

```javascript
<web-app xmlns="http://xmlns.jcp.org/xml/ns/javaee"
         xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
         xsi:schemaLocation="http://xmlns.jcp.org/xml/ns/javaee http://xmlns.jcp.org/xml/ns/javaee/web-app_4_0.xsd"
         version="4.0">
```



若普通web项目转为maven web项目，jar包需要配置，否则要在artifact里手动导入

方法1，指定war打包方式(可将pom.xml按archetype设置)，import change后会自动创建war、war:expolded的artifact

```javascript
<packaging>war</packaging>
```

若404尝试将facet底部的source roots勾上



方法2，build添加映射，可能无效

```javascript
<!--配置Maven编译插件 -->
<build>
    <finalName>book</finalName>
    <plugins>
        <plugin>
            <artifactId>maven-compiler-plugin</artifactId>
            <configuration>
                <!--源码及编译版本-->
                <source>1.8</source>
                <target>1.8</target>
                <compilerArguments>
                    <!-- 无此指定则需要手动把maven的jar包，映射到lib下面 -->
                    <extdirs>src/main/webapp/WEB-INF/lib</extdirs>
                </compilerArguments>
            </configuration>
        </plugin>
    </plugins>
</build>
```



https://www.pianshen.com/article/8712898711/

```javascript
<!--配置Maven编译插件 -->
<build>
    <finalName>leon</finalName>
    <plugins>
        <plugin>
            <groupId>org.apache.maven.plugins</groupId>
            <artifactId>maven-compiler-plugin</artifactId>
            <version>3.5</version>
            <configuration>
                <source>1.8</source>
                <target>1.8</target>
                <compilerArguments>
                    <extdirs>src/main/webapp/WEB-INF/lib</extdirs><!-- 无此指定则需要手动把maven的jar包，映射到lib下面 -->
                    <bootclasspath>${java.home}/lib/rt.jar;${java.home}/lib/jce.jar</bootclasspath>
                </compilerArguments>
                <!-- 跳过测试用例 ,也不编译-->
                <skip>true</skip>
            </configuration>
        </plugin>
        <!-- 跳过测试用例 -->
        <plugin>
            <artifactId>maven-surefire-plugin</artifactId>
            <configuration>
                <skipTests>true</skipTests>
            </configuration>
        </plugin>
    </plugins></build>
```

1.遇到的坑：

A.<bootclasspath><extdirs>两个标签，如果配置多个数据，mac,linux用冒号(:)，而windows用分号(;)

B.<bootclasspath><extdirs>两个标签，windows路径用\，mac，linux用/

