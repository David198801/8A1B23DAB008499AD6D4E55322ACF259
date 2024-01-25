

```javascript
public class BufferedReader extends Reader
```



1.成员变量

默认缓冲区大小

```javascript
private static int defaultCharBufferSize = 8192;
```



2.构造方法

(1)传入Reader

```javascript
public BufferedReader(Reader in)
```

(2)传入Reader，定义缓冲区大小sz

```javascript
public BufferedReader(Reader in, int sz)
```



3.方法

读取一行

```javascript
public String readLine() throws IOException
```

eg.

```javascript
br = new BufferedReader(r);
String line = null;
while((line=br.readLine())!=null){
	System.out.println(line);
}
```



4.BufferedReader读文件

```javascript
FileInputStream fis = null;
Reader r = null;
BufferedReader br = null;
try {
	//读取文件
	fis = new FileInputStream("c:\\test.txt");
	//指定编码读取
	r = new InputStreamReader(fis,"GBK");
	//创建缓冲流
	br = new BufferedReader(r);
	String line = null;
	while((line=br.readLine())!=null){
		System.out.println(line);
	}
	
	
} catch (FileNotFoundException e) {
	e.printStackTrace();
}catch (IOException e) {
	e.printStackTrace();
} finally{
	//关闭
	try {
		br.close();
		r.close();
		fis.close();
		
		
	} catch (IOException e) {
		e.printStackTrace();
	}
}
```

