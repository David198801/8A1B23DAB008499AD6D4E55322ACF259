Linux——nohup命令使用（weblogic启动命令）

https://blog.csdn.net/Roy_70/article/details/61918182



Linux下有时候我们希望某个程序可以一直在后台执行，很多都是使用& 在程序命令结尾来让程序自动运行。

例如：



```javascript
[root@lbogon roy]# ./test.sh & 
```

但是这样子在终端关闭的时候，程序也会随之停止，此时我们可以使用nohup命令：

```javascript
nohup command &
```

例如：



```javascript
[root@lbogon roy]# nohup ./test.sh & 
```



这样的话虽然终端关闭后，程序不会停止，但是如果程序执行过程中报错，出现了logout，程序也会随之终止。所以如果我们希望程序可以一直在后台运行，无论是否报错，可以使用下面这个命令：



```javascript
[root@lbogon roy]# nohup ./test.sh >output.log 2>&1 &
```



为什么这么写有用，我们需要知道Linux操作系统中有三个常用的流：

　　0：标准输入流 stdin

　　1：标准输出流 stdout

　　2：标准错误流 stderr

一般当我们用 > console.txt，实际是 1>console.txt的省略用法；< console.txt ，实际是 0 < console.txt的省略用法，所以上条命令中>output.log是将输出写入到output.log文件中，2>&1意思是把标准错误（2）重定向到标准输出中（1），即标准输出以及错误输出都在output.log文件中，程序执行出现错误也不会终止。



延伸

1.当在后台运行了程序的时候，可以用jobs命令来查看后台作业的状态。在有多个后台程序时，要使用来参数的fg命令将不同序号的后台作业切换到前台上运行。



```javascript
[root@lbogon roy]# jobs
[root@lbogon roy]# fg %1
```



2.常用的weblogic启动server命令：



```javascript
[root@lbogon roy]# nohup ./startWebLogic.sh >out.log 2>&1 & 
```

