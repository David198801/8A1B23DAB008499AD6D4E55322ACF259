HandlerMapping接口用于请求映射

DispatcherServlet的getHandler()方法中遍历其handlerMappings属性进行映射并调用mapping.getHandler()返回HandlerExecutionChain





mapping.getHandler()在AbstractHandlerMapping中实现

调用了其抽象方法 getHandlerInternal()，

getHandlerInternal()在AbstractHandlerMethodMapping(AbstractHandlerMapping子类)中实现，其属性mappingRegistry.mappingLookup中保存着映射规则

```javascript
@Override
protected HandlerMethod getHandlerInternal(HttpServletRequest request) throws Exception {
   // 获取访问的url
   String lookupPath = getUrlPathHelper().getLookupPathForRequest(request);
   request.setAttribute(LOOKUP_PATH, lookupPath);
   // 获取锁
   this.mappingRegistry.acquireReadLock();
   try {
       // 在mappingRegistry中查找url匹配的handler方法(内部类Match，包含HandlerMethod属性)返回，匹配多个则报ambiguous异常
      HandlerMethod handlerMethod = lookupHandlerMethod(lookupPath, request);
      return (handlerMethod != null ? handlerMethod.createWithResolvedBean() : null);
   }
   finally {
       // 释放锁
      this.mappingRegistry.releaseReadLock();
   }
}
```

