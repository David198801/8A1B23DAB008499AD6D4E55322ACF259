was websphere IBM JDK下访问SSL/HTTPS时候ClassNotFoundException

java.lang.ClassNotFoundException: com.ibm.websphere.ssl.protocol.SSLSocketFactory



jvm只允许加载一种JSSE(Java Secure Socket Extension) socket factory，加载了com.ibm.websphere.ssl.protocol.SSLSocketFactory后，OSGI程序的类加载器无法加载这个实现导致报错。



启动时设置默认值为com.ibm.jsse2.SSLSocketFactoryImpl

```java
static{
	String vendor = System.getProperty("java.vendor");
	if (vendor.startsWith("IBM")) {
		Security.setProperty("ssl.SocketFactory.provider","com.ibm.jsse2.SSLSocketFactoryImpl");
		Security.setProperty("ssl.ServerSocketFactory.provider","com.ibm.jsse2.SSLServerSocketFactoryImpl");
		logger.info("ssl.SocketFactory.provider初始化为com.ibm.jsse2.SSLSocketFactoryImpl");
		logger.info("ssl.ServerSocketFactory.provider初始化为com.ibm.jsse2.SSLServerSocketFactoryImpl");
	}
}
```

或
更改 <IBM_JAVA_HOME>/jre/lib/security/java.security里的设置，改成

```javascript
# Default JSSE socket factories
ssl.SocketFactory.provider=com.ibm.jsse2.SSLSocketFactoryImpl
ssl.ServerSocketFactory.provider=com.ibm.jsse2.SSLServerSocketFactoryImpl
# WebSphere socket factories (in cryptosf.jar)
#ssl.SocketFactory.provider=com.ibm.websphere.ssl.protocol.SSLSocketFactory
#ssl.ServerSocketFactory.provider=com.ibm.websphdere.ssl.protocol.SSLServerSocketFactory
```

