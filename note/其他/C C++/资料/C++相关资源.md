C++相关资源

生死相依 2015-01-04 原文

http://www.cnblogs.com/xi52qian/p/4186983.html

语言

ISO/IEC JTC1/SC22/WG21 - The C++ Standards Committee

http://www.open-std.org/jtc1/sc22/wg21/

C/C++ Reference

http://www.cppreference.com/

The C++ Resources Network

http://www.cplusplus.com/

C++ FAQ LITE — Frequently Asked Questions

http://www.sunistudio.com/cppfaq/index.html

库

1)STL

Standard Template Library Programmer's Guide

http://www.sgi.com/tech/stl/

Effective STL

http://www.stlchina.org/documents/EffectiveSTL/index.html

2)Boost

Boost C++ Libraries

http://www.boost.org/

3)ACE

The ADAPTIVE Communication Environment (ACE)

http://www.cs.wustl.edu/~schmidt/ACE.html

ACE/TAO爱好者之家

http://www.ace-tao.org/

工具

Microsoft Visual C++ 6.0

    最常见的老牌IDE。

Microsoft Visual Studio .NET 2003

    对C++标准支持很好了,配合VisualAssistXv10是强有力的工具，是我目前的最佳选择。

Borland C++Builder

    装了没大用过，应该也不错。

CodeBlocks

    一个开源的，跨平台的免费的C/C++集成开发环境。http://www.codeblocks.org。

Scott Meyers: Software Development Consultant

http://www.aristeia.com/

Bjarne Stroustrup's Homepage

http://research.att.com/~bs/homepage.html

补充一下:

IDE：

Eclipse+CDT开发环境，虽然是java 用着慢一点，但是功能还是不错的，适合于做java和C++开发结合的人，而且内置了CVS插件、SVN等，还是不错的

工具:

C++转为HTML:

     SCC（老罗的），个人觉得这个不错，比较简单。

     C++2HTML，参考站点：http://www.bedaux.net/cpp2html/（还没用过，不知道）

库:

C++作为一个历史长久的语言，肯定会有很多库的，现在只列举一些常用的(转贴的)

1、   Dinkumware C++ Library

参考站点：http://www.dinkumware.com/

P.J. Plauger编写的高品质的标准库。P.J. Plauger博士是Dr. Dobb's程序设计杰出奖的获得者。其编写的库长期被Microsoft采用，并且最近Borland也取得了其OEM的license，在其C/C+ +的产品中采用Dinkumware的库。

2、   RogueWave Standard C++ Library

参考站点：http://www.roguewave.com/

这个库在Borland C++ Builder的早期版本中曾经被采用，后来被其他的库给替换了。笔者不推荐使用。

3、SGI STL

参考站点：http://www.roguewave.com/

SGI公司的C++标准模版库。

4、STLport

参考站点：http://www.stlport.org/

SGI STL库的跨平台可移植版本。

准标准库——Boost

Boost库是一个经过千锤百炼、可移植、提供源代码的C++库，作为标准库的后备，是C++标准化进程的发动机之一。 Boost库由C++标准委员会库工作组成员发起，在C++社区中影响甚大，其成员已近2000人。 Boost库为我们带来了最新、最酷、最实用的技术，是不折不扣的“准”标准库。

Boost中比较有名气的有这么几个库：

Regex

正则表达式库

Spirit

LL parser framework，用C++代码直接表达EBNF

Graph

图组件和算法

Lambda

在调用的地方定义短小匿名的函数对象，很实用的functional功能

concept check

检查泛型编程中的concept

Mpl

用模板实现的元编程框架

Thread

可移植的C++多线程库

Python

把C++类和函数映射到Python之中

Pool

内存池管理

smart_ptr

5个智能指针，学习智能指针必读，一份不错的参考是来自CUJ的文章：

Smart Pointers in Boost,哦，这篇文章可以查到，CUJ是提供在线浏览的。中文版见笔者在《Dr. Dobb's Journal软件研发杂志》第7辑上的译文。

Boost总体来说是实用价值很高，质量很高的库。并且由于其对跨平台的强调，对标准C++的强调，是编写平台无关，现代C++的开发者必备的工具。但是 Boost中也有很多是实验性质的东西，在实际的开发中实用需要谨慎。并且很多Boost中的库功能堪称对语言功能的扩展，其构造用尽精巧的手法，不要贸 然的花费时间研读。Boost另外一面，比如Graph这样的库则是具有工业强度，结构良好，非常值得研读的精品代码，并且也可以放心的在产品代码中多多 利用。

参考站点：http://www.boost.org （国内镜像：http://www.c-view.org/tech/lib/boost/index.htm ）

GUI

在众多C++的库中，GUI部分的库算是比较繁荣，也比较引人注目的。在实际开发中，GUI库的选择也是非常重要的一件事情，下面我们综述一下可选择的GUI库，各自的特点以及相关工具的支持。

1、   MFC

大名鼎鼎的微软基础类库（Microsoft Foundation Class）。大凡学过VC++的人都应该知道这个库。虽然从技术角度讲，MFC是不大漂亮的，但是它构建于Windows API 之上，能够使程序员的工作更容易,编程效率高，减少了大量在建立 Windows 程序时必须编写的代码，同时它还提供了所有一般 C++ 编程的优点，例如继承和封装。MFC 编写的程序在各个版本的Windows操作系统上是可移植的，例如，在 Windows 3.1下编写的代码可以很容易地移植到 Windows NT 或 Windows 95 上。但是在最近发展以及官方支持上日渐势微。

