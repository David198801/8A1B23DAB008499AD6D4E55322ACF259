https://my.oschina.net/itsaysay/blog/4299422



在计算机中既可以是大端存储，也可以小端存储，跟CPU架构有关，IA架构(Intel、AMD)的CPU中是Little-Endian，而PowerPC 、SPARC和Motorola处理器是Big-Endian



基于Java8,在Java中提供了一个api可以获取CPU使用的字节序：



Java中既能有大端也能有小端，只是Java默认使用了大端