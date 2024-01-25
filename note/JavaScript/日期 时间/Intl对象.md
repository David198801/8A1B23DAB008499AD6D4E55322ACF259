https://developer.mozilla.org/zh-CN/docs/Web/JavaScript/Reference/Global_Objects/Intl



DateTimeFormat

https://developer.mozilla.org/zh-CN/docs/Web/JavaScript/Reference/Global_Objects/Intl/DateTimeFormat

```javascript
new Intl.DateTimeFormat('zh-CN').format(new Date())
"2021/4/16"
```



```javascript
const options = {
   weekday: 'long',
   year: 'numeric',
   month: 'long',
   day: 'numeric',
   timeZone: 'Asia/Shanghai',
   timeZoneName: 'long'
};
console.log(new Intl.DateTimeFormat('zh-CN', options).format(new Date()))
```

