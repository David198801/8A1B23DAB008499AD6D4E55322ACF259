

```javascript
public class OutputStreamWriter extends Writer
```



1.构造方法

(1)传入OutputStream

```javascript
public OutputStreamWriter(OutputStream out)
```

(2)传入OutputStream，指定字符集

```javascript
public OutputStreamWriter(OutputStream out, String charsetName)
```

eg.

```javascript
OutputStreamWriter(fos, "GBK")
```

