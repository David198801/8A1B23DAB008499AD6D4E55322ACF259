initializer勾上mybatis、mysql，druid需要手动加到pom.xml中



单纯整合mybatis，只需配置mysql连接信息和mybatis的配置文件、mapper.xml即可

注意mysql connector 8类名：com.mysql.cj.jdbc.Driver

application.yaml

```javascript
spring:
  datasource:
    driver-class-name: com.mysql.cj.jdbc.Driver
    username: root
    password: root
    url: jdbc:mysql://localhost:3306/train

mybatis:
  config-location: classpath:mybatis-config.xml
  mapper-locations: classpath*:mappers/*.xml
```



整合druid，创建一个配置类，@Bean返回DruidDataSource，绑定spring.datasource的配置项

```javascript
package com.lzb.train.config;

import com.alibaba.druid.pool.DruidDataSource;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;

@Configuration
public class DruidConfig {
    @Bean
    @ConfigurationProperties(prefix = "spring.datasource")
    public DataSource druidDataSource(){
        return new DruidDataSource();
    }
}
```



新：实际不需要在主配置类中导入

主配置类导入

```javascript
@Import({DruidConfig.class})
@SpringBootApplication
public class TrainApplication
```

