beans.xml

```javascript
<bean id="pet1" class="com.lzb.boot.pojo.Pet">
    <property name="name" value="pet1"/>
</bean>
```

在配置类上导入文件

```javascript
@ImportResource("classpath:beans.xml")
public class SpringConfig
```

即可使用其中的组件

```javascript
Object pet1 = run.getBean("pet1");
System.out.println(pet1);//Pet{name='pet1'}
```

