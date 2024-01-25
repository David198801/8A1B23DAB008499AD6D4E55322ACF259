public class SimpleDateFormat extends DateFormat

继承DateFormat

作用：自定义格式解析日期或时间

| 字母 | 含义 | 示例 |
| - | - | - |
| y | 年份。一般用 yy 表示两位年份，yyyy 表示 4 位年份 | 使用 yy 表示的年扮，如 11；<br>使用 yyyy 表示的年份，如 2011 |
| M | 月份。一般用 MM 表示月份，如果使用 MMM，则会<br>根据语言环境显示不同语言的月份 | 使用 MM 表示的月份，如 05；<br>使用 MMM 表示月份，在 Locale.CHINA<br>语言环境下，如&quot;十月”；在 Locale.US<br>语言环境下，如 Oct |
| d | 月份中的天数。一般用 dd 表示天数 | 使用 dd 表示的天数，如 10 |
| D | 年份中的天数。表示当天是当年的第几天， 用 D 表示 | 使用 D 表示的年份中的天数，如 295 |
| E | 星期几。用 E 表示，会根据语言环境的不同， 显示不<br>同语言的星期几 | 使用 E 表示星期几，在 Locale.CHINA 语<br>言环境下，如&quot;星期四”；在 Locale.US 语<br>言环境下，如 Thu |
| H | 一天中的小时数（0~23)。一般用 HH 表示小时数 | 使用 HH 表示的小时数，如 18 |
| h | 一天中的小时数（1~12)。一般使用 hh 表示小时数 | 使用 hh 表示的小时数，如 10 (注意 10 有<br>可能是 10 点，也可能是 22 点） |
| m | 分钟数。一般使用 mm 表示分钟数 | 使用 mm 表示的分钟数，如 29 |
| s | 秒数。一般使用 ss 表示秒数 | 使用 ss 表示的秒数，如 38 |
| S | 毫秒数。一般使用 SSS 表示毫秒数 | 使用 SSS 表示的毫秒数，如 156 |




| Letter | Date or Time Component | Presentation | Examples |
| - | - | - | - |
| G | Era designator | Text | AD |
| y | Year | Year | 1996; 96 |
| Y | Week year | Year | 2009; 09 |
| M | Month in year (context sensitive) | Month | July; Jul; 07 |
| L | Month in year (standalone form) | Month | July; Jul; 07 |
| w | Week in year | Number | 27 |
| W | Week in month | Number | 2 |
| D | Day in year | Number | 189 |
| d | Day in month | Number | 10 |
| F | Day of week in month | Number | 2 |
| E | Day name in week | Text | Tuesday; Tue |
| u | Day number of week (1 = Monday, ..., 7 = Sunday) | Number | 1 |
| a | Am/pm marker | Text | PM |
| H | Hour in day (0-23) | Number | 0 |
| k | Hour in day (1-24) | Number | 24 |
| K | Hour in am/pm (0-11) | Number | 0 |
| h | Hour in am/pm (1-12) | Number | 12 |
| m | Minute in hour | Number | 30 |
| s | Second in minute | Number | 55 |
| S | Millisecond | Number | 978 |
| z | Time zone | General time zone | Pacific Standard Time; PST; GMT-08:00 |
| Z | Time zone | RFC 822 time zone | -0800 |
| X | Time zone | ISO 8601 time zone | -08; -0800; -08:00 |








SimpleDateFormat 类主要有如下 3 种构造方法。

- SimpleDateFormat()：用默认的格式和默认的语言环境构造 SimpleDateFormat。

- SimpleDateFormat(String pattern)：用指定的格式和默认的语言环境构造 SimpleDateF ormat。

- SimpleDateFormat(String pattern,Locale locale)：用指定的格式和指定的语言环境构造 SimpleDateF ormat。





```javascript
SimpleDateFormat f = new SimpleDateFormat("今天是 " + "yyyy 年 MM 月 dd 日 E HH 点 mm 分 ss 秒");
System.out.println(f.format(new Date())); 
```



时区转换

```javascript
Date date=new Date();


SimpleDateFormat sdf8=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//需要import java.util.TimeZone;
sdf8.setTimeZone(TimeZone.getTimeZone("GMT+8:00"));//设置时区为东八区
System.out.println("东八区的时间:"+sdf8.format(date));//东八区的时间:2020-12-23 15:16:38

SimpleDateFormat sdf9=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
sdf9.setTimeZone(TimeZone.getTimeZone("GMT+9:00"));//设置时区为东八区
System.out.println("东九区的时间:"+sdf9.format(date));//东九区的时间:2020-12-23 16:16:38
```

