SLF4J: Failed to load class "org.slf4j.impl.StaticLoggerBinder".

SLF4J: Defaulting to no-operation (NOP) logger implementation

SLF4J: See http://www.slf4j.org/codes.html#StaticLoggerBinder for further details.



java.lang.ExceptionInInitializerError



解决：加上slf4j-nop.jar slf4j-simple.jar， slf4j-log4j12.jar，slf4j-jdk14.jar或 logback-classic.jar中的其中一个jar包

```javascript
<dependency>
  <groupId>org.slf4j</groupId>
  <artifactId>slf4j-simple</artifactId>
  <version>1.7.25</version>
  <scope>compile</scope>
</dependency>
```



官网

Failed to load class org.slf4j.impl.StaticLoggerBinder

This warning message is reported when the org.slf4j.impl.StaticLoggerBinder class could not be loaded into memory. This happens when no appropriate SLF4J binding could be found on the class path. Placing one (and only one) of slf4j-nop.jar slf4j-simple.jar, slf4j-log4j12.jar, slf4j-jdk14.jar or logback-classic.jar on the class path should solve the problem.

Note that slf4j-api versions 2.0.x and later use the ServiceLoader mechanism. Backends such as logback 1.3 and later which target slf4j-api 2.x, do not ship with org.slf4j.impl.StaticLoggerBinder. If you place a logging backend which targets slf4j-api 2.0.x, you need slf4j-api-2.x.jar on the classpath. See also relevant faq entry.

SINCE 1.6.0 As of SLF4J version 1.6, in the absence of a binding, SLF4J will default to a no-operation (NOP) logger implementation.

If you are responsible for packaging an application and do not care about logging, then placing slf4j-nop.jar on the class path of your application will get rid of this warning message. Note that embedded components such as libraries or frameworks should not declare a dependency on any SLF4J binding but only depend on slf4j-api. When a library declares a compile-time dependency on a SLF4J binding, it imposes that binding on the end-user, thus negating SLF4J's purpose.