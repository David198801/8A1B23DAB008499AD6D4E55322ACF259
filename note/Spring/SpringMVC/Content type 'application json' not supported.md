https://blog.csdn.net/fpxty/article/details/72835993

https://segmentfault.com/a/1190000008675224





```javascript
<bean class="org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerAdapter">
    <property name="messageConverters">
        <list>
            <bean class="org.springframework.http.converter.json.MappingJack2sonHttpMessageConverter"></bean>
        </list>
    </property>
</bean>
```



spring3旧版

```javascript
<bean
		class="org.springframework.web.servlet.mvc.annotation.AnnotationMethodHandlerAdapter">
		<property name="messageConverters">
			<list>
				<bean class="org.springframework.http.converter.json.MappingJacksonHttpMessageConverter">
					<property name="supportedMediaTypes">
                        <list>
                            <value>application/json</value>
                            <value>application/json;charset=UTF-8</value>
                        </list>
                    </property>
				</bean>
			</list>
		</property>
	</bean>
```

