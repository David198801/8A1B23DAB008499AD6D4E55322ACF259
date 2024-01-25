https://www.zhihu.com/question/489914730/answer/2160472535



作者：wujiuqier

链接：https://www.zhihu.com/question/489914730/answer/2160472535

来源：知乎

著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。



并不全是老式的，而是因为直接使用Win32子窗口控件的话默认使用的是老式样式。需要在开发时特别配置相关参数才能使其跟随系统主题样式。

如果是直接调用Win32的子窗口控件的话，需要在项目中添加一个manifest文件，这样编译出来才是跟随系统主题的样式。

著作权归作者所有。

商业转载请联系作者获得授权，非商业转载请注明出处。

作者：guijava

链接：MFC程序具有XP风格显示

来源：CSDN博客

这种方法可以将manifest文件编译到exe文件当中，发布的时候不需要额外增加一个.manifest文件。

步骤如下：



首先，在程序的RES目录下建一个文件,命名xp.manifest，文件内容为：

Code:

```javascript
<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<assembly xmlns="urn:schemas-microsoft-com:asm.v1" manifestVersion="1.0">
<assemblyIdentity
   version="1.0.0.0"
   processorArchitecture="X86"
   name="Microsoft.Windows.dummy"
   type="win32"
/>
<description>Your app description here</description>
<dependency>
   <dependentAssembly>
   <assemblyIdentity
   type="win32"
   name="Microsoft.Windows.Common-Controls"
   version="6.0.0.0"
   processorArchitecture="X86"
   publicKeyToken="6595b64144ccf1df"
   language="*"
   />
   </dependentAssembly>
</dependency>
</assembly>
```

然后，在VC中导入资源，导入xp.manifest，类型为24，并且修改ID为IDR_MANIFEST。

最后，通过菜单View-Resources Symbol …或者直接修改resource.h，将IDR_MANIFEST的值改为1。

编译后运行，程序就有xp的风格了。