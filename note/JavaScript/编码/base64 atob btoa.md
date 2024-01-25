btoa，binary to ascii

atob，ascii to binary



对非字母数字的字符，可以使用url编码，再转成latin1字符串

```javascript
btoa(unescape(encodeURIComponent("中文abc")))
// "5Lit5paHYWJj"

decodeURIComponent(escape(atob("5Lit5paHYWJj")))
// "中文abc"
```

