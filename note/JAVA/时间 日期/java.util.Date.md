时间皆为时间戳(毫秒)

构造方法

1.无参，fastTime属性为System.currentTimeMillis()

2.设置fastTime为传入的long

```javascript
public Date(long date) {
    fastTime = date;
}
```

@Deprecated

```javascript
@Deprecated
public Date(int year, int month, int date) {
	this(year, month, date, 0, 0, 0);
}

@Deprecated
public Date(int year, int month, int date, int hrs, int min) {
	this(year, month, date, hrs, min, 0);
}

@Deprecated
public Date(int year, int month, int date, int hrs, int min, int sec)

@Deprecated
public Date(String s) {
	this(parse(s));
}
```

3.Date(int year, int month, int day) ，可以构造指定日期的Date类对象，Date类中年份的参数应该是实际需要代表的年份减去1900，实际需要代表的月份减去1以后的值。

```javascript
//创建一个代表2014年6月12号的Date对象
Date d1 = new Date(2014-1900, 6-1, 12); （注意参数的设置）
```

方法

获取时间戳

public long getTime()

```javascript
long l1 = date.getTime();
System.out.println(l1);//1608702182469
Thread.sleep(1000);
System.out.println(date.getTime());//1608702182469
System.out.println(System.currentTimeMillis());//1608702183471
```

date对象new时时间就已确定

getTime()实际返回System.currentTimeMillis()，如果只要用毫秒时间可以直接用后者

getTime()调用getTimeImpl()，getTimeImpl()返回fastTime属性，fastTime在构造方法中被赋值							为System.currentTimeMillis()



toString()

转换为英文时间格式

```javascript
System.out.println(date);//Wed Dec 23 14:06:32 CST 2020
```



操作fastTime

setTime()、getTime()



比较日期

public boolean after(Date when)

public boolean before(Date when)

```javascript
Date oldDate = new Date();
Thread.sleep(1000);
Date newDate = new Date();

System.out.println(oldDate.before(newDate));//true
System.out.println(oldDate.after(newDate));//false
```





