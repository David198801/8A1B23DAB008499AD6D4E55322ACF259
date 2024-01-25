java代码中可通过如下代码设置

```javascript
response.setHeader("Pragma", "No-Cache");
response.setHeader("Cache-Control", "No-Cache");
response.setDateHeader("Expires", 0);
```



同理，nginx服务器上也可通过给nginx添加响应头，让html,css,js不缓存

nginx.conf

```javascript
location / {

    add_header Cache-Control no-cache;
    
    add_header Pragma no-cache;
    
    add_header Expires 0;

}
```

