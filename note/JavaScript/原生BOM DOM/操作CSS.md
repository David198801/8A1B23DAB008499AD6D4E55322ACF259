操作CSS

1.style属性

元素.style.样式名

```javascript
document.getElementById("cartList").display;
```

IE:HTML元素. currentStyle.样式属性;

```javascript
document.getElementById("cartList").currentStyle.display;
```

2.内部/外部

document.defaultView.getComputedStyle(元素,null).属性;

```javascript
var cartList=document.getElementById("cartList");
alert(document.defaultView.getComputedStyle(cartList,null).display);
```

3.隐藏/显示元素

```javascript
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title></title>
	</head>
	<body>
		<p style="border: 1px red solid;margin-left: auto;margin-right: auto;width: 80px;" onmouseover="over()" onmouseout="out()">悬停区域</p>
		<p id="p2" style="border: 1px red solid;margin-left: auto;margin-right: auto;width: 400px;height: 400px;" >显示区域</p>
		<script type="text/javascript">
			//document.getElementById('p2').style.display='none';
			function over(){
				document.getElementById('p2').style.display='block';
			}
			
			function out(){
				document.getElementById('p2').style.display='none';
			}
		</script>
	</body>
</html>

```

通过修改class，显示时覆盖在上

```javascript
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title></title>
		<style type="text/css">
			body{
				position: relative;
			}
			.cartout{
				display: none;
			}
			.cartover{
				border: 1px red solid;margin-left: -200px;width: 400px;height: 400px;left: 50%;
				display: block;
				position: absolute;
				z-index: 2;
				background-color: aqua;
			}
			.other{
				border: 1px red solid;margin-left: -150px;width: 300px;height: 300px;left: 50%;
				display: block;
				position: absolute;
				z-index: 1;
			}
		</style>
	</head>
	<body>
		<p style="border: 1px red solid;margin-left: auto;margin-right: auto;width: 80px;" onmouseover="over()" onmouseout="out()">悬停区域</p>
		<p class="cartout" id="p2">显示区域</p>
		<p class="other">其他内容区域</p>

		<script type="text/javascript">
			//document.getElementById('p2').style.display='none';
			function over(){
				document.getElementById('p2').className='cartover';
			}
			
			function out(){
				document.getElementById('p2').className='cartout';
			}
		</script>
	</body>
</html>

```

