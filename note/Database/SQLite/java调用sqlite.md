pom

```xml
<dependency>
    <groupId>org.xerial</groupId>
    <artifactId>sqlite-jdbc</artifactId>
    <version>3.45.2.0</version>
</dependency>
```



```java
// 加载SQLite驱动程序
Class.forName("org.sqlite.JDBC");

// 创建数据库连接
Connection conn = DriverManager.getConnection("jdbc:sqlite:d:/test1/sqlite.db");

PreparedStatement ps = conn.prepareStatement("select fid,fname from t1");
ResultSet rs = ps.executeQuery();
while (rs.next()) {
    System.out.println(rs.getString("fname"));
}

rs.close();
ps.close();
conn.close();
```

