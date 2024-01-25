xml方式

```javascript
<import resource="spring2.xml"/>
<import resource="spring3.xml"/>
```



@Import注解方式

```javascript
@Configuration
@Import({StdOutConfig.class, FileLogConfig.class, MysqlLogConfig.class})
public class LogParentConfig {

}
```



@Import注解的其他用途

https://www.cnblogs.com/dongguangming/p/12963060.html