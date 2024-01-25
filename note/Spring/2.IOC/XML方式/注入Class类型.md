Class可以传全类名字符串，会调用

```javascript
public SyfpWhiteListBO(Class<SyfpWhiteList> persistentClass) {
        super(persistentClass);
    }
```



```javascript
<bean id="syfpWhiteListBO" class="com.yss.acs.thirdservice.cmbc.biz.SyfpWhiteListBO">
		<constructor-arg type="java.lang.Class"
			value="com.yss.acs.api.third.vo.SyfpWhiteList" />
	</bean>
```



```javascript
<bean id="dataMartService" class="com.someClass">
    <constructor-arg>
        <value type="java.lang.Class">someotherclass</value>
    </constructor-arg>
</bean>
```



