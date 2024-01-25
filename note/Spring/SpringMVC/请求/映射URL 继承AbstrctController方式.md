1.controller继承AbstrctController或实现Controller接口，实现其抽象方法handleRequestInternal()

```javascript
public class TestController implements Controller{
    @Override
    public ModelAndView handleRequest(HttpServletRequest request, HttpServletResponse response) throws Exception {
        response.getWriter().write("xxxxx");
        return null;
    }
}
```

可以supportedMethods属性设置请求方式

```javascript
<property name="supportedMethods">
    <array>
        <value>POST</value>
    </array>
</property>
```



2.可以用以下方法映射controller



2.1创建controller的bean，name属性为映射的url，通过BeanNameUrlHandlerMapping自动映射

```javascript
<bean id="/index" class="com.lzb.mvc.controller.TestController"/>
```

2.2创建SimpleUrlHandlerMapping，在属性中映射URL

```javascript
<bean id="testController" class="com.lzb.mvc.controller.TestController"/>

<bean class="org.springframework.web.servlet.handler.SimpleUrlHandlerMapping">
    <property name="mappings">
        <props>
            <prop key="/index">testController</prop>
        </props>
    </property>
</bean>
```

SimpleUrlHandlerMapping也可以设置urlMap属性

```javascript
<property name="urlMap">
    <map>
        <entry key="/hello">
            <value>second</value>
        </entry>
    </map>
</property>
```

