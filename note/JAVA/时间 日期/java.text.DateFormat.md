public abstract class DateFormat extends Format

抽象类，继承Fortmat.

作用：格式化并解析日期或时间。



创建对象

```javascript
DateFormat df = DateFormat.getDatelnstance();
```

| 方法 | 描述 |
| - | - |
| String format(Date date) | 将 Date 格式化日期/时间字符串 |
| Calendar getCalendar() | 获取与此日期/时间格式相关联的日历 |
| static DateFormat getDateInstance() | 获取具有默认格式化风格和默认语言环境的日期格式 |
| static DateFormat getDateInstance(int style) | 获取具有指定格式化风格和默认语言环境的日期格式 |
| static DateFormat getDateInstance(int style,<br>Locale locale) | 获取具有指定格式化风格和指定语言环境的日期格式 |
| static DateFormat getDateTimeInstance() | 获取具有默认格式化风格和默认语言环境的日期/时间<br>格式 |
| static DateFormat getDateTimeInstance(int<br>dateStyle,int timeStyle) | 获取具有指定日期/时间格式化风格和默认语言环境的<br>日期/时间格式 |
| static DateFormat getDateTimeInstance(int<br>dateStyle,int timeStyle,Locale locale) | 获取具有指定日期/时间格式化风格和指定语言环境的<br>日期/时间格式 |
| static DateFormat getTimeInstance() | 获取具有默认格式化风格和默认语言环境的时间格式 |
| static DateFormat getTimeInstance(int style) | 获取具有指定格式化风格和默认语言环境的时间格式 |
| static DateFormat getTimeInstance(int style,<br>Locale locale) | 获取具有指定格式化风格和指定语言环境的时间格式 |
| void setCalendar(Calendar newCalendar) | 为此格式设置日历 |
| Date parse(String source) | 将给定的字符串解析成日期/时间 |




```javascript
DateFormat df = DateFormat.getDateInstance();

System.out.println(df.format(new Date()));//2020-12-23

// 获取不同格式化风格和中国环境的日期
//需要import java.util.Locale
DateFormat df1 = DateFormat.getDateInstance(DateFormat.SHORT, Locale.CHINA);
DateFormat df2 = DateFormat.getDateInstance(DateFormat.FULL, Locale.CHINA);
DateFormat df3 = DateFormat.getDateInstance(DateFormat.MEDIUM, Locale.CHINA);
DateFormat df4 = DateFormat.getDateInstance(DateFormat.LONG, Locale.CHINA);

// 获取不同格式化风格和中国环境的时间
DateFormat df5 = DateFormat.getTimeInstance(DateFormat.SHORT, Locale.CHINA);
DateFormat df6 = DateFormat.getTimeInstance(DateFormat.FULL, Locale.CHINA);
DateFormat df7 = DateFormat.getTimeInstance(DateFormat.MEDIUM, Locale.CHINA);
DateFormat df8 = DateFormat.getTimeInstance(DateFormat.LONG, Locale.CHINA);

// 将不同格式化风格的日期格式化为日期字符串
String date1 = df1.format(new Date());
String date2 = df2.format(new Date());
String date3 = df3.format(new Date());
String date4 = df4.format(new Date());

// 将不同格式化风格的时间格式化为时间字符串
String time1 = df5.format(new Date());
String time2 = df6.format(new Date());
String time3 = df7.format(new Date());
String time4 = df8.format(new Date());

// 输出日期
System.out.println("SHORT：" + date1 + " " + time1);
//SHORT：20-12-23 下午2:58
System.out.println("FULL：" + date2 + " " + time2);
//FULL：2020年12月23日 星期三 下午02时58分24秒 CST
System.out.println("MEDIUM：" + date3 + " " + time3);
//MEDIUM：2020-12-23 14:58:24
System.out.println("LONG：" + date4 + " " + time4);
//LONG：2020年12月23日 下午02时58分24秒
```

