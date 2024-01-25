Driver for test database type [HSQL] is not available in the classpath

Driver for test database type [H2] is not available in the classpath



查看好久发现 原来是 idea工具 在 事务的配置文件下 加了一段引用dataSource的无用代码

```javascript
<jdbc:embedded-database id="dataSource"/>
```

把它去掉就ok了 mark一下