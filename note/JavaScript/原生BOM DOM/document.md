document

1.常用属性

| referrer | 返回载入当前文档的URL |
| - | - |
| URL | 返回当前文档的URL |




```javascript
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title></title>
	</head>
	<body>
		<!--request中referrer，无则返回空串-->
		<input type="button"  value="referrer" onclick='javascript:alert(document.referrer)' />
		<br/>
		<!--URL大写-->
		<input type="button"  value="URL" onclick='javascript:alert(document.URL)' />
	</body>
</html>

```

2.常用方法

| getElementById() | 返回对拥有指定id的第一个对象的引用 |
| - | - |
| getElementsByName() | 返回带有指定名称的对象的集合 |
| getElementsByTagName() | 返回带有指定标签名的对象的集合 |
| write() | 向文档写文本、HTML表达式或JavaScript代码 |




```javascript
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title></title>
	</head>
	<body>
		
		<p id="pid"></p>
		<p name='pname'></p>
		<p name='pname'></p>
		<h2></h2>
		<script type="text/javascript">
			//根据id获取标签，修改内容
			document.getElementById('pid').innerText='根据id获取标签，修改内容';
			
			//根据name属性获取标签，返回nodeList，修改内容
			var names = document.getElementsByName('pname');
			names[0].innerText='根据name属性获取标签，修改数组[0]';
			names[1].innerText='根据name属性获取标签，修改数组[1]';
			
			//根据标签名获取标签，返回nodeList，修改内容
			var tns = document.getElementsByTagName('h2');
			tns[0].innerText='根据标签名获取标签，修改数组[0]';
		</script>
	</body>
</html>
```

