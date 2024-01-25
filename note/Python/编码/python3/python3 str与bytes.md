bytes类型，字节串，类似字节数组

str.encode()得到bytes

bytes.decode()得到str

```javascript
"\\a".encode()
# b"\\a"，类似[92,97]
```

bytes元素为int

```javascript
type(b"\\a"[0])
# <class 'int'>
```

