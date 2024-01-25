https://blog.csdn.net/ws_blog/article/details/46986051

https://www.oschina.net/question/1435252_140476



### Error querying database.  Cause: org.springframework.jdbc.CannotGetJdbcConnectionException:

java.sql.SQLException: Access denied for user '??????'@'localhost' (using password: YES)



spring使用context:property-placeholder读取配置文件，使用了${userame}

${userame}读取到了环境变量userame，即系统用户名



解决方法：设置local-override属性为true

```javascript
<context:property-placeholder local-override="true" location="classpath:mysql.properties"/>
```

