1.事件和分类

jQuery事件是对JavaScript事件的封装，常用事件分类

基础事件

鼠标事件

键盘事件

window事件

表单事件

复合事件

鼠标光标悬停

鼠标连续点击



2.鼠标事件

| 方法 | 描述 | 执行时机 |
| - | - | - |
| click( ) | 触发或将函数绑定到指定元素的click事件 | 单击鼠标时 |
| mouseover( ) | 触发或将函数绑定到指定元素的mouseover事件 | 鼠标指针移过时 |
| mouseout( ) | 触发或将函数绑定到指定元素的mouseout事件 | 鼠标指针移出时 |
| mouseenter( ) | 触发或将函数绑定到指定元素的mouseenter事件 | 鼠标指针进入时 |
| mouseleave( ) | 触发或将函数绑定到指定元素的mouseleave事件 | 鼠标指针离开时 |


over/out和enter/leave区别：在绑定元素内部子元素中经过时over/out会触发，enter/leave不会触发，使用enter/leave

```javascript
$(function(){
	$(".nav-ul a").mouseover(function(){
		$(this).css("background-color","#f01e28");
	});
	$(".nav-ul a").mouseout(function(){
		$(this).css("background-color","#ff2832");
	});

})
```



3.键盘事件

| 方法 | 描述 | 执行时机 |
| - | - | - |
| keydown( ) | 触发或将函数绑定到指定元素的keydown事件 | 按下键盘时 |
| keyup( ) | 触发或将函数绑定到指定元素的keyup事件 | 释放按键时 |
| keypress( ) | 触发或将函数绑定到指定元素的keypress事件 | 产生可打印的字符时 |




```javascript
$(function(){
	$("[type=password]").keyup(function () {
			$("#events").append("keyup,");
	}).keydown(function (e) {
		$("#events").append("keydown,");
	}).keypress(function () {
		$("#events").append("keypress,");
	});
	$(document).keydown(function (event) {
		if (event.keyCode == "13") {
			alert("确认要提交么？");
		}
	});

		
})
```



4.bind()绑定与unbind()移除事件

bind()

绑定单个

```javascript
$('#foo').bind('click', function() {
  alert(1);
});
```

绑定多个

```javascript
//多个事件同一个函数
$('#foo').bind('mouseenter mouseleave', function() {
  $(this).toggleClass('entered');
});
//多个事件多个函数
$('#foo').bind({
	mouseover:function(){
		
	},
	mouseout:function(){
		
	}
});
```

unbind

不带参数移除所有事件

unbind("click")移除click事件

unbind("click",func)移除click事件的func函数



5.hover() toggle()

hover()

相当于mouseover与mouseout

```javascript
$(function(){
	$(".on").hover(
		function(){
			$(".topDown").show();
		},
		function(){
			$(".topDown").hide();
		}
	)
})
```

toggle()

无参相当于show()和hide()

有参为点击多次在多个函数间切换执行

```javascript
$("input").toggle(
    function(){$("body").css("background","#ff0000");},
    function(){$("body").css("background","#00ff00");},
    function(){$("body").css("background","#0000ff");}
)
```



5.动画效果

show()和hide()传入毫秒，实现缓慢显示/隐藏的动画效果

fadeIn()和fadeOut()，实现淡入淡出效果

slideDown() 可以使元素逐步延伸显示

slideUp()则使元素逐步缩短直至隐藏

