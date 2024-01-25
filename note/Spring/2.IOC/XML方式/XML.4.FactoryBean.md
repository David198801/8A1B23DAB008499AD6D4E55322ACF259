普通的bean，getBean()获取的类型就是自己，即<bean>的class属性的类型

而FactoryBean获取的类型可以不一样



定义一个类实现FactoryBean接口，返回值类型由FactoryBean的泛型参数及实现的getObject()方法确定

```javascript
package com.lzb.spring.bean;

import org.springframework.beans.factory.FactoryBean;

public class StringFactory implements FactoryBean<String>{
    @Override
    public String getObject() throws Exception {
        // 实例，实际会用到工厂
        return new String("aaa");
    }

    @Override
    public Class<?> getObjectType() {
        return null;
    }

    @Override
    public boolean isSingleton() {
        return false;
    }
}
```



```javascript
<bean id="stringFactory1" class="com.lzb.spring.bean.StringFactory">

</bean>
```



```javascript
@Test
public void test4(){
    ApplicationContext context = new ClassPathXmlApplicationContext("spring.xml");

    String string = context.getBean("stringFactory1", String.class);

    System.out.println(string);//aaa
}
```

