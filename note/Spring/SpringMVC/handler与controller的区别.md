https://codeday.me/bug/20190714/1459037.html



一般来说,Controller是Handler,但Handler不一定是Controller



例如,HttpRequestHandler,WebRequestHandler,MessageHandler都是可以使用DispatcherServlet的处理程序. ((@)Controller是执行Web请求并返回视图的处理程序.)



简而言之,Handler只是一个术语,它既不是类也不是接口.它负责执行映射.