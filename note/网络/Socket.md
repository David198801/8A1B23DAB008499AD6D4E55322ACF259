Socket是电脑网络中进程通信端点，基于网络协议的Socket被称为Internet socket。



操作系统通常会提供一套Socket API。

JAVA中的Socket调用了操作系统的API，如WinSock



Berkeley sockets，最早的Socket API（4.2 BSD）。

伯克利套接字（英语：Internet Berkeley sockets） ，又称为BSD 套接字(BSD sockets)是一种应用程序接口（API），用于网络套接字（ socket）与Unix域套接字，包括了一个用C语言写成的应用程序开发库，主要用于实现进程间通讯，在计算机网络通讯方面被广泛使用。



Unix域套接字(Unix domain socket) 或者 IPC socket是一种终端，可以使同一台操作系统上的两个或多个进程进行数据通信。与管道相比，Unix domain sockets 既可以使用字节流，又可以使用数据队列，而管道通信则只能使用字节流。Unix domain sockets的接口和Internet socket很像，但它不使用网络底层协议来通信。Unix domain socket 的功能是POSIX操作系统里的一种组件。



Windows 10 Build 17063 开始支持Unix domain socket。



https://www.wanweibaike.com/wiki-%E7%BD%91%E7%BB%9C%E5%A5%97%E6%8E%A5%E5%AD%97

https://www.zhihu.com/question/32244600

https://www.zhihu.com/question/390668853

https://www.zhihu.com/question/423488440

https://www.zhihu.com/question/20060141