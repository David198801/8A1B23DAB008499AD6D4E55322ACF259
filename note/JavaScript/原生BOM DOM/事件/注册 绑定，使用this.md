动态

```javascript
xxx.onclick = function(){
    //xxxx
}
```



静态

```javascript
<input type="button" onload="clk()" value="点击触发" />
<script type="text/javascript">
    function clk(){
        console.log('触发');
    }
</script>
```

使用参数

```javascript
<input type="button" onload="calc(5,6)" value="点击触发" />
```







本质

```javascript
<input onclick="func();" />
```

等价于

```javascript
inputE.onclick=function(){
    func();
}
```

触发事件的标签对象this

静态：

```javascript
<input onclick="func(this);" />
```

动态：

```javascript
inputE.onclick=function(){
    this.value='xxxx';
}
```

