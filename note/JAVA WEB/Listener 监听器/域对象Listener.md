Listener是java ee的规范，接口



Listener监听事件，通过回调函数进行处理





ServletContextListener

```javascript
public class Listen implements ServletContextListener {
    @Override
    public void contextInitialized(ServletContextEvent sce) {
        //在ServletContext创建后调用，初始化
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        //在ServletContext销毁后调用
    }
}
```

使用方法：

实现ServletContextListener，实现方法

web.xml中配置listener标签（<webapp>中）

```javascript
<listener>
    <listener-class>servlet.Listen</listener-class>
</listener>
```



其他监听器

https://blog.csdn.net/hbtj_1216/article/details/83015670

https://www.cnblogs.com/zhangyanran/p/10082180.html



ServletContextListener

contextInitialized()

contextDestroyed()

ServletRequestListener

requestInitialized()

requestDestroyed()

HttpSessionListener

sessionCreated()

sessionDestroyed()



ServletContextAttributeListener 监听ServletContext中属性变化

HttpSessionAttributeListener 监听HttpSession中属性变化

ServletRequestAttributeListener 监听ServletRequest中属性变化



attributeAdded 监听属性添加 —- 当数据范围对象没有该属性，第一次添加时调用执行

attributeRemoved 监听属性移除 —- 从一个数据范围对象删除一个已经存在属性执行

attributeReplaced 监听属性替换 —– 当一个数据范围已经存在一个属性，向数据范围添加相同名称属性触发替换方法



