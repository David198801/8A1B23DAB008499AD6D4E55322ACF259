

```javascript
public class BufferedWriter extends Writer
```

1.成员变量

默认缓冲区大小

```javascript
private static int defaultCharBufferSize = 8192;
```



2.构造方法

(1)传入Writer

```javascript
public BufferedWriter(Writer out)
```

(2)传入Writer,指定缓冲区大小

```javascript
public BufferedWriter(Writer out, int sz)
```



3.方法

(1)写入int,强转为char

```javascript
public void write(int c) throws IOException
```

(2)写入char[],off,len

```javascript
public void write(char cbuf[], int off, int len) throws IOException
```

(3)写入String,off,len

```javascript
public void write(String s, int off, int len) throws IOException
```

(4)写入一个换行符

```javascript
public void newLine() throws IOException
```

