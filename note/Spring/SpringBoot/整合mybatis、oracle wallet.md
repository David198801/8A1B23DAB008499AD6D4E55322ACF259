用spring.datasource.druid.connection-properties配置oracle.net.wallet_location





未验证

```properties
spring.datasource.driver-class-name=oracle.jdbc.OracleDriver
spring.datasource.url=jdbc:oracle:thin:@//your_host:your_port/your_service_name
spring.datasource.username=your_username
spring.datasource.password=your_password

spring.datasource.druid.connection-properties=oracle.net.wallet_location=(source=(method=file)(method_data=(directory=/path_to_your_wallet/)))
```

```yaml
spring:
  datasource:
    driver-class-name: oracle.jdbc.OracleDriver
    url: jdbc:oracle:thin:@//your_host:your_port/your_service_name
    username: your_username
    password: your_password
    druid:
      connection-properties: oracle.net.wallet_location=/path_to_your_wallet

```

