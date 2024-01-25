window

1、常用属性

| history  | 有关客户访问过的URL的信息 |
| - | - |
| location | 有关当前 URL 的信息 |




2、常用方法

| prompt( ) | 显示可提示用户输入的对话框 |
| - | - |
| alert( ) | 显示带有一个提示信息和一个确定按钮的警示框  |
| confirm( ) | 显示一个带有提示信息、确定和取消按钮的对话框， |
| close( ) | 关闭浏览器窗口 |
| open( ) | 打开一个新的浏览器窗口，加载给定 URL 所指定的文档 |
| setTimeout( ) | 在指定的毫秒数后调用函数或计算表达式 |
| setInterval( ) | 按照指定的周期（以毫秒计）来调用函数或表达式 |


prompt('提示内容','输入框默认内容')，无输入则返回空串

confirm( )返回布尔值



setTimeout(函数，毫秒)

clearTimeout(setTimeOut()返回的ID值)



setInterval(函数，毫秒)

clearInterval(setInterval()返回的ID值)



3.open()

open(url,窗口名,窗口参数)

| height、width | 窗口文档显示区的高度、宽度。以像素计 |
| - | - |
| left、top | 窗口的x坐标、y坐标。以像素计 |
| toolbar=yes | no  |1 | 0 | 是否显示浏览器的工具栏。黙认是yes |
| scrollbars=yes | no  |1 | 0 | 是否显示滚动条。黙认是yes |
| location=yes | no  |1 | 0 | 是否显示地址地段。黙认是yes |
| status=yes | no  |1 | 0 | 是否添加状态栏。黙认是yes |
| menubar=yes | no  |1 | 0 | 是否显示菜单栏。黙认是yes |
| resizable=yes | no  |1 | 0 | 窗口是否可调节尺寸。黙认是yes |
| titlebar=yes | no  |1 | 0 | 是否显示标题栏。黙认是yes |
| fullscreen=yes | no  |1 | 0 | 是否使用全屏模式显示浏览器。黙认是no。处于全屏模式的窗口必须同时处于剧院模式 |




```javascript
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title></title>
	</head>
	<body>
		<input type="button" value="打开空白窗口" onclick="blank()" />
		<input type="button" value="打开100x100窗口" onclick="wh()" />
		<script type="text/javascript">
			//打开了标签页
			function blank(){
				open("",'空白');
			}
			//指定replace参数，打开了窗口
			function wh(){
				open('','','width=100,height=100');
			}
		</script>
	</body>
</html>
```



history

常用方法

| back() | 加载 history 对象列表中的前一个URL |
| - | - |
| forward() | 加载 history 对象列表中的下一个URL  |
| go() | 加载 history 对象列表中的某个具体URL |


back()等同于go(-1)

forward()等同于go(1)

```javascript
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title></title>
	</head>
	<body>
		<input type="button" value="后退" onclick="back()" />
		<input type="button" value="前进" onclick="forward()" />
		<script type="text/javascript">
			//后退
			function back(){
				//history.go(-1);
				history.back();
			}
			//前进
			function forward(){
				//history.go(1);
				history.forward();
			}
		</script>
	</body>
</html>

```



location

1.常用属性

| host | 设置或返回主机名和当前URL的端口号 |
| - | - |
| hostname | 设置或返回当前URL的主机名 |
| href | 设置或返回完整的URL |




2.常用方法

| reload() | 重新加载当前文档 |
| - | - |
| replace() | 用新的文档替换当前文档 |


reload()相当于刷新

replace()没有历史记录，无法后退

```javascript
<html>
	<head>
		<meta charset="UTF-8">
		<title></title>
	</head>
	<body>
		<input type="button"  value="刷新" onclick="rl()" />
		<input type="button"  value="replace" onclick="rp()" />
		<script type="text/javascript">
			function rl(){
				location.reload();
			}
			function rp(){
				location.replace("http:/www.baidu.com");
			}
		</script>
	</body>
</html>
```

