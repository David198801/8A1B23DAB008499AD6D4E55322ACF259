mysql-connector-java 5.1使用com.mysql.jdbc.Driver

mysql-connector-java 8 使用com.mysql.cj.jdbc.Driver，使用旧的会报错



com.mysql.cj.jdbc.Driver需要指定时区

```javascript
jdbc:mysql://localhost:3306/test?serverTimezone=Asia/Shanghai&useUnicode=true&characterEncoding=utf8&useSSL=false
```

