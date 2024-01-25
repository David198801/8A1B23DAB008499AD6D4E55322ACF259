

```javascript
public abstract class Writer implements Appendable, Closeable, Flushable
```



1.方法

(1)写入char

```javascript
public void write(char cbuf[]) throws IOException
```

(2)写入int，强转为char

```javascript
public void write(int c) throws IOException
```

(3)写入char[]

```javascript
public void write(char cbuf[]) throws IOException {
	write(cbuf, 0, cbuf.length);
}
```

(4)写入char[]，指定起始、长度，在OutputStreamWriter中实现，调用StreamEncoder的write()

```javascript
abstract public void write(char cbuf[], int off, int len) throws IOException
```

(5)写入String

```javascript
public void write(String str) throws IOException {
	write(str, 0, str.length());
}
```

(6)写入String，指定起始、长度

```javascript
public void write(String str, int off, int len) throws IOException
```



(7)flush调用StreamEncoder的flush()

```javascript
public void flush() throws IOException {
	se.flush();
}
```

(8)close调用StreamEncoder的close()

```javascript
public void close() throws IOException {
	se.close();
}
```

