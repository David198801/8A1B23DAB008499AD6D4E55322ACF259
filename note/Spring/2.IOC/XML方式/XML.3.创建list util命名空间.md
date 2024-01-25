添加xmlns:util空间，指定schemaLocation

```javascript
<beans xmlns="http://www.springframework.org/schema/beans"
       xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
       xsi:schemaLocation="
       http://www.springframework.org/schema/beans
       http://www.springframework.org/schema/beans/spring-beans.xsd
       http://www.springframework.org/schema/util
       http://www.springframework.org/schema/util/spring-util.xsd"

       xmlns:util="http://www.springframework.org/schema/util"
>
```

使用<util:list>创建list

```javascript
<util:list id="cardList1">
    <ref bean="card1"/>
    <ref bean="card2"/>
</util:list>
```

注入

```javascript
<bean id="bag1" class="com.lzb.spring.bean.Bag">
    <property name="cardList" ref="cardList1"/>
</bean>
```

