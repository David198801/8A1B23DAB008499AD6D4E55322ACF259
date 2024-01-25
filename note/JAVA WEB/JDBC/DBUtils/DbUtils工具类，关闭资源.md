在JDBCUtils中，getConnection()由连接池获取，关闭资源可用DbUtils工具类，也可用自己写的，实现都一样是try和判断null



省去判断null

```javascript
public static void closeResource(Connection conn){
    try {
        DbUtils.close(conn);
    } catch (SQLException e) {
        e.printStackTrace();
    }
}
```

或都省

```javascript
public static void closeResource(Connection conn){
    DbUtils.closeQuietly(conn);
}
```

