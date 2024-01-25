1.设置

preference--maven

installations，设置maven位置，不建议使用embedded，添加自己的

user settings，指定配置文件、找本地仓库，一般将user settings设为conf\settings.xml即可



2.创建maven project，勾选simple project（或者选择插件创建，但可能目录不全）

输入三个坐标即可



3.选择pom.xml，run as执行maven命令



创建web工程

建立maven工程，打包改成war，然后properties--project facets，取消dynamic web project，apply，重新勾选dynamic web project，further configure available，目录改成src/main/webapp，勾选生成web.xml，确定（webapp不要在外面也有）

然后在pom.xml中添加servletapi的依赖

```javascript
<dependency>
    <groupId>javax.servlet</groupId>
    <artifactId>javax.servlet-api</artifactId>
    <version>3.0.1</version>
    <scope>provided</scope>
</dependency>
```

