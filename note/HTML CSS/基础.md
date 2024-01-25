1.基本结构

```javascript
<html>
	<head>
	</head>
	<body>
	</body>
</html>
```



2.基本标签

1.标题标签h1~h6

字体 加粗strong,斜体em

```javascript
<h1>一级标题</h1>
<h2>二级标题</h2>
```

2.段落标签

```javascript
<p>段落</p>
```

3.换行标签

```javascript
<br/>
```

4.水平线标签

```javascript
<hr/>
```

5.字体样式标签

```javascript
<!-- 加粗 -->
<strong></strong>
<!-- 斜体 -->
<em></em>
```

6.注释和特殊符号

```javascript
<!-- 注释内容 -->

<!-- 特殊符号 -->
<!-- 不换行空格 --> &nbsp;
<!-- 半角空格 --> &ensp;
<!-- 全角空格 --> &emsp;
<!-- 大于号 --> &gt;
<!-- 小于号 --> &lt;
<!-- 引号 --> &quoe;
<!-- 版权符号 --> &copy;
```



3.img标签

属性

src

alt 图片加载错误时显示的内容

title 鼠标悬停说明

width 宽度 200px 50%

height 高度

```javascript
<img src="a.png" alt="图片加载失败" title="鼠标悬停文字" />
```



4.a标签

属性

href

target _self 当前页面打开（默认） _blank 新空白页打开



锚链接

<a href="#anchor1">锚点1</a>

<a name="anchor2">锚点1</a>

```javascript
<a href="http://www.baidu.com" target="_blank">超链接</a>

<a name="anchor">锚点</a>
<a href="test.html#anchor">锚链接</a>
```



5.列表

无序表

```javascript
<ul>
			<li>无序列表行1</li>
			<li>无序列表行2</li>
			<li>无序列表行3</li>
</ul>
```



有序表

```javascript
<ol>
			<li>有序列表行1</li>
			<li>有序列表行2</li>
			<li>有序列表行3</li>
</ol>
```



定义列表

```javascript
<dl>
			<dt>水果</dt>
			<dd>苹果</dd>
			<dd>梨子</dd>
			<dd>西瓜</dd>
			<dt>蔬菜</dt>
			<dd>白菜</dd>
			<dd>生菜</dd>
			<dd>豇豆</dd>
</dl>
```

