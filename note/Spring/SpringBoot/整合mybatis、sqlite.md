指定driver和datasource即可

 [代码.zip](./assets/代码.zip) 



```yaml
# SQLite 数据源配置
spring.datasource.url=jdbc:sqlite:d:/test1/sqlite.db
spring.datasource.driver-class-name=org.sqlite.JDBC

#MyBatis
#指定Mybatis的Mapper文件
mybatis.mapper-locations=classpath:mappers/*xml
#指定Mybatis的实体目录
mybatis.type-aliases-package=com.example.springbootsqlite.mybatis.entity

# Druid 连接池配置
spring.datasource.type=com.alibaba.druid.pool.DruidDataSource
# 连接池配置:
# 初始化时建立物理连接的个数
spring.datasource.druid.initial-size=2
# 最大连接池数量
spring.datasource.druid.max-active=10
# 最小连接池数量
spring.datasource.druid.min-idle=2
# 属性类型是字符串，通过别名的方式配置扩展插件，配置多个英文逗号分隔。
# 常用的插件有：监控统计用的filter:stat,日志用的filter:log4j,防御sql注入的filter:wall
spring.datasource.druid.filters=stat,wall
# 监控配置:
# WebStatFilter配置
# 是否启用StatFilter默认值false
spring.datasource.druid.web-stat-filter.enabled=true
spring.datasource.druid.web-stat-filter.url-pattern=/*
# 排除不必要的url
spring.datasource.druid.web-stat-filter.exclusions=*.js,*.gif,*.jpg,*.png,*.css,*.ico,/druid/*
# 是否开启session统计功能
spring.datasource.druid.web-stat-filter.session-stat-enable=true
# session最大数，缺省sessionStatMaxCount是1000个
spring.datasource.druid.web-stat-filter.session-stat-max-count=500
# 用户user信息保存在session中的sessionName，druid能够知道当前的session用户
spring.datasource.druid.web-stat-filter.principal-session-name=web_user
# 用户user信息保存在cookie 中的cookieName，druid能够知道当前的用户
#spring.datasource.druid.web-stat-filter.principal-cookie-name=
# 监控单个url调用的sql列表
spring.datasource.druid.web-stat-filter.profile-enable=true
# StatViewServlet配置
# 是否启用StatViewServlet（监控页面）默认值为false（考虑到安全问题默认并未启动，如需启用建议设置密码或白名单以保障安全）
spring.datasource.druid.stat-view-servlet.enabled=true
# 访问url模式
spring.datasource.druid.stat-view-servlet.url-pattern=/druid/*
# 是否允许清空统计数据
spring.datasource.druid.stat-view-servlet.reset-enable=true
# 监控页面访问用户名
spring.datasource.druid.stat-view-servlet.login-username=admin
# 监控页面访问密码
spring.datasource.druid.stat-view-servlet.login-password=admin
# -访问控制：
#   -deny优先于allow，如果在deny列表中，就算在allow列表中，也会被拒绝。
#   -如果allow没有配置或者为空，则允许所有访问）
#   -由于匹配规则不支持IPV6，配置了allow或者deny之后，会导致IPV6无法访问
# 访问控制，白名单IP
#spring.datasource.druid.stat-view-servlet.allow=
# 访问控制，黑名单IP
#spring.datasource.druid.stat-view-servlet.deny=

```

