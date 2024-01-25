System.currentTimeMillis()返回的是自1970年1月1日0时起的毫秒数

System.nanoTime()返回的是纳秒值，可能是任意时间或者是负数



public static native long currentTimeMillis();

public static native long nanoTime();

```javascript
System.out.println(System.currentTimeMillis());//1608702583743
System.out.println(System.nanoTime());//3205120464382
```

https://blog.csdn.net/qq_31388481/article/details/86521971



两者都可以用于计算运行时间

nanoTime()提供纳秒级精度，但不保证提供纳秒级分辨率

```javascript
long startTime,endTime;

//毫秒级
startTime = System.currentTimeMillis();

int sum=0;

for (int i = 0; i < 10000000; i++) {
	sum += i;
}

endTime = System.currentTimeMillis();

System.out.println((endTime-startTime) + "ms");

//纳秒级
startTime = System.nanoTime();

sum = 0;

for (int i = 0; i < 1000000; i++) {
	sum += i;
}

endTime = System.nanoTime();

System.out.println((endTime-startTime) + "ns");
```

