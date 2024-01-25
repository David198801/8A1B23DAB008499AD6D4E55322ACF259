在链式调用中使结果集回退一个操作，如果之前没有改动的操作，则返回一个空集。

HTML 代码:

```javascript
<p><span>Hello</span>,how are you?</p>
```

jQuery 代码:

```javascript
$("p").find("span").end()
```

结果:

```javascript
[ <p><span>Hello</span> how are you?</p> ]
```

