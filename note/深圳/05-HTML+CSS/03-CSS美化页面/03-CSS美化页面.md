1.CSS

Cascading Style Sheet  级联样式表

表现HTML或XHTML文件样式的计算机语言

包括对字体、颜色、边距、高度、宽度、背景图片、网页定位等设定

语法：

选择器{css语句;}



2.引入CSS

1.行内样式

```javascript
<h1 style="color:red;">style属性的应用</h1>
```

2.内部样式

```javascript
<style>
        h1{color: green; }
</style>
```

3.外部样式

链接式

```javascript
<link href="style.css" rel="stylesheet" type="text/css" />
```

引入式(不常用)

```javascript
<style>
        @import url("style.css");
</style>
```

优先级：就近原则，行内>内部>外部

```javascript
<html>
	<head>
		<meta charset="UTF-8">
		<title></title>
		<!--外部-->
		<link rel="stylesheet" type="text/css" href="css/style.css"/>
		<!--内部-->
		<style type="text/css">
			h1{
				color: yellow;
			}
		</style>
	</head>
	<body>
		<!--行内-->
		<h1 style="color: red;">标题</h1>
		<p>段落段落</p>
		<p>段落段落</p>
		<p>段落段落</p>
	</body>
</html>
```



3.基本选择器

1.标签

2.id

3.class

```javascript
<html>
	<head>
		<meta charset="UTF-8">
		<title></title>
		<style type="text/css">
			/*标签选择器*/
			h1{
				color: red;
			}
			/*id选择器*/
			#pid{
				color: #66ccff;
			}
			/*类选择器*/
			.c2{
				color: aqua;
			}
		</style>
	</head>
	<body>
		<h1>h1h1h1</h1>
		<h1>h1h1</h1>
		<h2 class="c2">h2c2</h2>
		<h3 class="c2">h3c2</h3>
		<p id="pid">ppppppppid</p>
	</body>
</html>
```



4.层次选择器

| E F | 后代选择器 |
| - | - |
| E&gt;F | 子选择器 |
| E+F | 相邻兄弟选择器 |
| E~F | 通用兄弟选择器 |




```javascript
<html>
	<head>
		<meta charset="UTF-8">
		<title></title>
		<style type="text/css">
			/*后代选择器，选择div1后代中所有p元素*/
			#div1 p{
				color: red;
			}
			
			/*子代选择器，选择div2子代中的p元素*/
			#div2>p{
				color: red;
			}
			
			/*相邻兄弟选择器，选择h2后面紧挨的一个h3*/
			h2+h3{
				color: red;
			}
			
			/*~选择器，选择h4后面所有的兄弟h5*/
			h4~h5{
				color: red;
			}
		</style>
	</head>
	<body>
		<h1>后代选择器，选择div1后代中所有p元素</h1>
		<div id="div1">
			<p>
				div1-p,<span>div-p-span,</span>div1-p
				<section>
					<p>div-section-p</p>
				</section>
			</p>
			<span>div-span</span>
		</div>
		
		<h1>子代选择器，选择div2子代中的p元素</h1>
		<div id="div2">
			<p>
				div1-p,<span>div-p-span,</span>div1-p
				<section>
					<p>div-section-p</p>
				</section>
			</p>
		</div>
		
		<h1>相邻兄弟选择器，选择h2后面紧挨的一个兄弟h3</h1>
		<h3>h3</h3>
		<h2>h2</h2>
		<h3>h3</h3>
		<h3>h3</h3>
		<h2>h2</h2>
		<p>p<h3>p-h3</h3></p>
		<h3>h3</h3>
		
		<h1>~选择器，选择h4后面所有的h5</h1>
		<h5>h5</h5>
		<h4>h4</h4>
		<p>p<h5>p-h5</h5></p>
		<h5>h5</h5>
		<h5>h5</h5>
		<h5>h5</h5>
		
	</body>
</html>
```



5.属性选择器

| E[attr] | a[id] { background: yellow;} |
| - | - |
| E[attr=val] | a[id=first] { background: red;} |
| E[attr^=val] | a[href^=http] { background: red;} |
| E[attr$=val] | a[href$=png] { background: red;}              |
| E[attr\*=val] | a[class\*=links] { background: red;} |




```javascript
<html>
	<head>
		<meta charset="UTF-8">
		<title></title>
		<style type="text/css">
			h2[name]{
				color: red;
			}
			h3[name="a"]{
				color: red;
			}
			h4[name^="aa"]{
				color: red;
			}
			h5[name$="aa"]{
				color: red;
			}
			h6[name*="aa"]{
				color: red;
			}
		</style>
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



6.字体样式

| font-family | 设置字体类型 | font-family:"隶书"; |
| - | - | - |
| font-size | 设置字体大小 | font-size:12px; |
| font-style | 设置字体风格 | font-style:italic; |
| font-weight | 设置字体的粗细 | font-weight:bold; |
| font | 在一个声明中设置所有字体属性 | font:italic bold 36px "宋体"; |


```javascript
<style type="text/css">
	body {
		font-family: Times, "Times New Roman", "楷体";
	}
	
	h1 {
		font-size: 24px;
		font-style: italic;
	}
	
	h1 span {
		font-style: oblique;
		font-weight: normal;
	}
	
	h2 {
		font-size: 16px;
		font-style: normal;
	}
	
	p {
		font-size: 12px;
	}
	
	p span {
		font-weight: bold;
	}
	
	p span {
		font: oblique bold 12px "楷体";
	}
</style>
```



[03-CSS美化页面.zip](assets/03-CSS美化页面.zip)

7.文本样式

| color | 设置文本颜色 | color:\#00C; |
| - | - | - |
| text-align | 设置元素水平对齐方式 | text-align:right; |
| text-indent | 设置首行文本的缩进 | text-indent:20px; |
| line-height | 设置文本的行高 | line-height:25px; |
| text-decoration | 设置文本的装饰 | text-decoration:underline; |


1.水平对齐方式

| left | 把文本排列到左边。默认值：由浏览器决定 |
| - | - |
| right | 把文本排列到右边 |
| center | 把文本排列到中间 |
| justify | 实现两端对齐文本效果 |


2.缩进

text-indent：em或px

3.行高px

4.装饰

| none | 默认值，定义的标准文本 |
| - | - |
| underline | 设置文本的下划线 |
| overline | 设置文本的上划线 |
| line-through | 设置文本的删除线 |




8.超链接伪类

| a:link | 未单击访问时超链接样式 | a:link{color:\#9ef5f9;} |
| - | - | - |
| a:visited | 单击访问后超链接样式 | a:visited {color:\#333;} |
| a:hover | 鼠标悬浮其上的超链接样式 | a:hover{color:\#ff7300;} |
| a:active | 鼠标单击未释放的超链接样式 | a:active {color:\#999;} |


