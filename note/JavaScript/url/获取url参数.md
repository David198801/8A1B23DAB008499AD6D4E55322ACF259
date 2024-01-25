获取url参数

简单处理

```javascript
function getQueryVariable(variable)
{
       var query = window.location.search.substring(1);
       var vars = query.split("&");
       for (var i=0;i<vars.length;i++) {
               var pair = vars[i].split("=");
               if(pair[0] == variable){return pair[1];}
       }
       return(false);
}
```



调用 getQueryVariable("id") 返回 1。

调用 getQueryVariable("image") 返回 "awesome.jpg"



正则

```javascript
function getQueryString(name) {
    var reg = new RegExp('(^|&)' + name + '=([^&]*)(&|$)', 'i');
    var r = window.location.search.substr(1).match(reg);
    if (r != null) {
        return unescape(r[2]);
    }
    return null;
}
// 这样调用：
alert(GetQueryString("参数名1"));
alert(GetQueryString("参数名2"));
alert(GetQueryString("参数名3"));
```



jquery插件

https://blog.csdn.net/w995223851/article/details/115404077

https://plugins.jquery.com/query-object/

https://github.com/alrusdi/jquery-plugin-query-object



vue

