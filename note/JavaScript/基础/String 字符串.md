String

属性

length，长度

方法

| charAt(index) | 返回在指定位置的字符 |
| - | - |
| indexOf(str，index) | 查找某个指定的字符串在字符串中首次出现的位置 |
| substring(index1，index2) | 返回位于指定索引index1和index2之间的字符串，并且包括索引index1对应的字符，不包括索引index2对应的字符 |
| split(str) | 将字符串分割为字符串数组 |


```javascript
var s="hello world 你好 世界";
console.log("字符串长度：",s.length);
console.log("下标为1的字符：",s.charAt(1));
console.log("substring(1,5)：",s.substring(1,5));
console.log("分割字符串：",s.split(" "));
```

可以下标访问

```javascript
s[1]//e
```

直接比较长度

'aaa'>'aaaa'，false



反转字符串

```javascript
"abc".toString().split("").reverse().join("")
```