2、   QT

参考网站：http://www.trolltech.com/

Qt是Trolltech公司的一个多平台的C++图形用户界面应用程序框架。它提供给应用程序开发者建立艺术级的图形用户界面所需的所用功能。 Qt是完全面向对象的很容易扩展，并且允许真正地组件编程。自从1996年早些时候，Qt进入商业领域，它已经成为全世界范围内数千种成功的应用程序的基 础。Qt也是流行的Linux桌面环境KDE 的基础，同时它还支持Windows、Macintosh、Unix/X11等多种平台。

3、WxWindows

参考网站：http://www.wxwindows.org/

跨平台的GUI库。因为其类层次极像MFC，所以有文章介绍从MFC到WxWindows的代码移植以实现跨平台的功能。通过多年的开发也是一个日趋完善 的GUI库，支持同样不弱于前面两个库。并且是完全开放源代码的。新近的C++ Builder X的GUI设计器就是基于这个库的。

4、Fox

开放源代码的GUI库。作者从自己亲身的开发经验中得出了一个理想的GUI库应该是什么样子的感受出发，从而开始了对这个库的开发。有兴趣的可以尝试一下。

参考网站：http://www.fox-toolkit.org/

5、   WTL

基于ATL的一个库。因为使用了大量ATL的轻量级手法，模板等技术，在代码尺寸，以及速度优化方面做得非常到位。主要面向的使用群体是开发COM轻量级供网络下载的可视化控件的开发者。

6、   GTK

参考网站：http://gtkmm.sourceforge.net/

GTK是一个大名鼎鼎的C的开源GUI库。在Linux世界中有Gnome这样的杀手应用。而GTK就是这个库的C++封装版本。

网络通信

ACE

参考网站：http://www.cs.wustl.edu/~schmidt/ACE.html

C++库的代表，超重量级的网络通信开发框架。ACE自适配通信环境（Adaptive Communication Environment）是可以自由使用、开放源代码的面向对象框架，在其中实现了许多用于并发通信软件的核心模式。ACE提供了一组丰富的可复用C++ 包装外观（Wrapper Facade）和框架组件，可跨越多种平台完成通用的通信软件任务，其中包括：事件多路分离和事件处理器分派、信号处理、服务初始化、进程间通信、共享内 存管理、消息路由、分布式服务动态（重）配置、并发执行和同步，等等。

StreamModule

参考网站：http://www.omnifarious.org/StrMod/

设计用于简化编写分布式程序的库。尝试着使得编写处理异步行为的程序更容易，而不是用同步的外壳包起异步的本质。

SimpleSocket

参考网站：http://home.hetnet.nl/~lcbokkers/simsock.htm

这个类库让编写基于socket的客户/服务器程序更加容易。

A Stream Socket API for C++

参考网站：http://www.pcs.cnu.edu/~dgame/sockets/socketsC++/sockets.html

又一个对Socket的封装库。

XML

Xerces

参考网站：http://xml.apache.org/xerces-c/

Xerces-C++ 是一个非常健壮的XML解析器，它提供了验证，以及SAX和DOM API。XML验证在文档类型定义(Document Type Definition，DTD)方面有很好的支持，并且在2001年12月增加了支持W3C XML Schema 的基本完整的开放标准。

XMLBooster

参考网站：http://www.xmlbooster.com/

这个库通过产生特制的parser的办法极大的提高了XML解析的速度，并且能够产生相应的GUI程序来修改这个parser。在DOM和SAX两大主流XML解析办法之外提供了另外一个可行的解决方案。

Pull Parser

参考网站：http://www.extreme.indiana.edu/xgws/xsoap/xpp/

这个库采用pull方法的parser。在每个SAX的parser底层都有一个pull的parser，这个xpp把这层暴露出来直接给大家使用。在要充分考虑速度的时候值得尝试。

Xalan

参考网站：http://xml.apache.org/xalan-c/

Xalan是一个用于把XML文档转换为HTML，纯文本或者其他XML类型文档的XSLT处理器。

CMarkup

参考网站：http://www.firstobject.com/xml.htm

这是一种使用EDOM的XML解析器。在很多思路上面非常灵活实用。值得大家在DOM和SAX之外寻求一点灵感。

libxml++

http://libxmlplusplus.sourceforge.net/

libxml++是对著名的libxml XML解析器的C++封装版本

http://www.functionx.com   里面比较全面的在线教程

C 程序设计语言 相关资源 精选

语言标准及文档

● ISO C99

http://www.open-std.org/JTC1/SC22/WG14/www/docs/n1124.pdf

● ANSI C Rationale

http://www.quut.com/c/rat/title.html

FAQ (常见问题)

● comp.lang.c FAQ

http://c-faq.com/

● C-FAQ 中文版

http://c-faq-chn.sourceforge.net/ccfaq/node1.html

学习资料

● The New C Standard: An economic and cultural commentary

http://www.coding-guidelines.com/cbook/cbook1_0b.pdf

● C Programming Reference

http://www.space.unibe.ch/comp_doc/c_manual/C/cref.html

● Official GNU C Library documentation

http://www.gnu.org/software/libc/manual/

● C Programming course at University of Strathclyde Computer Centre

http://www.its.strath.ac.uk/courses/c/

● Everything you need to know about pointers in C

http://boredzo.org/pointers/

● Notes on K&R2

http://www.eskimo.com/~scs/cclass/cclass.html

● Howstuffworks C Programming

http://computer.howstuffworks.com/c.htm

更多

● http://www.quut.com/c/