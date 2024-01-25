https://docs.oracle.com/en/cloud/paas/autonomous-database/serverless/adbsb/connect-jdbc-thin-wallet.html#GUID-32A48CAA-89AC-40A4-AFD1-BB962C562805



https://docs.oracle.com/en/database/oracle/oracle-database/21/jjdbc/data-sources-and-URLs.html#GUID-6F729E4D-064B-4FD9-AE92-1BD44B8BE5EF



https://github.com/Apress/exp-oracle-java-security/blob/master/OraJavSecure/Chapter11/wallet/TestWallet.java









建立wallet

```javascript
mkstore -wrl /tmp/wl -create
```

添加wallet凭证

```javascript
mkstore -wrl /tmp/wl -createCredential localhost:1521/myservice user pass
```

连接

```javascript
Class.forName("oracle.jdbc.driver.OracleDriver");
Properties props = new Properties();
props.setProperty("oracle.net.wallet_location", "c:\\oracle\\wallet");
Connection connection = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521/ORCL",props);

PreparedStatement ps = connection.prepareStatement("select 1 from dual");
ResultSet rs = ps.executeQuery();
while (rs.next()){
    String string = rs.getString(1);
    System.out.println(string);

}

ps.close();
connection.close();
```























在Java中使用Wallet连接Oracle数据库时，通常不需要设置"oracle.net.tns_admin"属性来指定TNS_ADMIN目录。相反，您可以通过设置一些其他属性来实现这一目的。下面是一个示例代码片段，演示了如何在Java中使用Wallet连接Oracle数据库：



```javascript
import java.sql.Connection;
import java.sql.DriverManager;
import java.util.Properties;

public class OracleWalletExample {
    public static void main(String[] args) throws Exception {
        String url = "jdbc:oracle:thin:@(DESCRIPTION=(ADDRESS=(PROTOCOL=TCPS)(HOST=hostname)(PORT=2484))(CONNECT_DATA=(SERVICE_NAME=service_name)))";

        Properties props = new Properties();
        props.setProperty("oracle.net.wallet_location", "(SOURCE=(METHOD=FILE)(METHOD_DATA=(DIRECTORY=/path_to_wallet_directory)))");

        Connection conn = DriverManager.getConnection(url, props);
        // 使用连接执行数据库操作
        conn.close();
    }
}
```

在上面的示例中，我们将"oracle.net.wallet_location"属性设置为Wallet所在的目录。这样，Java代码就能够使用Wallet进行安全的数据库连接，而不需要设置"oracle.net.tns_admin"属性。

请注意，实际的连接URL和钱包目录路径应该根据您的实际环境进行调整。另外，您可能还需要在您的项目中包含适当的Oracle JDBC驱动程序。

希望这可以帮助到您！如果您有其他问题，请随时问我。





在Java中连接Oracle数据库使用Wallet时，设置"oracle.net.wallet_location"属性和在URL中添加"WALLET_LOCATION"参数是等效的。这两种方式都可以让Java程序在连接Oracle数据库时使用Wallet进行身份验证。

例如，您可以使用以下任一方式连接Oracle数据库：



```javascript
// 使用"oracle.net.wallet_location"属性连接Oracle数据库
//放-Doracle.net.wallet_location也行，System.setPropertie也行
//放properties文件也行，放oracle.properties会自动读取
Properties props = new Properties();
//props.setProperty("oracle.net.wallet_location", "(SOURCE=(METHOD=FILE)(METHOD_DATA=(DIRECTORY=/path_to_wallet_directory)))");
props.setProperty("oracle.net.wallet_location", "/path_to_wallet_directory");
Connection conn1 = DriverManager.getConnection("jdbc:oracle:thin:@hostname:1521/service_name", props);

// 在URL中添加"WALLET_LOCATION"参数连接Oracle数据库
String url = "jdbc:oracle:thin:@(description=(address=(protocol=tcp)(host=hostname)(port=1521))(connect_data=(service_name=service_name))(security=(ssl_server_cert_dn=\"CN=hostname,OU=xyz,O=abc,L=city,S=state,C=country\")(ssl_authentication_protocol=\"TCPS\"))(WALLET_LOCATION=(SOURCE=(METHOD=FILE)(METHOD_DATA=(DIRECTORY=/path_to_wallet_directory))))";
Connection conn2 = DriverManager.getConnection(url);
```

无论使用哪种方式，Java程序都将使用Wallet对Oracle数据库进行身份验证。

请注意，上述示例中的连接URL中包含了其他安全选项（例如SSL证书验证）。如果您的环境需要这些选项，请根据实际情况相应地调整连接URL。

希望这可以回答您的问题！







