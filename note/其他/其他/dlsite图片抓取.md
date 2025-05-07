找到imageBlob，注入这段内容

好像必须替换后在本地修改才行，来源里格式化后修改会不加载

```javascript
const imageUrl = URL.createObjectURL(t.imageBlob);const link = document.createElement("a");link.href = imageUrl;link.download = t.name+".png";link.click();URL.revokeObjectURL(imageUrl);
```

