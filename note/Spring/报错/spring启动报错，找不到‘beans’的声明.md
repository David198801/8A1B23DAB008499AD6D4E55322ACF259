spring启动报错，找不到‘beans’的声明



一个项目启动报错：



Line 19 in XML document from ServletContext resource [/WEB-INF/classes/applicationContext.xml]

is invalid; nested exception is org.xml.sax.SAXParseException; lineNumber:

19; columnNumber: 10; cvc-elt.1: 找不到元素 'beans' 的声明。at org.springframework.beans.factory.xml.XmlBeanDefinitionReader.doLoadBeanDefinitions(XmlBeanDefinitionReader.java:396)



之所以报这个错，是因为项目里spring的jar包版本和spring配置文件里声明的xsd版本不一致，声明是3.2，但jar包里是4.0



```javascript
<?xml version="1.0" encoding="UTF-8"?>
<beans xmlns="http://www.springframework.org/schema/beans"
	xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance" 
	xmlns:context="http://www.springframework.org/schema/context"
	xmlns:aop="http://www.springframework.org/schema/aop"
	xmlns:tx="http://www.springframework.org/schema/tx"
	xmlns:mvc="http://www.springframework.org/schema/mvc"
	
	xsi:schemaLocation="http://www.springframework.org/schema/beans
      http://www.springframework.org/schema/beans/spring-beans-3.2.xsd
      http://www.springframework.org/schema/context
      http://www.springframework.org/schema/context/spring-context-3.2.xsd
      http://www.springframework.org/schema/aop
      http://www.springframework.org/schema/aop/spring-aop-3.2.xsd
      http://www.springframework.org/schema/tx
      http://www.springframework.org/schema/tx/spring-tx-3.2.xsd
      http://www.springframework.org/schema/mvc
      http://www.springframework.org/schema/mvc/spring-mvc-3.2.xsd
      ">
</beans>
```

      http://www.springframework.org/schema/tx

Spring默认在启动时是要加载XSD文件来验证xml文件的，所以如果有的时候断网了，或者一些开源软件切换域名，那么就很容易碰到应用启动不了。为了防止这种情况，Spring提供了一种机制，默认从本地加载XSD文件。

写代码，一些配置文件难免要复制粘贴，但不应该只是复制粘贴就完事了，要检查相关代码是否符合自己项目的情况。