Win

https://luabinaries.sourceforge.net/download.html

Lua for Windows

https://github.com/rjpcomputing/luaforwindows/releases



接下来我们创建一个 HelloWorld.lua 文件，代码如下:

```javascript
print("Hello World!")
```

执行以下命令:

```javascript
$ lua HelloWorld.lua
```

输出结果为：

```javascript
Hello World!
```



交互模式

```javascript
lua -i 
```



```javascript
> print(1)
1
```

Linux 系统上安装

Linux & Mac上安装 Lua 安装非常简单，只需要下载源码包并在终端解压编译即可，本文使用了5.3.0版本进行安装：

```javascript
curl -R -O http://www.lua.org/ftp/lua-5.3.0.tar.gz
tar zxf lua-5.3.0.tar.gz
cd lua-5.3.0
make linux test
make install
```



---

Mac OS X 系统上安装

```javascript
curl -R -O http://www.lua.org/ftp/lua-5.3.0.tar.gz
tar zxf lua-5.3.0.tar.gz
cd lua-5.3.0
make macosx test
make install
```

或

```javascript
brew install lua
```

