传入函数，使用结果集中所有元素调用这个函数，并传入index

跳出：

return false，相当于break

return true，相当于continue

```javascript
$("img").each(function(i){
   this.src = "test" + i + ".jpg";
 });
结果:
[ <img src="test0.jpg" />, <img src="test1.jpg" /> ]
```

