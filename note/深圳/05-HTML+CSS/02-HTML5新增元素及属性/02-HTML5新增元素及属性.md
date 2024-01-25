



1.HTML5新增元素及属性

1.HTML5是用于取代HTML和XHTML的标准版本

新的语义化标签，比如 header、footer、nav

新的表单控件，比如 email、url、search

用于绘画的 canvas 元素

用于媒介回放的 video 和 audio 元素

2.新增结构元素

|  header | 页面或页面中某一个区块的页眉，通常是一些引导和导航信息 |
| - | - |
| nav  | 可以作为页面导航的链接组 |
| section | 页面中的一个内容区块，通常由内容及其标题组成 |
| article | 代表一个独立的、完整的相关内容块，可独立于页面其它内容使用 |
| aside | 非正文的内容，与页面的主要内容是分开的，被删除而不会影响到网页的内容 |
| footer | 页面或页面中某一个区块的脚注 |


结构元素起语义化作用

```javascript
<html>
	<head>
		<meta charset="UTF-8">
		<title></title>
		<style type="text/css">
			header,section,footer{
				height:200px;
				border: 1px red solid;
			}
		</style>
	</head>
	<body>
			<header>
        <h2>头部</h2>
			</header>
			<section>
				<h2>主体</h2>
			</section>
			<footer>
				<h2>底部</h2>
			</footer>
	</body>
</html>
```

3.新增网页元素

| audio | 定义音频，如音乐或其他音频流 |
| - | - |
| video | 定义视频，如电影片段或其他视频流 |
| canvas | 定义图形 |
| datalist | 定义可选数据的列表 |
| time | 标签定义日期或时间 |
| mark | 在视觉上向用户呈现那些需要突出的文字 |


canvas定义画布，使用js绘制。

4.新增属性

| contentEditable | 规定是否允许用户编辑内容 |
| - | - |
| designMode | 规定整个页面是否可编辑 |
| hidden | 规定对元素进行隐藏 |
| spellcheck | 规定是否必须对元素进行拼写或语法检查 |
| time | 标签定义日期或时间 |
| tabindex | 规定元素的tab键迭制次序 |




2.音视频播放

1.音频

| controls | controls | 如果出现该属性，则向用户显示控件，比如播放按钮 |
| - | - | - |
| autoplay | autoplay | 如果出现该属性，则音频在就绪后马上播放 |
| loop | loop | 如果出现该属性，则当音频结束时重新开始播放 |
| preload | auto/meta/none | 如果出现该属性，则音频在页面加载时进行加载，并预备播放 |




```javascript
<html>
	<head>
		<meta charset="UTF-8">
		<title></title>
	</head>
	<body>
		<audio controls="controls" autoplay="autoplay">
			<source src="a.mp4" type="audio/mp3"></source>
		</audio>
	</body>
</html>
```

2.视频

| controls | controls | 如果出现该属性，则向用户显示控件，比如播放按钮 |
| - | - | - |
| autoplay | autoplay | 如果出现该属性，则视频在就绪后马上播放 |
| loop | loop | 如果出现该属性，则当视频结束时重新开始播放 |
| preload | auto | 如果出现该属性，则视频在页面加载时进行加载，并预备播放 |
| width/height | length(px) | 设置视频播放器的宽度/高度 |


高版本浏览器需要静音muted="muted"才能自动播放

放多个source标签会播放支持的第一个source

```javascript
<html>
	<head>
		<meta charset="UTF-8">
		<title></title>
	</head>
	<body>
		
		<video width="400" height="250" controls="controls" autoplay="autoplay" muted="muted">
			<source src="a.mp4" type="video/mp4"></source>
			<source src="a2.mp4" type="video/mp4"></source>
		</video>
		

	</body>
</html>

```



3.表单

1.新增input类型

```javascript
<form action="xxx" method="get">
	<!-- 邮箱输入框 -->
	<p>邮箱:<input type="email"  name="email"/></p>
	<!-- 网址输入框，必须包含http协议 -->
	<p>请输入你的网址:<input type="url"  name="userUrl"/></p>
	<!-- 取色器 -->
	<p><input type="color"> </p>
	<!-- 搜索框 -->
	<p><input type="search"></p>
	<!-- 限制输入数字 -->
	<input type="number" name= "num" min="0" max="100" step="3" value="3"/>
	<!-- 滑动条 -->
	<input type="range" name= "point" min="2" max="15" step="1" value="3"/>
	<!-- 日期输入框 -->
	<p><input type="date"> </p>
	<!-- 时间输入框 -->
	<p><input type="time"> </p>
	<input type="submit" value="提交"></p>

</form>

```

2.初级验证

```javascript
<form action="xxx" method="get">
	<!-- 输入框提示 -->
	姓名：<input type="text" name="name" placeholder="请输入姓名" />
	<!-- required属性，不能为空 -->
	<br>邮箱：<input type="email" name="email" required="required" />
	<!-- pattern属性，根据正则表达式检查输入内容 -->
	<br>手机号：<input type="text" name="phone" pattern="1[3578]\d{9}" />
	<br><input type="submit" />
</form>

```

