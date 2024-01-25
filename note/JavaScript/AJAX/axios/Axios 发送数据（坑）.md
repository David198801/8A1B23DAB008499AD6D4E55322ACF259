四种常见的 POST 提交数据方式 Content-Type

1. application/x-www-form-urlencoded

1. multipart/form-data

1. application/json

1. text/xml

axios 的 GET 和 POST

1. 使用 GET 数据参数集名称为 params

1. 使用 POST 数据参数集名称为 data

axios 跨域需要带上 cookies设置

// 跨域需要带上cookie，需要打开
axios.defaults.withCredentials = true;

axios POST发送 x-www-form-urlencoded

使用 jquery post 发送数据，默认是使用 x-www-form-urlencoded，默认会处理好参数，但是使用 axios 则需要手动处理参数，否则发送失败。原因参考官方文档，axios 默认都是帮将参数转为json，所以发送就出错了，同时文档也给出了解决方案，所以说看文档很重要 ::aru:knife:: 。

By default, axios serializes JavaScript objects to JSON.

To send data in the application/x-www-form-urlencoded format instead,

you can use one of the following options.

在浏览器里面

使用FormData

```javascript
const params = new FormData();
params.append('param1', 'value1');
params.append('param2', 'value2');
axios.post('/foo', params);
```

使用高级 API URLSearchParams 可以帮我们转化参数，但是IE几乎不兼容。

```javascript
const params = new URLSearchParams();
params.append('param1', 'value1');
params.append('param2', 'value2');
axios.post('/foo', params);
```



使用 qs 库

```javascript
const qs = require('qs');
axios.post('/foo', qs.stringify({ 'bar': 123 }));
```

NODE里面

使用 querystring 进行转换

```javascript
const querystring = require('querystring');
axios.post('http://something.com/', querystring.stringify({ foo: 'bar' }));
```

总结

多看文档，多看文档，多看文档 ::aru:cryingface::

参考

https://imququ.com/post/four-ways-to-post-data-in-http.html

https://github.com/axios/axios#browser