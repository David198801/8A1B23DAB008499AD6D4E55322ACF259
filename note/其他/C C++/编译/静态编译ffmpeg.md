利用GitHub Actions和ffmpeg-windows-build-helpers自动编译 https://github.com/AnimMouse/ffmpeg-stable-autobuild



https://trac.ffmpeg.org/wiki/CompilationGuide

https://github.com/rdp/ffmpeg-windows-build-helpers



https://my.oschina.net/zhangxu0512/blog/758453

https://blog.csdn.net/weixin_34379433/article/details/90304648



win

https://github.com/m-ab-s/media-autobuild_suite

https://www.jianshu.com/p/74e4e763ea6e



msys2 msys 会依赖msys-2.0.dll，用msys2 mingw



1.winpthread问题

msys2 mingw64安装的gcc实际上有libwinpthread.a，无需再安装(在msys64\mingw64\x86_64-w64-mingw32\lib)

https://packages.msys2.org/package/mingw-w64-x86_64-libwinpthread-git?repo=mingw64

源码在这里面



https://stackoverflow.com/questions/13768515/how-to-do-static-linking-of-libwinpthread-1-dll-in-mingw

```shell
 --pkg-config-flags="--static" 
```



```shell
LDFLAGS="-Wl,-Bstatic -lwinpthread -Wl,-Bdynamic -static-libgcc -static-libstdc++"
```



```shell
-Wl,-Bstatic,--whole-archive -lwinpthread -Wl,--no-whole-archive
```





CFLAGS

CXXFLAGS

LDFLAGS

https://blog.csdn.net/jfkidear/article/details/8262260?utm_medium=distribute.pc_relevant.none-task-blog-2%7Edefault%7EBlogCommendFromMachineLearnPai2%7Edefault-1.control&dist_request_id=1328769.32052.16174679661395623&depth_1-utm_source=distribute.pc_relevant.none-task-blog-2%7Edefault%7EBlogCommendFromMachineLearnPai2%7Edefault-1.control