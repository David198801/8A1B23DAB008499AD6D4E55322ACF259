https://developer.mozilla.org/zh-CN/docs/Web/JavaScript/Reference/Global_Objects/Date



Date

1.常用方法

| getDate() | 返回 Date 对象的一个月中的每一天，其值介于1～31之间 |
| - | - |
| getDay() | 返回 Date 对象的星期中的每一天，其值介于0～6之间 |
| getHours() | 返回 Date 对象的小时数，其值介于0～23之间 |
| getMinutes() | 返回 Date 对象的分钟数，其值介于0～59之间 |
| getSeconds() | 返回 Date 对象的秒数，其值介于0～59之间 |
| getMonth() | 返回 Date 对象的月份，其值介于0～11之间 |
| getFullYear() | 返回 Date 对象的年份，其值为4位数 |
| getTime() | 返回自某一时刻（1970年1月1日）以来的毫秒数 |


Month为0-11，需+1

Day为0-6，0为周日

```javascript
var d = new Date();
var s = "";

//返回年份
s += '年:';
s += d.getFullYear();
s += '<br/>';

//返回月份,0-11
s += '月:';
s += d.getMonth()+1;
s += '<br/>';

//返回每月的第几天,1-31
s += '日:';
s += d.getDate();
s += '<br/>';

//返回每周第几天，0-6,周日为0
s += '星期几:';
s += '星期'+ d.getDay();
s += '<br/>';

//返回小时数
s += '小时:';
s += d.getHours();
s += '<br/>';

//返回分钟数
s += '分钟:';
s += d.getMinutes();
s += '<br/>';

//返回秒数
s += '秒:';
s += d.getSeconds();
s += '<br/>';

//返回时间戳
s += '时间戳:';
s += d.getTime();
s += '<br/>';



document.getElementsByTagName('p')[0].innerHTML=s;
```



