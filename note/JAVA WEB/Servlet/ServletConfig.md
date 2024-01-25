在init(ServletConfig config)(有无参重载)中传入并赋给属性

或使用getServletConfig()获取

重写init()后记得调用super.init()，否则servletConfig未经初始化

功能：

1.获取Servlet别名(web.xml中定义)

```javascript
System.out.println("servlet名称："+config.getServletName());
```

2.获取<init-param>初始化参数

```javascript
servletConfig.getInitParameter("参数名")

System.out.println("初始化参数："+config.getInitParameter("p1"));
```

3.获取ServletContext

```javascript
System.out.println(config.getServletContext());
```

