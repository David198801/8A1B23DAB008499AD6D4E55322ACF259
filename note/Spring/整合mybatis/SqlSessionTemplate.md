https://www.cnblogs.com/hellowhy/p/9728862.html



```javascript
<!--配置sqlSessionTemplate：通过带参数的构造方法创建对象 -->
    <bean id="sqlSessionTemplate" class="org.mybatis.spring.SqlSessionTemplate">
        <!-- 以sqlSessionFactory为参数传入构造函数中 -->
        <constructor-arg ref="sqlSessionFactory" />
        <!-- mybatis执行器，取值范围是SIMPLE/REUSE/BATCH三种类型 -->
        <constructor-arg value="BATCH" />
</bean>
```

