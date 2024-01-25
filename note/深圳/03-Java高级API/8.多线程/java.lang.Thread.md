

```javascript
public class Thread implements Runnable
```

1.常用构造方法

(1)

```javascript
public Thread()
```

(2)传入Runnable，赋给target成员变量

```javascript
public Thread(Runnable target)
```

2.常用方法

| 方       法 |  说       明 |
| - | - |
| void setPriority(int  newPriority) | 更改线程的优先级 |
| static void sleep(long millis) | 在指定的毫秒数内让当前正在执行的线程休眠 |
| void join() | 等待该线程终止 |
| static void yield() | 暂停当前正在执行的线程对象，并执行其他线程 |
| void interrupt() | 中断线程 |
| boolean isAlive() | 测试线程是否处于活动状态 |


