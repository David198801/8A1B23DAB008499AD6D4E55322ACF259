

```javascript
public abstract class Reader implements Readable, Closeable
```

1.常用方法

(1)按字符读取

```javascript
public int read() throws IOException
```



```javascript
while((n=r.read())!=0){
	System.out.println((char)n);
}
```

(2)按缓冲区读取

```javascript
public int read(char cbuf[]) throws IOException {
	return read(cbuf, 0, cbuf.length);
}
```



```javascript
char[] charBuffer = new char[8];
while((n=fr.read(charBuffer))!=-1){
	System.out.print(new String(charBuffer,0,n));
}
```

(3)按缓冲区读取，指定起始、长度，在InputStreamReader中实现，调用StreamDecoder的read()

```javascript
abstract public int read(char cbuf[], int off, int len) throws IOException;
```

InputStreamReader中

```javascript
public int read(char cbuf[], int offset, int length) throws IOException {
	return sd.read(cbuf, offset, length);
}
```



(4)关闭流，在InputStreamReader中实现，调用StreamDecoder的close()

```javascript
abstract public void close() throws IOException;
```

