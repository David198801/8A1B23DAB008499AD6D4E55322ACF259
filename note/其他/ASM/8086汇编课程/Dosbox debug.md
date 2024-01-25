Dosbox

https://www.dosbox.com/download.php?main=1

改窗口大小

双击安装目录下DOSBox 0.74 Options.bat

```javascript
fullresolution=original
windowresolution=1600x900
output=opengl
```

自动挂载，在最后面[autoexec]追加

```javascript
mount c e:\asm
c:
```





debug

[debug.exe](assets/debug.exe)

dos挂载

```javascript
mount c d:\test\debug
c:
debug
```

q退出



debug使用

https://blog.csdn.net/m0_52226803/article/details/119379958

r 查看、修改CPU寄存器中的内容。

```javascript
r cs
07cf
```



d 查看内存中的内容

e 修改内存中的内容，可直接一次性修改，也可根据提示一个一个字节的修改

u 以汇编指令形式查看对应内存中的内容

t 单步执行

a 以汇编指令形式向内存中写入指令

g 执行至指定内存的指令处

p 中断或跳出loop

