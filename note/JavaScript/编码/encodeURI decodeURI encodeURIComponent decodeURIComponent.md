作用：进行URL编码解码，对非latin1字符转为UTF-8，结果为字符串而非字节数组

区别：

encodeURI对除了 英文字母 数字 ~!@#$&*()=:/,;?+' 以外的字符进行编码

encodeURIComponent对除了 英文字母 数字 ~!*()' 以外的字符进行编码

```javascript
encodeURI('http://a.com/中文') 
// "http://a.com/%E4%B8%AD%E6%96%87"
encodeURIComponent('http://a.com/中文') 
// "http%3A%2F%2Fa.com%2F%E4%B8%AD%E6%96%87"

decodeURI("http://a.com/%E4%B8%AD%E6%96%87") 
// "http://a.com/中文"
decodeURIComponent("http://a.com/%E4%B8%AD%E6%96%87") 
// "http://a.com/中文"
decodeURIComponent("http%3A%2F%2Fa.com%2F%E4%B8%AD%E6%96%87") 
// "http://a.com/中文"
```

