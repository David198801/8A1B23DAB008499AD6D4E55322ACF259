引入CSS

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

