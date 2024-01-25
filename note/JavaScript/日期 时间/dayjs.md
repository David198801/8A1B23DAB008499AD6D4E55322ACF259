官网

https://day.js.org/zh-CN/

https://day.js.org/



cdn

https://www.bootcdn.cn/dayjs/

```javascript
<script src="https://cdn.bootcdn.net/ajax/libs/dayjs/1.10.4/dayjs.min.js"></script>
```



```javascript
https://cdn.jsdelivr.net/npm/dayjs@1.10.4/dayjs.min.js
```



文档

https://dayjs.gitee.io/docs/zh-CN/installation/installation



用法

```javascript
dayjs().format('YYYY年MM月DD日 HH时mm分ss秒') 
//"2021年04月16日 16时06分14秒"

dayjs(1430409600000).format('YYYY年MM月DD日 HH时mm分ss秒') 
//"2015年05月01日 00时00分00秒"
```

| 占位符 | 输出 | 详情 |
| - | - | - |
| YY | 18 | 两位数的年份 |
| YYYY | 2018 | 四位数的年份 |
| M | 1-12 | 月份，从 1 开始 |
| MM | 01-12 | 月份，两位数 |
| MMM | Jan-Dec | 缩写的月份名称 |
| MMMM | January-December | 完整的月份名称 |
| D | 1-31 | 月份里的一天 |
| DD | 01-31 | 月份里的一天，两位数 |
| d | 0-6 | 一周中的一天，星期天是 0 |
| dd | Su-Sa | 最简写的星期几 |
| ddd | Sun-Sat | 简写的星期几 |
| dddd | Sunday-Saturday | 星期几 |
| H | 0-23 | 小时 |
| HH | 00-23 | 小时，两位数 |
| h | 1-12 | 小时, 12 小时制 |
| hh | 01-12 | 小时, 12 小时制, 两位数 |
| m | 0-59 | 分钟 |
| mm | 00-59 | 分钟，两位数 |
| s | 0-59 | 秒 |
| ss | 00-59 | 秒 两位数 |
| SSS | 000-999 | 毫秒 三位数 |
| Z | +05:00 | UTC 的偏移量，±HH:mm |
| ZZ | +0500 | UTC 的偏移量，±HHmm |
| A | AM PM | a |


使用国际化

https://dayjs.gitee.io/docs/zh-CN/i18n/loading-into-browser

https://dayjs.gitee.io/docs/zh-CN/display/format

```javascript
<script src="https://cdn.bootcdn.net/ajax/libs/dayjs/1.10.4/dayjs.min.js"></script>
<script src="https://cdn.bootcdn.net/ajax/libs/dayjs/1.10.4/plugin/localizedFormat.min.js"></script>
<script src="https://cdn.bootcdn.net/ajax/libs/dayjs/1.10.4/locale/zh-cn.min.js"></script>
```



```javascript
dayjs.extend(window.dayjs_plugin_localizedFormat)
dayjs.locale('zh-cn')
dayjs().format('LLL');  // 2021年4月16日下午3点17分
```

