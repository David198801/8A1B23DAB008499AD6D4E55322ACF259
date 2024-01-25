Math

1.常用方法

| ceil() | 对数进行上舍入 | Math.ceil(25.5);返回26 |
| - | - | - |
| Math.ceil(-25.5);返回-25 | floor() | 对数进行下舍入 |
| Math.floor(25.5);返回25 | Math.floor(-25.5);返回-26 | round() |
| 把数四舍五入为最接近的数 | Math.round(25.5);返回26 | Math.round(-25.5);返回-26 |
| random() | 返回0~1之间的随机数 | Math.random();例如：0.6273608814137365 |


PI

E

```javascript
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title></title>
	</head>
	<body>
		<script type="text/javascript">
			//向上取整
			var n1 = Math.ceil(2.6);
			document.write('ceil(2.6):',n1,'<br/>');
			//向下取整
			var n2 = Math.floor(2.6);
			document.write('floor(2.6):',n2,'<br/>');
			//舍入
			var n3 = Math.round(2.6);
			document.write('round(2.6):',n3,'<br/>');
			//[0-1)随机数
			var n4 = Math.random();
			document.write('random():',n4,'<br/>');
		</script>
	</body>
</html>

```

