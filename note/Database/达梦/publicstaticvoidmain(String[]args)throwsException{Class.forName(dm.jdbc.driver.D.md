

```javascript
public static void main(String[] args) throws Exception {
    Class.forName("dm.jdbc.driver.DmDriver");
    Properties props = new Properties();
    props.setProperty("username", "SYSDBA");
    props.setProperty("password", "999999999");
    Connection connection = DriverManager.getConnection("jdbc:dm://localhost:5236",props);

    PreparedStatement ps = connection.prepareStatement("SELECT banner as 版本信息 FROM v$version");
    ResultSet rs = ps.executeQuery();
    while (rs.next()) {
        System.out.println(rs.getObject(1));
    }

}
```

