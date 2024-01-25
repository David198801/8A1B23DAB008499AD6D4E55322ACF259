String encode

```javascript
str.getBytes(charSetName)
```

String decode

```javascript
new String(byteArray,charSetName)
```



乱码恢复

```javascript
new String(str.getBytes(错误的读取编码),正确的读取编码)
```

