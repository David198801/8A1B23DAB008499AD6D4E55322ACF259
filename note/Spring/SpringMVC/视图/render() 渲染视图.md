https://my.oschina.net/alextrz/blog/914898



render()，把model数据填充到视图中

InternalResourceView（从AbstractView中继承）的render()

```javascript
@Override
public void render(@Nullable Map<String, ?> model, HttpServletRequest request,
      HttpServletResponse response) throws Exception {

   // 输出debug级别日志
   if (logger.isDebugEnabled()) {
      logger.debug("View " + formatViewName() +
            ", model " + (model != null ? model : Collections.emptyMap()) +
            (this.staticAttributes.isEmpty() ? "" : ", static attributes " + this.staticAttributes));
   }

   // 创建实际渲染的视图
   Map<String, Object> mergedModel = createMergedOutputModel(model, request, response);
   prepareResponse(request, response);
   // 调用renderMergedOutputModel()实际渲染视图
   renderMergedOutputModel(mergedModel, getRequestToExpose(request), response);
}
```

InternalResourceView的renderMergedOutputModel()

```javascript
@Override
protected void renderMergedOutputModel(
      Map<String, Object> model, HttpServletRequest request, HttpServletResponse response) throws Exception {

   // Expose the model object as request attributes.
   // 将model里的key,value 设置到request中
   exposeModelAsRequestAttributes(model, request);

   // Expose helpers as request attributes, if any.
   // 若存在则将helpers放到request中
   exposeHelpers(request);

   // Determine the path for the request dispatcher.
   // 指定转发的路径
   String dispatcherPath = prepareForRendering(request, response);

   // Obtain a RequestDispatcher for the target resource (typically a JSP).
   // 得到目标资源(典型的是jsp) 的requestDispatcher 
   RequestDispatcher rd = getRequestDispatcher(request, dispatcherPath);
   if (rd == null) {
      throw new ServletException("Could not get RequestDispatcher for [" + getUrl() +
            "]: Check that the corresponding file exists within your web application archive!");
   }

   // If already included or response already committed, perform include, else forward.
   // 如果已经included或者response已经提交，则执行include，否则进行转发
   if (useInclude(request, response)) {
      response.setContentType(getContentType());
      if (logger.isDebugEnabled()) {
         logger.debug("Including [" + getUrl() + "]");
      }
      rd.include(request, response);
   }

   else {
      // Note: The forwarded resource is supposed to determine the content type itself.
      // 注意：转发资源应该自己定义content type
      if (logger.isDebugEnabled()) {
         logger.debug("Forwarding to [" + getUrl() + "]");
      }
      // 转发
      rd.forward(request, response);
   }
}
```

