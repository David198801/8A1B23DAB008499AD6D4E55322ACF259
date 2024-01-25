

```javascript
$(document).ready(function(){
	alert('hello world');
})
```

简写

```javascript
$(function(){
	alert('hello world');
})
```





window.onload与ready区别

| <br>window.onload() | $(document).ready() | 加载时机 |
| - | - | - |
|   必须等待网页全部加载完毕（包括图片等），然后再执行JS代码 | 只需要等待网页中的DOM结构加载完毕，就能执行JS代码 | 执行次数 |
| 只能执行一次，如果第二次，那么第一次的执行会被覆盖 | 可以执行多次，第N次都不会被上一次覆盖 | 举例 |
| 以下代码无法正确执行：  <br>window.onload = function() { alert(&quot;text1”);};<br>window.onload = function() { alert(&quot;text2”);}; <br>结果只输出第二个 | 以下代码正确执行： <br>$(document).ready(function(){alert(&quot;Hello”)});<br>$(document).ready(function(){alert(&quot;Hello”)});<br>结果两次都输出 | 简写方案 |




| window.onload | $(document).ready() | 执行时机 |
| - | - | - |
| 必须等待网页中所有的内容加载完毕后（包括图片、flash、视频等）才能执行 | 网页中所有DOM文档结构绘制完毕后即刻执行，可能与DOM元素关联的内容（图片、flash、视频等）并没有加载完 | 编写个数 |
| 同一页面不能同时编写多个 | 同一页面能同时编写多个 | 简化写法 |




原生js实现

```javascript
(function() {
    var ie = !!(window.attachEvent && !window.opera);
    var wk = /webkit\/(\d+)/i.test(navigator.userAgent) && (RegExp.$1 < 525);
    var fn = [];
    var run = function() {
        for (var i = 0; i < fn.length; i++) {
            fn[i]()
        }
    };
    var d = document;
    d.ready = function(f) {
        if (!ie && !wk && d.addEventListener) {
            return d.addEventListener("DOMContentLoaded", f, false)
        }
        if (fn.push(f) > 1) {
            return
        }
        if (ie) { (function() {
                try {
                    d.documentElement.doScroll("left");
                    run()
                } catch(err) {
                    setTimeout(arguments.callee, 0)
                }
            })()
        } else {
            if (wk) {
                var t = setInterval(function() {
                    if (/^(loaded|complete)$/.test(d.readyState)) {
                        clearInterval(t),
                        run()
                    }
                },
                0)
            }
        }
    }
})();
```

压缩

```javascript
(function(){var ie=!!(window.attachEvent&&!window.opera);var wk=/webkit\/(\d+)/i.test(navigator.userAgent)&&(RegExp.$1<525);var fn=[];var run=function(){for(var i=0;i<fn.length;i++){fn[i]()}};var d=document;d.ready=function(f){if(!ie&&!wk&&d.addEventListener){return d.addEventListener("DOMContentLoaded",f,false)}if(fn.push(f)>1){return}if(ie){(function(){try{d.documentElement.doScroll("left");run()}catch(err){setTimeout(arguments.callee,0)}})()}else{if(wk){var t=setInterval(function(){if(/^(loaded|complete)$/.test(d.readyState)){clearInterval(t),run()}},0)}}}})();
```

使用

```javascript
document.ready(function() {
    document.getElementById("test").innerHTML = "document.ready test!"
});
```

