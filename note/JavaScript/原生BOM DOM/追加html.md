https://developer.mozilla.org/zh-CN/docs/Web/API/Element/insertAdjacentHTML

```javascript
<!DOCTYPE html>
<html>
	<head>
		<meta content="text/html; charset=utf-8" />
		<title></title>
	</head>
	<body >
		<input id="i1" onload="done()" onclick="clk()" onmouseover="over()" onkeydown="kd()" onchange="change()" />
		<script type="text/javascript">
			function done(){
				document.getElementById("i1").insertAdjacentHTML("afterEnd",'<br/>'+"输入框加载完成");
			}
			
			function clk(){
				document.getElementById("i1").insertAdjacentHTML("afterEnd",'<br/>'+"鼠标点击了输入框");
			}
			
			function over(){
				document.getElementById("i1").insertAdjacentHTML("afterEnd",'<br/>'+"鼠标移到了输入框上");
			}
			
			function kd(){
				document.getElementById("i1").insertAdjacentHTML("afterEnd",'<br/>'+"按下键盘");
			}
			
			function change(){
				document.getElementById("i1").insertAdjacentHTML("afterEnd",'<br/>'+"输入框内容改变");
			}
			
		</script>
	</body>
</html>
```

