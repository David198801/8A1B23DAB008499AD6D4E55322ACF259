

```javascript
public abstract class Calendar implements Serializable, Cloneable, Comparable<Calendar>
```



```javascript
//创建对象,默认获取当前时间
Calendar calendar = Calendar.getInstance();

//月份少了1
System.out.print(calendar.get(Calendar.YEAR) + "-");
System.out.print((calendar.get(Calendar.MONTH) + 1) + "-");
System.out.print(calendar.get(Calendar.DAY_OF_MONTH) + " ");
System.out.print(calendar.get(Calendar.HOUR_OF_DAY) + ":");
System.out.print(calendar.get(Calendar.MINUTE) + ":");
System.out.println(calendar.get(Calendar.SECOND));

//计算，减则传入-1
calendar.add(Calendar.YEAR, 1);//年
calendar.add(Calendar.MONTH, 1);//月
calendar.add(Calendar.DATE, 1);//日
calendar.add(Calendar.HOUR_OF_DAY, 1);//时
calendar.add(Calendar.MINUTE, 1);//分
calendar.add(Calendar.SECOND, 1);//秒

//getTime(),返回Date
System.out.println(calendar.getTime());

Date date = new Date();
//设置时间
calendar.setTime(date);
System.out.println(calendar.getTime());
```

