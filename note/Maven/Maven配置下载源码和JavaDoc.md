Maven配置下载源码和JavaDoc



方法一：Maven命令下载源码和JavaDoc

在IDE中使用Maven时，如果想要看引用的jar包中类的源码和JavaDoc，可以通过Maven命令下载。

必须进入到相应的pom.xml项目中，然后执行命令。

cmd中执行mvn命令：

```javascript
mvn dependency:sources
mvn dependency:resolve -Dclassifier=javadoc
```



```javascript
mvn dependency:sources -DdownloadSources=true -DdownloadJavadocs=true
```

- 1

- 2

说明：

第一行命令：尝试下载在pom.xml中依赖的文件的源代码；

第二行命令：尝试下载对应的JavaDoc；

但是有可能一些文件没有源代码或者JavaDoc；



方法二：配置setting.xml

打开Maven配置文件 setting.xml文件(…/.m2/settings.xml) 增加如下配置：

```javascript
<profiles>
    <profile>  
        <id>downloadSources</id>  
        <properties>  
            <downloadSources>true</downloadSources>  
            <downloadJavadocs>true</downloadJavadocs>             
        </properties>  
    </profile>
</profiles>
<activeProfiles>  
    <activeProfile>downloadSources</activeProfile>  
</activeProfiles> 
```





- 



方法三：配置eclipse

Window > Preferences > Maven 然后勾选 Download Artifact Sources 和 Download Artifact JavaDoc 选项，在pom.xml配置相应包时，Maven在下载jar包的同时会下载源码和JavaDoc文档