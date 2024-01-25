不发送referer

1.

```javascript
function open_without_referrer(link){
    document.body.appendChild(document.createElement('iframe')).src='javascript:"<script>top.location.replace(\''+link+'\')<\/script>"';
}
```

如果如果是新窗口打开,可以使用如下代码:

```javascript
function open_new_window(full_link){ 
    window.open('javascript:window.name;', '<script>location.replace("'+full_link+'")<\/script>');
 }
```





2.基于HTML标准，可以在a标签内使用rel="noreferrer"来达到这一目的。

目前大部分基于Webkit的浏览器已经支持。

3.提供跨浏览器支持的更好的办法是使用一个第三方的库noreferrer.js，它可以自动识别浏览器并选择最优方案。

补充

Opera并没有提供可以实现不发送referrer的方法，noreferrer.js的解决方案是利用google的url中转。在国内的网络环境下，你懂的。。。

可以自己搭建一个跳转的页面，或者用其他站点的url跳转接口。







4.使用 Referer Meta 标签控制 referer

https://developer.mozilla.org/en-US/docs/Web/HTML/Element/meta/name

```javascript
<meta name="referrer" content="never">
```

使用场景

在某些情况下，出于一些原因，网站想要控制页面发送给 server 的 referer 信息的情况下，可以使用这一 referer metadata 参数。

隐私

社交网站一般都会有用户个人页面，这些页面中用户都有可能添加一些外网的链接，而社交网站有可能不希望在用户点击了这些链接的时候，泄露用户页面的 URL ，因为这些 URL 中可能包含一些敏感信息。当然，有些社交网站可能只想在 referer 中提供一个 hostname，而不是完整的 URL 信息。

安全

有些使用了 https 的网站，可能在 URL 中使用一个参数(sid 等)来作为用户身份凭证，而又需要引入其他 https 网站的资源，这种情况下，网站肯定不希望泄露用户的身份凭证信息。

Object-Capability Discipline

有些网站遵循Object-Capability Discipline，而 referer 刚好与这一策略相悖，所以，网站能够控制 refeer 将对 Object-Capability Discipline 很有利。



技术细节

referer 的 metedata 参数可以设置为以下几种类型的值：

never

always

origin

default

如果在文档中插入 meta 标签，并且 name 属性的值为 referer，浏览器客户端将按照如下步骤处理这个标签：



1.如果 meta 标签中没有 content 属性，则终止下面所有操作

2.将 content 的值复制给 referrer-policy ，并转换为小写

3.检查 content 的值是否为上面 list 中的一个，如果不是，则将值置为 default

上述步骤之后，浏览器后续发起 http 请求的时候，会按照 content 的值，做出如下反应(下面 referer-policy 的值即 meta 标签中 content 的值)：



1.如果 referer-policy 的值为never：删除 http head 中的 referer；

2.如果 referer-policy 的值为default：如果当前页面使用的是 https 协议，而正要加载的资源使用的是普通的 http 协议，则将 http header 中的 referer 置为空；

3.如果 referer-policy 的值为 origin：只发送 origin 部分；

4.如果 referer-policy 的值为 always：不改变http header 中的 referer 的值，注意：这种情况下，如果当前页面使用了 https 协议，而要加载的资源使用的是 http 协议，加载资源的请求头中也会携带 referer。

例子

如果页面中包含了如下 meta 标签，所有从当前页面中发起的请求将不会携带 referer：

```javascript
<meta name="referrer" content="never">
```

如果页面中包含了如下 meta 标签，则从当前页面中发起的 http请求将只携带 origin 部分（注：根据原文中的语境，我理解这里的 origin 是包含了 schema 和 hostname 的部分 url，不包含 path 等后面的其他 url 部分），而不是完整的 URL ：

```javascript
<meta name="referrer" content="origin">
```

注意：在使用本文中所述的 meta 标签的时候，浏览器原有的 referer 策略将被打破，比如从 http 协议的页面跳转到 https 的页面的时候，如果设置了适当的值，也会携带 referer。