1.通过无参构造器创建对象

2.设置属性值(调用set方法)

3.把bean对象传给后置处理器(需要实现接口)，执行postProcessBeforeInitialization()

4.调用bean的初始化方法(需要进行设置)

5.把bean对象传给后置处理器(需要实现接口)，执行postProcessAfterInitialization()

6.使用bean

7.容器关闭时，调用bean的销毁方法(需要进行配置)



后置处理器需要bean实现BeanPostProcessor接口