在doGet() doPost()中

```javascript
req.getRequestDispatcher("/hello").forward(req,resp);
```



1.url不会变，相对路径参照会出错，应搭配base标签

2.只有一个请求，共享request资源

3.可以转发到WEB-INF中

4.无法访问工程外的地址，如百度(应使用重定向)