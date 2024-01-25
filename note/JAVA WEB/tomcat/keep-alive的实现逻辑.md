https://blog.csdn.net/lies_joker/article/details/103758040

https://www.jianshu.com/p/9ed60827177c

https://hongjiang.info/how-tomcat-implements-keep-alive/

https://www.jianshu.com/p/09e2f32a74dd



https://segmentfault.com/a/1190000016637212

http://blog.sina.com.cn/s/blog_e59371cc0102ux5w.html





https://www.fatalerrors.org/a/long-connection-socket-and-tomcat-containers.html



keepalive是工作不正常时回收资源用的。在一定时间后发送探测报文，如果客户端挂了就关闭连接，相对于“keep”更像是“check”。



同时，tcp连接的关闭也是由HTTP Server控制，执行了socket.close()才会关闭tcp连接。OS不会去关闭正常工作的连接，所以keepalive对HTTP Keep-Alive没有影响。



很多教程会提到tcp“长连接”“短连接”，个人觉得“长连接”“短连接”放在HTTP讲就好，keepalive不如理解成自动回收连接。