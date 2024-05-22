全都没用，只有刷固件，用脚本解决



 [keep_disk_awake.py](./assets/keep_disk_awake.py) 

```python
import os
import time

# 获取当前脚本所在目录
current_dir = os.path.dirname(os.path.realpath(__file__))
print(current_dir)

# 指定写入文件的路径
file_path = os.path.join(current_dir, 'output.txt')

# 间隔时间（秒）
interval = 10

file = open(file_path, 'w')

i = 1

while True:
    
    # 写入时间戳到文件
    file.write(str(i)+'\n')
    file.flush()
    print(i)
    i+=1

    # 每隔指定时间执行一次
    time.sleep(interval)

```







0.最终选择 硬盘apm禁用工具smart apm

[SmartAPM.exe](assets/SmartAPM.exe)

属性里勾选，管理员权限

1、任务计划程序 -> 新建任务

2、

常规：勾选最高权限、隐藏

触发器：新建2个触发器

1.登录时

2.发生事件时

日志：系统

源：Microsoft-Windows-Power-Troubleshooter

事件 ID：1

操作：启动程序，参数设置为【--quiet】

条件:取消交流电源，勾选唤醒

设置：停止现有实例





1.

电源设置0/从不，对APM无效



2.软改APM

会自动变回省电，需每次手动调

diskinfo，功能，高级特征，AAM/APM，设置APM为性能模式FEH



3.开机/唤醒自动改APM

硬盘apm禁用工具/smartmontools

设置计划任务关闭APM，可以设置休眠唤醒时运行

https://www.52pojie.cn/forum.php?mod=viewthread&tid=1128572

https://tieba.baidu.com/p/5234035086?pn=2

https://zry.io/archives/381

3.1批处理

https://forum.51nb.com/thread-1291752-1-1.html

3.2quietHDD，不起作用



4.永久修改APM，未成功

https://forum.51nb.com/thread-1353133-1-1.html



5.持续写入文件

DiskNoSleep