Python2.7在Windows下CMD编码为65001/utf-8时print报错[Errno 0]/[Errno 2]

使用python2.7处理unicode的字符串，环境变量已设置PYTHONIOENCODING为utf-8，cmd编码为utf-8时print unicode字符串会报错[Errno 0]或[Errno 2]（python3.6环境下未出现此问题）

```javascript
#coding:utf-8
import os
os.system("chcp 65001")
a = u"你好こんにちは"
print a
```

此时会报错，如果字符串只含ASCII字符就不会报错

 

经查这是windows实现C函数的问题

https://bugs.python.org/issue1602#msg148990



解决方法

方法1 使用win_unicode_console模块

1.安装

```javascript
pip install win_unicode_console
```

2.使用

很简单，导入后设置开启就行

```javascript
#coding:utf-8
import os
import win_unicode_console

win_unicode_console.enable()

os.system("chcp 65001")
a = u"你好こんにちは"
print a
```

方法2 不使用print

 根据issue的描述，可以用os.write(sys.stdout.fileno(), utf8str)的方式绕过

此时字符串不加u前缀，直接写入str类型

```javascript
#coding:utf-8
import os
import sys
os.system("chcp 65001")
a = "你好こんにちは"
os.write(sys.stdout.fileno(), a)
```



偷懒方法

1.使用pycharm执行不会报错，推测pycharm自行修复了这个问题

2.只输出中文的话，那就不用utf8了，直接chcp 936然后输出a.encode("gbk","ignore")