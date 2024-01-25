for-in，对数组获取的是下标，若不写var则创建一个全局变量

```javascript
var arr=["a","bb","ccc"];
for(var i in arr){
	document.write(arr[i],",");
}
```



for-in会遍历原型链中的元素

解决

1.ES6且为迭代器类型，可使用for-of

2.hasOwnProperty()

```javascript
for (var i in arr) {
　　if(arr.hasOwnProperty(i)){
　　　　console.log(i);
　　}
}

for (var key in myObject) {
　　if(myObject.hasOwnProperty(key)){
　　　　console.log(key);
　　}
}
```

3.forEach

```javascript
Object.prototype.method=function(){
　　console.log(this);
}
var myObject={
　　a:1,
　　b:2,
　　c:3
}
Object.keys(myObject).forEach(function(key,index){
    console.log(key,myObject[key])
})
```

