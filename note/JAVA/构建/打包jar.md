eclipse

export，jar或者runnable jar



指定MANIFEST.MF的MainClass

maven

```javascript
<build>
    <plugins>
        <plugin>
            <groupId>org.apache.maven.plugins</groupId>
            <artifactId>maven-shade-plugin</artifactId>
            <version>3.2.1</version>
            <executions>
                <execution>
                    <phase>package</phase>
                    <goals>
                        <goal>shade</goal>
                    </goals>
                    <configuration>
                        <transformers>
                            <transformer implementation="org.apache.maven.plugins.shade.resource.ManifestResourceTransformer">
                            	<!--这里写你的main函数所在的类的路径名-->
                                <mainClass>com.hello.MainClass</mainClass>
                            </transformer>
                        </transformers>
                    </configuration>
                </execution>
            </executions>
        </plugin>
    </plugins>
</build>

```























1. 介绍

1.1 介绍

福哥在使用Maven打包TFSpring项目的时候，遇到了一个问题，就是调试类的程序不想打包到jar库包里面去，并且测试启动类也不想打包到jar库包里面去。

要实现这些先要把POM配置文件分开多个版本，调试时候用一个版本，打包时候用一个版本。

启动类也要分开多个版本，调试一个版本，打包一个版本。

今天福哥就带着大家来了解一下如何将项目的POM配置文件和启动类分开多个版本。

MainClass启动类

1.2 SpringBoot

1.2.1 start-class

网上有人说，在properties下面通过start-class设置启动类，福哥照着做了一下。

```javascript
<properties>
    <java.version>13</java.version>
    <start-class>net.tongfu.tfspring.TfspringApplicationPackage</start-class>
</properties>
```

事实证明，无效！

maven依然尝试编译两个启动类，并报出了错误！

1.2.2 plugin

网上还有人说，增加springframework的plugin，通过mainClass设置启动了，福哥又尝试了一下。

```javascript
<plugin>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-maven-plugin</artifactId>
    <configuration>
        <mainClass>net.tongfu.tfspring.TfspringApplicationPackage</mainClass>
    </configuration>
</plugin>
```

事实证明，依然无效！

maven还是尝试编译两个启动类，并报出了错误！

1.3 Maven

1.3.1 pluginManagement

还有人说，在pluginManagement里面增加maven-compiler-plugin，设置mainClass，福哥也弄了一下。

```javascript
<pluginManagement>
    <plugins>
        <plugin>
            <groupId>org.apache.maven.plugins</groupId>
            <artifactId>maven-compiler-plugin</artifactId>
            <version>3.7.0</version>
            <configuration>
                <archive>
                    <manifest>
                        <addDefaultImplementationEntries>true</addDefaultImplementationEntries>
                        <addDefaultSpecificationEntries>true</addDefaultSpecificationEntries>
                        <addClasspath>true</addClasspath>
                        <mainClass>net.tongfu.tfspring.TfspringApplicationPackage</mainClass>
                    </manifest>
                </archive>
            </configuration>
        </plugin>
    </plugins>
</pluginManagement>
```

事实证明，还是无效！

maven还是尝试编译两个启动类，并报出了错误！

2. 解决

经过福哥的各种测试，发现了一个规律！

貌似无论我们怎么配置，编译器都会尝试将每一个包含main方法的类当作启动类进行处理。

既然编译器一定要处理，福哥就给它排除掉就好了~

2.1 排除

福哥把默认的TfspringApplication排除掉，就保留一个TfspringApplicationPackage。

```javascript
<excludes>
    <exclude>**/demo/**</exclude>
    <exclude>**/TfspringApplication.java</exclude>
</excludes>
```

测试一下，成功了！

maven只报了TfspringApplicationPackage的错误！