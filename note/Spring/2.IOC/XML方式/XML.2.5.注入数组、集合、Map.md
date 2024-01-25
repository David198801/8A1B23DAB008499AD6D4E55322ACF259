

```javascript
<bean id="card1" class="com.lzb.spring.bean.Card"/>
<bean id="card2" class="com.lzb.spring.bean.Card"/>
```



数组，使用<array>或<list>，内部为<ref bean="id">或<value>

若是String，可以用<value>字符串内容</value>的形式

```javascript
<bean id="bag1" class="com.lzb.spring.bean.Bag">
    <property name="cards">
        <array>
            <ref bean="card1"/>
            <ref bean="card2"/>
        </array>
    </property>
</bean>
```





集合，使用<list>标签，内部为<ref bean="id">或<value>

```javascript
<bean id="bag1" class="com.lzb.spring.bean.Bag">
    <property name="cardList">
        <list>
            <ref bean="card1"/>
            <ref bean="card2"/>
        </list>
    </property>
</bean>
```



Map，使用<map>标签，内部为<entry>标签，属性为key、value(String)或key-ref="id"、value-ref="id"

```javascript
<bean id="bag1" class="com.lzb.spring.bean.Bag">
    <property name="cardMap">
        <map>
            <entry key="c1" value-ref="card1"/>
            <entry key="c2" value-ref="card2"/>
        </map>
    </property>
</bean>
```

