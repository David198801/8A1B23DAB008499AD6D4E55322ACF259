导入dbcp，pool

文档，apidocs目录内，为api文档



quick start

```javascript
// 创建连接池
BasicDataSource source = new BasicDataSource();

// 设置四个属性
source.setDriverClassName("com.mysql.jdbc.Driver");
source.setUrl("jdbc:mysql://localhost:3306/test");
source.setUsername("root");
source.setPassword("root");

// 管理连接池
source.setInitialSize(10);
source.setMaxActive(20);

Connection conn = source.getConnection();

System.out.println(conn);
```



使用配置文件

dbcp.properties

```javascript
driverClassName=com.mysql.jdbc.Driver
url=jdbc:mysql://localhost:3306/test
username=root
password=root
```



```javascript
Properties prop=new Properties();
InputStream is = ClassLoader.getSystemResourceAsStream("dbcp.properties");
prop.load(is);

// 创建连接池
DataSource source = BasicDataSourceFactory.createDataSource(prop);


Connection conn = source.getConnection();

System.out.println(conn);
```

