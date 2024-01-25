

```javascript
Boolean  isAjax  =  false;
isAjax  =  (request.getHeader("X-Requested-With")  !=  null  &&  "XMLHttpRequest".equals(request
.getHeader("X-Requested-With").toString()));
```

