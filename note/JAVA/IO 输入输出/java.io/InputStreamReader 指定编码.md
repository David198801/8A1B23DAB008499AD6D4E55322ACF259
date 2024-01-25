

```javascript
public class InputStreamReader extends Reader
```

1.构造方法

```javascript
public InputStreamReader(InputStream in)
```

传入InputStream

```javascript
public InputStreamReader(InputStream in, String charsetName)
        throws UnsupportedEncodingException
```

传入InputStream,字符集

eg.

```javascript
InputStreamReader(fis,"UTF-8")
```





按指定编码读取

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

