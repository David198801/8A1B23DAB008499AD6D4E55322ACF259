1.HTTP的规定中，get用于获取数据，post用于添加数据

2.get的请求体可能不受支持或者被server忽略

3.get请求的参数直接显示在url中，可以用于分享、搜索引擎收录等，安全性略低

4.get应该是幂等的，可以被缓存或预加载

5.get请求可能受到浏览器或服务器的url大小限制

6.post可以发送二进制数据



https://www.w3school.com.cn/tags/html_ref_httpmethods.asp







| GET | POST | 后退按钮/刷新 |
| - | - | - |
| 无害 | 数据会被重新提交（浏览器应该告知用户数据会被重新提交）。 | 书签 |
| 可收藏为书签 | 不可收藏为书签 | 缓存 |
| 能被缓存 | 不能缓存 | 编码类型 |
| application/x-www-form-urlencoded | application/x-www-form-urlencoded 或 multipart/form-data。为二进制数据使用多重编码。 | 历史 |
| 参数保留在浏览器历史中。 | 参数不会保存在浏览器历史中。 | 对数据长度的限制 |
| 是的。当发送数据时，GET 方法向 URL 添加数据；URL 的长度是受限制的（URL 的最大长度是 2048 个字符）。 | 无限制。 | 对数据类型的限制 |
| 只允许 ASCII 字符。 | 没有限制。也允许二进制数据。 | 安全性 |
| 与 POST 相比，GET 的安全性较差，因为所发送的数据是 URL 的一部分。<br>在发送密码或其他敏感信息时绝不要使用 GET ！ | POST 比 GET 更安全，因为参数不会被保存在浏览器历史或 web 服务器日志中。 | 可见性 |


