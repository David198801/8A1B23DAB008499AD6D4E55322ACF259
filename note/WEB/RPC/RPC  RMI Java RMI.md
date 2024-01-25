RPC：Remote Procedure Call，远程过程调用，也是一种进程通信的方式，常用于分布式架构

如果涉及的软件采用面向对象编程，那么远程过程调用亦可称作远程调用或远程方法调用(RMI)



Java RMI即java对RMI的实现，提供一组接口。在jdk1.2支持，在java.rmi包中。



RPC有基于各种协议的实现

thrift，实际上是一种协议(基于tcp)，也是一个rpc框架

https://thrift.apache.org/

https://github.com/apache/thrift

gRPC，基于HTTP/2

https://www.grpc.io/

Motan，支持多种协议，自有协议Motan(tcp)

https://github.com/topics/motan



基于rpc的微服务框架

Spring Cloud，支持多种协议

Dubbo，支持多种协议，自有协议Dubbo(基于TCP)

https://www.cnblogs.com/lengfo/p/4293399.html

Tars



gRPC是谷歌开源的一个 RPC 框架，面向移动和 HTTP/2 设计。





资料：

https://www.jianshu.com/p/7d6853140e13

https://www.wanweibaike.com/wiki-%E9%81%A0%E7%A8%8B%E9%81%8E%E7%A8%8B%E8%AA%BF%E7%94%A8

分布式架构基础:Java RMI详解

