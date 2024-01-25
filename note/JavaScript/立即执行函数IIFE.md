立即执行函数IIFE

```javascript
(function(){
	console.log('do something');
})();
(function(){
	console.log('do something');
}());

//使用参数
(function(arg){
	console.log('do something');
})(arg);

//赋值给变量，则变量储存返回的值
var result = (function () {
    var name = "Barry";
    return name;
})();
// IIFE 执行后返回的结果：
result; // "Barry"
```



http://lipeng1667.github.io/2016/12/20/IIFE-in-js/

https://developer.mozilla.org/zh-CN/docs/Glossary/%E7%AB%8B%E5%8D%B3%E6%89%A7%E8%A1%8C%E5%87%BD%E6%95%B0%E8%A1%A8%E8%BE%BE%E5%BC%8F



惰性载入

https://blog.csdn.net/heuguangxu/article/details/98615385