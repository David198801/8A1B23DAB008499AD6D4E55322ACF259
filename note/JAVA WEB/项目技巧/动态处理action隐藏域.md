一个页面同时负责add或update操作

隐藏域的value需要动态处理

```javascript
<input type="hidden" name="action" value="" />
```



方式1.跳转/提交时携带操作参数

```javascript
<a href="pages/manager/book_edit.jsp?actionValue=add">添加图书</a>
```



```javascript
req.getRequestDispatcher("/pages/manager/book_edit.jsp?actionValue=update").forward(req,resp);
```



```javascript
value="${param.actionValue}"
```

方式2.判断是否有参数 或 域对象转发数据

```javascript
value="${empty param.id?"add":"update"}"
```



```javascript
value="${empty requestScope.book?"add":"update"}"
```

