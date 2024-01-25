HTML5新增元素及属性

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




