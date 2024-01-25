attr()，操作标签的属性

prop()，操作DOM对象的属性，eg：prop('outerHTML')

传入对象操作多个属性

若结果集有多个则返回第一个元素的属性

对于selected、checked、disabled等属性使用prop('selected')可以直接获得true/false，使用attr()可能获得undefined





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





操作class属性

addClass('aaa')，添加类

addClass('aaa bbb')，添加多个类

removeClass()，移除类

addClass('ccc aaa')，移除多个类，顺序不影响

toggleClass()，调用一次执行addClass()，再调用执行removeClass()

hasClass()，判断是否包含类

