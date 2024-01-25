数组

js数组可以存放不同类型元素，数组长度可变

1.创建数组

```javascript
var arr = new Array(4);
var arr2 = new Array(4,2,3,1);
var arr3 = ["aaa","bbb","ccc"];
```



```javascript
//赋值
arr[0]=1;
arr[1]=10;
arr[2]=100
arr[3]=1000;

//下标访问
console.log(arr[1],arr2[3],arr3[0]);
```

2.属性、方法

length

| 属性 | length | 设置或返回数组中元素的数目 |
| - | - | - |
| 方法 | join( ) | 把数组的所有元素放入一个字符串，通过一个的分隔符进行分隔 |
| sort() | 对数组排序 | push() |




```javascript
//属性
//长度
console.log("arr长度为：",arr.length);

//方法
//转为字符串
console.log("转为字符串：",arr2.join());
//排序
arr2.sort();
console.log("排序后：",arr2);
//添加元素，数组长度可变
arr.push(1234);
console.log("添加后：",arr);
```

sort()传入函数，自定义大小规则，0为等于，正数大于，负数小于

```javascript
var points = [40, 100, 1, 5, 25, 10];
points.sort(function(a, b){return b - a});
```

裁剪，截取，arrayObject.slice(start,end)，可为负数