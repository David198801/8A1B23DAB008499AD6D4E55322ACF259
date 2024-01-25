重定向特点：

1.浏览器地址发生变化

2.两次请求，不共享request

3.无法访问WEB-INF

4.可以重定向到工程外，如百度



方法：

1.

```javascript
resp.sendRedirect("/svl/hello");
```

2.手动

```javascript
//设置响应码302
resp.setStatus(302);
//设置响应头location
resp.setHeader("location","xxx");
```

