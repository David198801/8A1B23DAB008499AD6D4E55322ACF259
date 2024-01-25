updete，edit.jsp接受转发的book参数，form提交修改后参数，id可用隐藏域一起提交

```javascript
<input type="hidden" name="id" value="${requestScope.book.id}" />
```

