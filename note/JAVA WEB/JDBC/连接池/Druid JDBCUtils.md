

```javascript
package utils;

import com.alibaba.druid.pool.DruidDataSourceFactory;
import com.mchange.v2.c3p0.ComboPooledDataSource;
import org.apache.commons.dbcp.BasicDataSourceFactory;

import javax.sql.DataSource;
import java.io.IOException;
import java.io.InputStream;
import java.sql.*;
import java.util.Properties;

public class JDBCUtils {

    private static DataSource source = null;
    static{
        try {
            Properties prop=new Properties();
            InputStream is=ClassLoader.getSystemResourceAsStream("druid.properties");
            prop.load(is);
            source = DruidDataSourceFactory.createDataSource(prop);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //获得连接
    public static Connection getConnection()  {
        Connection conn= null;
        try {
            conn = source.getConnection();
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

