1.基本语法

```javascript
<script src="https://cdn.staticfile.org/jquery/3.5.1/jquery.min.js"></script>
<script>
     $(document).ready(function() {
        alert("我欲奔赴沙场征战jQuery，势必攻克之！");
    });
    //简写
    $(function() {
        alert("我欲奔赴沙场征战jQuery，势必攻克之！");
    });
</script>
```

window.onload，需要等待静态资源也加载完成，多次执行赋值会覆盖

 $(document).ready()，文档结构加载完成即可，可以多次执行



2.基本选择器

| 标签选择器 | element | 根据给定的标签名匹配元素 | $("h2" )选取所有h2元素 |
| - | - | - | - |
| 类选择器 | .class | 根据给定的class匹配元素 | $(" .title")选取所有class为title的元素 |
| ID选择器 | \#id | 根据给定的id匹配元素 | $(" \#title")选取id为title的元素 |
| 并集选择器 | selector1,selector2,...,selectorN | 将每一个选择器匹配的元素合并后一起返回 | $("div,p,.title" )选取所有div、p和拥有class为title的元素 |
| 全局选择器 | \* | 匹配所有元素 | $("\*" )选取所有元素 |




```javascript
<!DOCTYPE html>
<html>
	<head>
		<meta charset="{CHARSET}">
		<title></title>
		<script src="jquery.min.js" type="text/javascript"></script>
		<script type="text/javascript">
			$(document).ready(function(){
				//选择所有标签
				$("*").css('color','red');
			})
		</script>
	</head>
	<body>
		<h1>选择所有标签</h1>
		<h2>h2</h2>
		<h1>h1</h1>
		<p>p</p>
		<h2>h2</h2>
		<h1>h1</h1>
		<p>p</p>
	</body>
</html>
```



```javascript
<!DOCTYPE html>
<html>
	<head>
		<meta charset="{CHARSET}">
		<title></title>
		<script src="jquery.min.js" type="text/javascript"></script>
		<script type="text/javascript">
			$(document).ready(function(){
				//选择所有h1,h2标签
				$("h1,h2").css('color','red');
			})
		</script>
	</head>
	<body>
		<h1>选择所有h1,h2标签</h1>
		<h2>h2</h2>
		<h1>h1</h1>
		<p>p</p>
		<h2>h2</h2>
		<h1>h1</h1>
		<p>p</p>
	</body>
</html>

```





3.层次选择器

| 后代选择器 | ancestor descendant | 选取ancestor元素里的所有descendant（后代）元素 | $("\#menu span" )选取\#menu下的&lt;span&gt;元素 |
| - | - | - | - |
| 子选择器 | parent&gt;child | 选取parent元素下的child（子）元素 | $(" \#menu&gt;span" )选取\#menu的子元素&lt;span&gt; |
| 相邻元素选择器 | prev+next | 选取紧邻prev元素之后的next元素 | $(" h2+dl " )选取紧邻&lt;h2&gt;元素之后的同辈元素&lt;dl&gt; |
| 同辈元素选择器 | prev~sibings | 选取prev元素之后的所有siblings元素 | $(" h2~dl " )选取&lt;h2&gt;元素之后所有的同辈元素&lt;dl&gt; |




```javascript
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title></title>
		<script src="jquery.min.js" type="text/javascript"></script>
		<script type="text/javascript">
			$(document).ready(function(){
				$("p~h1").css('color','red');
			})
		</script>
	</head>
	<body>
		<h1>1111</h1>
		<h1>1111</h1>
		<h1>1111</h1>
		<p>pppppp</p>
		<h1>1111</h1>
		<h1>1111</h1>
		<h1>1111</h1>
		<h1>1111</h1>
	</body>
</html>

```



```javascript
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title></title>
		<script src="jquery.min.js" type="text/javascript"></script>
		<script type="text/javascript">
			$(document).ready(function(){
				$("p+h1").css('color','red');
			})
		</script>
	</head>
	<body>
		<h1>1111</h1>
		<h1>1111</h1>
		<h1>1111</h1>
		<p>pppppp</p>
		<h1>1111</h1>
		<h1>1111</h1>
		<h1>1111</h1>
		<h1>1111</h1>
	</body>
</html>

```



4.属性选择器

| [attribute^=value] | 选取给定属性是以某些特定值开始的元素 | $(" [href^='en']" )选取href属性值以en开头的元素 |
| - | - | - |
| [attribute$=value] | 选取给定属性是以某些特定值结尾的元素 | $(" [href$='.jpg']" )选取href属性值以.jpg结尾的元素 |
| [attribute\*=value] | 选取给定属性是以包含某些值的元素 | $(" [href\* ='txt']" )选取href属性值中含有txt的元素 |


[attr]有attr属性的

[xxxx][xxxx]属性符合多个条件



