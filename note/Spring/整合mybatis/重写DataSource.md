

```javascript
import com.alibaba.druid.pool.DruidDataSource;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class DruidOracleWalletDataSource extends DruidDataSource {

    private static final String driverClassName = "oracle.jdbc.driver.OracleDriver";

    @Override
    public Connection getConnection() throws SQLException {
        String url = this.getUrl();
        Properties properties = new Properties();
        properties.putAll(this.getConnectProperties());

        // 设置钱包文件路径
        properties.setProperty("oracle.net.wallet_location", "c:\\oracle\\wallet");

        try {
            Class.forName(driverClassName);
            // 获取钱包认证连接
            Connection connection = DriverManager.getConnection(url, properties);
            return connection;
        } catch (ClassNotFoundException e) {
            throw new SQLException("Failed to load Oracle JDBC driver", e);
        }
    }
}

```



```javascript
import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class HikariCPOracleWalletDataSource extends HikariDataSource {

    private static final String driverClassName = "oracle.jdbc.driver.OracleDriver";

    @Override
    public Connection getConnection() throws SQLException {
        String url = this.getJdbcUrl();
        Properties properties = new Properties();
        properties.putAll(this.getDataSourceProperties());

        // 设置钱包文件路径
        properties.setProperty("oracle.net.wallet_location", "c:\\oracle\\wallet");

        try {
            Class.forName(driverClassName);
            // 获取钱包认证连接
            Connection connection = DriverManager.getConnection(url, properties);
            return connection;
        } catch (ClassNotFoundException e) {
            throw new SQLException("Failed to load Oracle JDBC driver", e);
        }
    }

    public static void main(String[] args) throws SQLException {
        HikariConfig config = new HikariConfig();
        config.setJdbcUrl("jdbc:oracle:thin:@127.0.0.1:1521/ORCL");
        config.setUsername("username");
        config.setPassword("password");

        HikariCPOracleWalletDataSource dataSource = new HikariCPOracleWalletDataSource();
        dataSource.setDataSourceProperties(config.getDataSourceProperties());
        dataSource.setJdbcUrl(config.getJdbcUrl());
        dataSource.setUsername(config.getUsername());
        dataSource.setPassword(config.getPassword());

        Connection conn = dataSource.getConnection();
        // 使用连接进行操作
        // ...

        conn.close();
        dataSource.close();
    }
}

```

