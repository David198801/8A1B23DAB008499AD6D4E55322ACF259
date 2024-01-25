https://blog.csdn.net/weixin_39805338/article/details/81060142

https://www.cnblogs.com/lxcmyf/p/7144292.html



使用自定义连接池，导入对应的DataSource



不使用连接池

org.springframework.jdbc.dataSource.DriverManagerDataSource



使用tomcat自带的dbcp，开发时需要导入tomcat的library

org.apache.commons.dbcp.BasicDataSource



从JNDI中获取连接池

org.springframework.jndi.JndiObjectFactoryBean



使用mybatis自带数据源

mybatis自带连接池

org.apache.ibatis.datasource.pooled.PooledDataSource

不用连接池

org.apache.ibatis.datasource.unpooled.UnpooledDataSource