```javascript
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title></title>
		<script src="jquery.min.js" type="text/javascript"></script>
		<script type="text/javascript">
			$(document).ready(function(){
				$("h2[name]").css('color','red');
				$('h3[name="a"]').css('color','red');
				$('h4[name^="aa"]').css('color','red');
				$('h5[name$="aa"]').css('color','red');
				$('h6[name*="aa"]').css('color','red');
			})
		</script>
	</head>
	<body>
		<h1>--选择有name属性的h2元素</h1>
		<h2>无name属性</h2>
		<h2 name="aaa">有name属性</h2>
		<h2 name="aaa">有name属性</h2>
		
		<h1>--选择有name属性=a的h3元素</h1>
		<h3 name="a">name属性=a</h3>
		<h3 name="b">name属性=b</h3>
		<h3 name="a">name属性=a</h3>
		
		
		<h1>--选择name属性开头为aa的h4元素</h1>
		<h4 name="aaaabb">name=aaaabb</h4>
		<h4 name="bbaa">name=bbaa</h4>
		<h4 name="aa">name=aa</h4>
		
		
		<h1>--选择name属性结尾为aa的h5元素</h1>
		<h5 name="bbaa">name=bbaa</h5>
		<h5 name="aaaabb">name=aaaabb</h5>
		<h5 name="aa">name=aa</h5>
		
		<h1>--选择name属性包含为aa的h6元素</h1>
		<h6 name="xxxxaa">name=xxxxaa</h6>
		<h6 name="aa">name=aa</h6>
		<h6 name="aasefesf">name=aasefesf</h6>
		<h6 name="sefsefaasefse">name=sefsefaasefse</h6>
		<h6 name="fsesef">name=fsesef</h6>
	</body>
</html>

```



5.基本过滤选择器

| :eq(index) | 选取索引等于index的元素（index从0开始） | $("li:eq(1)" )选取索引等于1的&lt;li&gt;元素 |
| - | - | - |
| :gt(index) | 选取索引大于index的元素（index从0开始） | $(" li:gt(1)" )选取索引大于1的&lt;li&gt;元素（注：大于1，不包括1） |
| :lt(index) | 选取索引小于index的元素（index从0开始） | $(&quot;li:lt(1)” )选取索引小于1的&lt;li&gt;元素（注：小于1，不包括1） |
| :header | 选取所有标题元素，如h1~h6 | $(":header" )选取网页中所有标题元素 |
| :focus | 选取当前获取焦点的元素 | $(":focus" )选取当前获取焦点的元素 |
| :animated | 选择所有动画 | $(":animated" )选取当前所有动画元素 |
| :first | 选取第一个元素 | $(" li:first" )选取所有&lt;li&gt;元素中的第一个&lt;li&gt;元素 |
| :last | 选取最后一个元素 | $(" li:last" )选取所有&lt;li&gt;元素中的最后一个&lt;li&gt;元素 |
| :not(selector) | 选取去除所有与给定选择器匹配的元素 | $(" li:not(.three)" )选取class不是three的元素 |
| :even | 选取索引是偶数的所有元素（index从0开始） | $(" li:even" )选取索引是偶数的所有&lt;li&gt;元素 |
| :odd | 选取索引是奇数的所有元素（index从0开始） | $(" li:odd" )选取索引是奇数的所有&lt;li&gt;元素 |


:input，所有input标签

:text，type为text的input标签

:password，type为password的input标签

使用多个过滤选择器

```javascript
$('.contain tr:not(:first):even').css('background-color','#eff7d1');
```



```javascript
<!DOCTYPE html>
<html>
	<head>
		<meta charset="{CHARSET}">
		<title></title>
		<script src="jquery.min.js" type="text/javascript"></script>
		<script type="text/javascript">
			$(document).ready(function(){
				$("li:eq(5)").css('color','red');
				$("li:gt(7)").css('color','red');
				$("li:lt(2)").css('color','red');
				$("#d1>:header").css('color','red');
			})
		</script>
	</head>
	<body>
		<h1>选择索引等于5、小于2、大于7的li标签，索引从0开始</h1>
		<ul>
			<li>0</li>
			<li>1</li>
			<li>2</li>
			<li>3</li>
			<li>4</li>
			<li>5</li>
			<li>6</li>
			<li>7</li>
			<li>8</li>
		</ul>
		<h1>选择div下所有标题标签</h1>
		<div id="d1">
			<h1>h1</h1>
			<h1>h1</h1>
			<h2>h2</h2>
			<p>p</p>
			<h3>h3</h3>
			<span>
				span
			</span>
		</div>
	</body>
</html>

```



6.可见性过滤选择器

| :visible | 选取所有可见的元素 | $(":visible" )选取所有可见的元素 |
| - | - | - |
| :hidden | 选取所有隐藏的元素 | $(":hidden" ) 选取所有隐藏的元素 |




```javascript
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title></title>
		<script src="jquery.min.js" type="text/javascript"></script>
		<script type="text/javascript">
			$(document).ready(function(){
				$("#hide").click(function(){
					$("h1:visible").hide();
				});
				$("#show").click(function(){
					$("h1:hidden").show();
				});
			})
		</script>
	</head>
	<body>
		<input id="show" type="button" value="显示"/>
		<input id="hide" type="button" value="隐藏"/>
		<h1>显示内容</h1>
		<h1>显示内容</h1>
		<h1>显示内容</h1>
		<h1>显示内容</h1>
	</body>
</html>

```

