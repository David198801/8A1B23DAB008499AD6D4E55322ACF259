包装类、基本数据类型、String互转

```javascript
int i = 1;
Integer it = new Integer(1);
String s = "1";
```

1.包装类 -> 基本数据类型

```javascript
i = it.intValue();
i = (int)it;
```

2.基本数据类型 -> 包装类

```javascript
it = Integer.valueOf(1);
it = new Integer(1);
```

3.包装类 -> String

```javascript
s = it.toString();
s = String.valueOf(it);//String.valueOf(Object obj)
```

4.String -> 包装类

```javascript
it = new Integer("1");
it = Integer.valueOf("1");
```

5.String -> 基本数据类型

```javascript
i = Integer.parseInt("1");
```

6.基本数据类型 -> String

```javascript
s = String.valueOf(1);//String.valueOf(int i)
s = Integer.toString(1);
s = 1 + "";
```



new Boolean(str)时，"true"则为true，null或其他字符串都为false

```javascript
Boolean b1 = new Boolean("true");
Boolean b2 = new Boolean("false");
Boolean b3 = new Boolean("aaa");
Boolean b4 = new Boolean(null);
System.out.println(b1);//true
System.out.println(b2);//false
System.out.println(b3);//false
System.out.println(b4);//false
```



无法解析时抛出NumberFormatException异常

```javascript
Integer integer = new Integer("aa");//java.lang.NumberFormatException
```

