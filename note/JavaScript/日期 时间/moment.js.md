官网

http://momentjs.cn/

https://momentjs.com/



cdn

```javascript
<script src="https://cdn.bootcdn.net/ajax/libs/moment.js/2.29.1/locale/zh-cn.min.js"></script>
```



```javascript
http://cdn.staticfile.org/moment.js/2.24.0/moment.js
```





用法

日期格式化

```javascript
moment().format('MMMM Do YYYY, h:mm:ss a'); // 四月 16日 2021, 3:17:02 下午
moment().format('dddd');                    // 星期五
moment().format("MMM Do YY");               // 4月 16日 21
moment().format('YYYY [escaped] YYYY');     // 2021 escaped 2021
moment().format();                          // 2021-04-16T15:17:02+08:00
```

相对时间

```javascript
moment("20111031", "YYYYMMDD").fromNow(); // 9 年前
moment("20120620", "YYYYMMDD").fromNow(); // 9 年前
moment().startOf('day').fromNow();        // 15 小时前
moment().endOf('day').fromNow();          // 9 小时内
moment().startOf('hour').fromNow();       // 17 分钟前
```

日历时间

```javascript
moment().subtract(10, 'days').calendar(); // 2021/04/06
moment().subtract(6, 'days').calendar();  // 上星期六15:17
moment().subtract(3, 'days').calendar();  // 上星期二15:17
moment().subtract(1, 'days').calendar();  // 昨天15:17
moment().calendar();                      // 今天15:17
moment().add(1, 'days').calendar();       // 明天15:17
moment().add(3, 'days').calendar();       // 下星期一15:17
moment().add(10, 'days').calendar();      // 2021/04/26
```

多语言支持

```javascript
moment.locale();         // zh-cn
moment().format('LT');   // 15:17
moment().format('LTS');  // 15:17:02
moment().format('L');    // 2021/04/16
moment().format('l');    // 2021/4/16
moment().format('LL');   // 2021年4月16日
moment().format('ll');   // 2021年4月16日
moment().format('LLL');  // 2021年4月16日下午3点17分
moment().format('lll');  // 2021年4月16日 15:17
moment().format('LLLL'); // 2021年4月16日星期五下午3点17分
moment().format('llll'); // 2021年4月16日星期五 15:17
```



