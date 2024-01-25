div等标签没有被focus

解决：

方法1

设置属性 contenteditable="true"，然后执行元素.focus()或打开网页后选中元素，按下键盘触发

方法2

直接给window或document绑定onkeydown事件

window.onkeydown=func;或document.func;

或window.addEventListener('keydown',clk);





其他方法



最简单的办法是给div的tabIndex属性设值.

```javascript
<div tabindex="-1" onfocus="console.log('focus')" onblur="console.log('blur')">Focus me</div>
```



也可以通过CSS来实现达到类似效果:

```javascript
<style>
#focus-example > .extra { display: none; }
#focus-example:focus > .extra { display: block; }
</style>
<div id="focus-example" tabindex="0">
  <div>Focus me!</div>
  <div>Hooray!</div>
</div>
```

在第2个例子中我们是显示/隐藏子元素, 因此当我们与子元素进行交互时, 父元素始终是focus的, 这种情况下我们就可以嵌入links, forms, videos等等子元素了.



tabindex属性值的是有一定含义的:

* -1: 用户不能通过tab的方式达到该元素, 只允许通过编程方式来获得focus(element.focus()), 或者就是点击获得focus.

* 0: 用户可以通过tab的方式达到该元素, 自动定义tab的顺序.

* >0: 给元素一个指定的tab优先级, 等于1时优先级最高.



定义tabindex属性后，元素是默认会加上焦点虚线的，那么在IE中可以通过hidefocus="true"去除！其他浏览器通过outline=0进行去除！