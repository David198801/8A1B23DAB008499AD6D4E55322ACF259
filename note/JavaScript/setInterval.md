实现

https://www.cnblogs.com/Smiled/p/8053150.html

https://www.cnblogs.com/shytong/p/5083332.html

https://zhuanlan.zhihu.com/p/73204517

https://blog.csdn.net/dongxingpeng/article/details/79142527

简单例子

```javascript
    setInterval = () =>{
        console.log(1) //使用递归
        setTimeout(setInterval,1000);
    };
    setInterval()
```

尝试

```javascript
<!DOCTYPE html>
<html>
	<head>
		<meta content="text/html; charset=utf-8" />
		<title></title>
	</head>
	<body>
		<input type=button onclick="stop()" value="stop" />
		<script type="text/javascript">
		var flag = true;
		var i=0;
		setInterval = () =>{
			if(flag){
				console.log(i++);
				setTimeout(setInterval,1000);
			}else{
				return;
			}
		};
		setInterval();
		
		function stop(){
			flag=false;
		}
		</script>
	</body>
</html>
```

