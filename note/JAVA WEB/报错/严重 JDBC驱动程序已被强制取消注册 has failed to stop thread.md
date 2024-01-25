二月 21, 2021 2:56:35 下午 org.apache.catalina.loader.WebappClassLoaderBase clearReferencesJdbc

严重: Web应用程序 [/book] 注册了JDBC驱动程序 [com.alibaba.druid.proxy.DruidDriver]，但在Web应用程序停止时无法注销它。 为防止内存泄漏，JDBC驱动程序已被强制取消注册。

二月 21, 2021 5:00:11 下午 org.apache.catalina.loader.WebappClassLoaderBase clearReferencesJdbc

严重: Web应用程序 [/book] 注册了JDBC驱动程序 [com.mysql.jdbc.Driver]，但在Web应用程序停止时无法注销它。 为防止内存泄漏，JDBC驱动程序已被强制取消注册。

二月 21, 2021 5:00:12 下午 org.apache.catalina.startup.TldConfig execute

严重: The web application [/book] appears to have started a thread named [mysql-cj-abandoned-connection-cleanup] but has failed to stop it. This is very likely to create a memory leak.

二月 21, 2021 2:56:35 下午 org.apache.catalina.loader.WebappClassLoaderBase clearReferencesThreads

严重: The web application [/book] appears to have started a thread named [Druid-ConnectionPool-Create-514897160] but has failed to stop it. This is very likely to create a memory leak.

二月 21, 2021 2:56:35 下午 org.apache.catalina.loader.WebappClassLoaderBase clearReferencesThreads

严重: The web application [/book] appears to have started a thread named [Druid-ConnectionPool-Destroy-514897160] but has failed to stop it. This is very likely to create a memory leak.

二月 21, 2021 2:56:36 下午 org.apache.catalina.startup.TldConfig execute





原因

druid未关闭连接池：[Druid-ConnectionPool-Create-514897160] 、[Druid-ConnectionPool-Destroy-514897160]

druid未关闭Abandoned线程：[mysql-cj-abandoned-connection-cleanup]

jdbc未解除注册：严重: Web应用程序] 注册了JDBC驱动程序...JDBC驱动程序已被强制取消注册

https://blog.csdn.net/qq_37834488/article/details/89928607

解决：ServletContextListener，销毁前关闭连接池、关闭Abandoned线程、解除注册

```javascript
package com.book.utils;

import com.alibaba.druid.pool.DruidDataSource;
import com.mysql.jdbc.AbandonedConnectionCleanupThread;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Enumeration;

public class AppListener implements ServletContextListener {
    @Override
    public void contextInitialized(ServletContextEvent sce) {

    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        // 关闭连接池
        DruidDataSource druidDataSource= (DruidDataSource) JdbcUtils.getSource();
        druidDataSource.close();
        // jdbc驱动解除注册
        try {
            Enumeration<Driver> drivers = DriverManager.getDrivers();
            while(drivers.hasMoreElements()){
                DriverManager.deregisterDriver(drivers.nextElement());
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        // 关闭 Abandoned 线程
        try {
            AbandonedConnectionCleanupThread.checkedShutdown();
        } catch (Exception e) {
            System.out.println("ContextFinalizer:SEVERE problem cleaning up: " + e.getMessage());
            e.printStackTrace();
        }
    }
}
```



```javascript
<listener>
    <listener-class>com.book.utils.AppListener</listener-class>
</listener>
```





可能原因：

过时的报错，jdbc未解除注册，无视/配置监听器解决

https://blog.csdn.net/weixin_45783996/article/details/107971824

https://blog.csdn.net/qq_44758515/article/details/106935615



注解方法名错误

https://blog.csdn.net/weixin_42956690/article/details/107710578

tomcat端口号大于四位数

https://www.cnblogs.com/w-yu-chen/p/13054042.html





