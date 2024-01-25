https://blog.csdn.net/zl3450341/article/details/9306983



1.无论是classpath还是classpath*都可以加载整个classpath下（包括jar包里面）的资源文件。

2.classpath只会返回第一个匹配的资源，查找路径是优先在项目中存在资源文件，再查找jar包。

3.文件名字包含通配符资源(如spring-*.xml，spring*.xml)，   如果根目录为""， classpath加载不到任何资源， 而classpath*则可以加载到classpath中 可以匹配的目录中的资源，但是不能加载到jar包中的资源