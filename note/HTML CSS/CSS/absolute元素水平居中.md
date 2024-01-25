原始（未居中）：

```javascript
.con{
  width:200px;
  height:200px;
  background:#ccc;
  position:relative;
}
.abs{
  width:40px;
  height:20px;
  background:steelblue;
  position:absolute;
  bottom:0;
}
```



```javascript
<!DOCTYPE html>
<html>
<head>
  <meta charset="utf-8">
  <meta name="viewport" content="width=device-width">
  <title>JS Bin</title>
</head>
<body>
<div class="con">
  <div class="abs"></div>
</div>
</body>
</html>
```

Solution 1：

给absolute元素的left设为50%, margin-left设为absolute元素宽度一半的负数

```javascript
.con{
  width:200px;
  height:200px;
  background:#ccc;
  position:relative;
}
.abs{
  width:40px;
  height:20px;
  background:steelblue;
  position:absolute;
  bottom:0;

  left:50%;
  margin-left:-20px;
}
```

Solution 2：

原理和1相似，设left：50%，但使用css3的transform：translate(x,y);

```javascript
.con{
  width:200px;
  height:200px;
  background:#ccc;
  position:relative;
}
.abs{
  width:40px;
  height:20px;
  background:steelblue;
  position:absolute;
  bottom:0;

  left:50%;
  transform:translate(-50%);
}
```

Solution 3：

margin:auto;实现居中,但是absolute元素一定要有宽度，并且如果宽度不合适（常见于ul li）也是不会居中的

```javascript
.con{
  width:200px;
  height:200px;
  background:#ccc;
  position:relative;
}
.abs{
  width:40px;
  height:20px;
  background:steelblue;
  position:absolute;
  bottom:0;
  left:0;
  right:0;
  margin:0 auto;
}
```

