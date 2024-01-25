1.构造方法

(1)传入File

```javascript
public FileReader(File file) throws FileNotFoundException
```

(2)传入String，自动创建File

```javascript
public FileReader(String fileName) throws FileNotFoundException
```



FileReader只定义了3个构造方法，使用的方法继承自父类



2.FileReader读文件

```javascript
FileReader fr = null;
try {
	//读取文件
	fr = new FileReader("c:\\test.txt.");
	int len = -1;
	char[] buffer = new char[1024];
	StringBuffer sb = new StringBuffer();
	//读取文本
	while((len =fr.read(buffer))!=-1){
		//append到StringBuffer中
		sb.append(buffer,0,len);
	}
	System.out.println(sb);
} catch (FileNotFoundException e) {
	e.printStackTrace();
}catch (IOException e) {
	e.printStackTrace();
} finally{
	//关闭
	try {
		fr.close();
	} catch (IOException e) {
		e.printStackTrace();
	}
}
```

