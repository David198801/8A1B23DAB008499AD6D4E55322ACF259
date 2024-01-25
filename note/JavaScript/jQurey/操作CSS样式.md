操作样式

1.设置和获取样式值，css()

```javascript
<!DOCTYPE html>
<html>
	<head>
		<meta charset="utf-8">
		<title></title>
		<script type="text/javascript" src="jquery.min.js"></script>
		<script type="text/javascript">
			$(function(){
				$('#d').css('color','red');
				$('#d').css({'font-size':'50px','font-weight':'bold'});
				setTimeout(function(){
					alert($('#d').css('font-size'));
				},100);
			})
		</script>
	</head>
	<body>
		<div id="d">
			div
		</div>
	</body>
</html>

```



2.通过class添加和移除样式

HTML可以有多个class，表现为 class="aaa bbb ccc"

addClass('aaa')，添加类

addClass('aaa bbb')，添加多个类

removeClass()，移除类

addClass('ccc aaa')，移除多个类，顺序不影响

toggleClass()，调用一次执行addClass()，再调用执行removeClass()

hasClass()，判断是否包含类

offset()，获取和设置元素的坐标(css的top和left属性)

```javascript
<!DOCTYPE html>
<html>
	<head>
		<meta charset="utf-8">
		<title></title>
		<style type="text/css">
			.yellow{
				background: yellow;
			}
			.fsize{
				font-size: 50px;
			}
			.b{
				font-weight: bold;
			}
		</style>
		<script type="text/javascript" src="jquery.min.js"></script>
		<script type="text/javascript">
			$(function(){
				//鼠标经过则添加类
				$('#d').mouseover(function(){
					$('#d').addClass("yellow fsize b");
				});
				//鼠标移出则删除类
				$('#d').mouseout(function(){
					$('#d').removeClass("yellow fsize b");
				});
			})
		</script>
	</head>
	<body>
		<div id="d">
			div
		</div>
	</body>
</html>

```



```javascript
$('#button').click(function(){
	if(!$('#d').hasClass('yellow')){
		$('#d').addClass('yellow');
	}else{
		$('#d').removeClass('yellow');
	}
})

```

