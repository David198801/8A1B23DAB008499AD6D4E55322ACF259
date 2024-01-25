

```javascript
package utils;

import com.mchange.v2.c3p0.ComboPooledDataSource;

import java.sql.*;

public class JDBCUtils {
    private static ComboPooledDataSource cpds = new ComboPooledDataSource("c3p0");
    //获得连接
    public static Connection getConnection()  {
        Connection conn= null;
        try {
            conn = cpds.getConnection();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return conn;
    }

    //关闭资源
    public static void closeResource(PreparedStatement ps){
        try {
            if(null!=ps){
                ps.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public static void closeResource(Connection conn){
        try {
            if(null!=conn){
                conn.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public static void closeResource(ResultSet rs){
        try {
            if(null!=rs){
                rs.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public static void closeResource(Connection conn, PreparedStatement ps, ResultSet rs){
        closeResource(ps);
        closeResource(conn);
        closeResource(rs);
    }
}
```

