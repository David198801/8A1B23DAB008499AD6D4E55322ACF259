https://www.cnblogs.com/qianzf/p/12836486.html

```javascript
当第一个tomcat启动后，后面tomcat的server.xml中的端口不管怎么改，仍然会报端口冲突。后来在dos下运行才发现所有的tomcat都会去找CATALINA_HOME和CATALINA_BASE这两个环境变量，因此步骤如下：  
1.使用压缩版的tomcat不能使用安装版的。  
2.第一个tomcat的配置不变。  
3.增加环境变量CATALINA_HOME2，值为新的tomcat的地址；增加环境变量CATALINA_BASE2，值为新的tomcat的地址。  
4.修改新的tomcat中的startup.bat，把其中的CATALINA_HOME改为CATALINA_HOME2。  
5.修改新的tomcat中的catalina.bat，把其中的CATALINA_HOME改为CATALINA_HOME2，CATALINA_BASE改为CATALINA_BASE2。  
6.修改conf/server.xml文件：  
6.1 <Server port="8005" shutdown="SHUTDOWN">把端口改为没有是使用的端口。  
6.2 <Connector port="8080" maxHttpHeaderSize="8192"  
  maxThreads="150" minSpareThreads="25" maxSpareThreads="75"  
  enableLookups="false" redirectPort="8443" acceptCount="100"  
  connectionTimeout="20000" disableUploadTimeout="true" /> 把端口改为没有是使用的端口。  
6.3<Connector port="8009"  
  enableLookups="false" redirectPort="8443" protocol="AJP/1.3" /> 把端口改为没有是使用的端口。  

7成功！
```

