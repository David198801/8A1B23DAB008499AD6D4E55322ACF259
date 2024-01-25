一般无需移除msvcrt.dll依赖



一定要移除msvcrt.dll，可以使用VC++/VS在Release模式下编译为MT

https://blog.csdn.net/weixin_30315723/article/details/95141142

https://zhidao.baidu.com/question/417736988.html





https://stackoverflow.com/questions/28783079/mingw-mingw64-linking-and-dependency-on-msvcrt-dll



对于libgcc_s_dw2-1.dll和libstdc++-6.dll，mingw可以通过-static -static-libgcc -static-libstdc++避免