设置自定义属性:setAttribute("属性的名字","属性的值");

获取自定义属性的值:getAttribute("属性的名字")

或直接写在html标签里然后getAttribute

html:

```javascript
<p tid="1" onClick="change()">111</p>
<p tid="2" onClick="change()">111</p>
<p tid="3" onClick="change()">111</p>
```



js:

```javascript
function change(ev) {
var ev = ev || window.event,
    target = ev.target || ev.srcElement, 
    tid = target.getAttribute('tid');
console.log('tid',tid)
}
```

获取data-xxx

js

```javascript
document.getElementById('fun').dataset.appId = 'hsfun' // <div data-app-id="hsfun"></div>
var f2 = document.getElementById('fun').dataset.appId // hsfun
```



jquery

```javascript
var f1 = $(this).data('id');
var f2 = $(this).attr('data-id');
```



