1.创建对象，默认使用无参构造器

xml中<beans>内添加<bean>标签

<bean>属性：

id，相当于一个别名，通过id可以获得该对象

class，bean类的完整类名

scope

singleton：单例

prototype：每次获取bean创建实例

request：每次请求创建实例

session：每次session创建实例

init-method="方法名"，初始化方法

destroy-method="方法名"，初始化方法

name，作用与id相同，区别是可以使用特殊字符如"/"，早期使用，现在使用不多

```javascript
<bean id="user" class="com.lzb.spring.bean.User"></bean>
```







