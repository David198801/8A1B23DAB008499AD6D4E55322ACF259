1.操作样式

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



2.操作元素HTML、Text、value属性

| html( ) | 无参数 | 用于获取第一个匹配元素的HTML内容或文本内容 |
| - | - | - |
| html(content) | content为元素的HTML内容 | 用于设置所有匹配元素的HTML内容或文本内容 |
| text( ) | 无参数 | 用于获取所有匹配元素的文本内容 |
| text (content) | content为元素的文本内容 | 用于设置所有匹配元素的文本内容 |




html()，相当于innerHTML，不传参获取，传参设置

text()，相当于innerText，不传参获取，传参设置

val()，操作value属性

```javascript
$('#get').click(function(){
	alert('内容为：'+$('#text').val());
})
$('#set').click(function(){
	$('#text').val(prompt('请输入内容'));
})

```



```javascript
$('#d').attr("name");
$('#d').attr("name",'aaa');
```



3.操作元素 创建 插入 删除 替换 克隆

1.创建元素

```javascript
$('<p></p>');
$('<p>');
$('<p>顶顶顶顶<span>啊啊啊啊</span>顶顶顶顶</p>');
```

2.插入元素

| append(content) | $(A).append(B)表示将B追加到A中 如：$("ul").append($newNode1); |
| - | - |
| appendTo(content) | $(A).appendTo(B)表示把A追加到B中 如：$newNode1.appendTo("ul"); |
| prepend(content) | $(A). prepend (B)表示将B前置插入到A中 如：$("ul"). prepend ($newNode1); |
| prependTo(content) | $(A). prependTo (B)表示将A前置插入到B中 如：$newNode1. prependTo ("ul"); |




```javascript
<!DOCTYPE html>
<html>
	<head>
		<meta charset="{CHARSET}">
		<title></title>
		<script type="text/javascript" src="jquery-1.12.4.js"></script>
		<script type="text/javascript">
			$(function(){
				
				
				$('body').append($('<p>新元素1</p>'));
				$('body').prepend($('<p>新元素2</p>'));
				$('<p>新元素3</p>').appendTo($('#d'));
				$('<p>新元素4</p>').prependTo($('#d'));
				
			})
		</script>
	</head>
	<body>
		<div id="d">div</div>
	</body>
</html>

```



| after(content) | $(A).after (B)表示将B插入到A之后 如：$("ul").after($newNode1); |
| - | - |
| insertAfter(content) | $(A). insertAfter (B)表示将A插入到B之后 如：$newNode1.insertAfter("ul"); |
| before(content) | $(A). before (B)表示将B插入至A之前 如：$("ul").before($newNode1); |
| insertBefore(content) | $(A). insertBefore (B)表示将A插入到B之前 如：$newNode1.insertBefore("ul"); |




```javascript
<!DOCTYPE html>
<html>
	<head>
		<meta charset="{CHARSET}">
		<title></title>
		<script type="text/javascript" src="jquery-1.12.4.js"></script>
		<script type="text/javascript">
			$(function(){
				
				$('#d1').after($('<p>新元素1</p>'));
				$('#d1').before($('<p>新元素2</p>'));
				
				$('<p>新元素1</p>').insertAfter($('#d2'));
				$('<p>新元素2</p>').insertBefore($('#d2'));
			
				
			})
		</script>
	</head>
	<body>
		<div id="d1">原有元素1</div>
		<hr />
		<div id="d2">原有元素2</div>
	</body>
</html>
```





3.删除节点

remove()：删除整个节点

empty()：清空节点内容

detach()：删除整个节点，保留元素的绑定事件、附加的数据

```javascript
<!DOCTYPE html>
<html>
	<head>
		<meta charset="{CHARSET}">
		<title></title>
		<script type="text/javascript" src="jquery-1.12.4.js"></script>
		<script type="text/javascript">
			$(function(){
				
				var d1 = $('#d1').remove();

				$('#d2').empty();
				
				$('div').click(function(){
					alert(111);
				});
				
				//保留了事件，恢复后可以调用
				var d3 = $('#d3').detach();
				$('body').append(d1);
				$('body').append(d3);
				
				
			})
		</script>
	</head>
	<body>
		<div id="d1">div1<p>sefsefsefs</p><span>sefsefe</span></div>
		<div id="d2">div2<p>sefsefsefs</p><span>sefsefe</span></div>
		<div id="d3">div3<p>sefsefsefs</p><span>sefsefe</span></div>
	</body>
</html>

```

4.替换节点

replaceWith()

replaceAll()

```javascript
<!DOCTYPE html>
<html>
	<head>
		<meta charset="{CHARSET}">
		<title></title>
		<script type="text/javascript" src="jquery-1.12.4.js"></script>
		<script type="text/javascript">
			$(function(){
				
				$('#p').replaceWith($('<h1>替换的标签</h1>'));
			
				$('<h1>替换的标签</h1>').replaceAll('div');
			})
		</script>
	</head>
	<body>
		<p id="p">p</p>
		<div id="d1">div1</div>
		<div id="d2">div2</div>
	</body>
</html>

```

5.复制节点

clone()，传入rue或false，是否保留事件

```javascript
<!DOCTYPE html>
<html>
	<head>
		<meta charset="{CHARSET}">
		<title></title>
		<script type="text/javascript" src="jquery-1.12.4.js"></script>
		<script type="text/javascript">
			$(function(){
				$('#d').click(function(){
					alert(11111);
				});
				
				var c = $('#d').clone();
				$('body').append(c);
				$('body').append($('#d').clone(true));
			
				
			})
		</script>
	</head>
	<body>
		<div id="d">原有元素</div>
	</body>
</html>

```



4.操作属性

attr()，设置/获取

```javascript
$('#d').html("aaa<br />bbb");
$('#d').text("aaabbb");
```

removeAttr()，移除



5.遍历元素

