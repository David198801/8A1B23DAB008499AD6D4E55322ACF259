作用：进行URL编码解码，对非latin1字符转为unicode，结果为字符串而非字节数组

```javascript
escape('http://a.com/中文')
// "http%3A//a.com/%u4E2D%u6587"

unescape("http%3A//a.com/%u4E2D%u6587")
// "http://a.com/中文"

//对于latin1范围内的%码兼容
unescape("http%3A%2F%2Fa.com%2F") //由encodeURIComponent编码
// "http://a.com/"
```

