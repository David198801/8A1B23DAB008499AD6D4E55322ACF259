

[DispatcherServlet.java](assets/DispatcherServlet.java)

https://www.jianshu.com/p/8a20c547e245

![](assets/0%20DispatcherServlet_image_0.png)

https://blog.csdn.net/qq_42154259/article/details/107242751

继承父类FrameworkServlet的方法

0.service()检查请求方式，调用HttpServlet的service()或processRequest()

1.doGet()和doPost()等都调用processRequest()	

2.processRequest()调用doService()

DispatcherServlet实现的方法

3.doService()调用doDispatch()

...

doDispatch()

```javascript
protected void doDispatch(HttpServletRequest request, HttpServletResponse response) throws Exception {
	HttpServletRequest processedRequest = request;
	HandlerExecutionChain mappedHandler = null;
	boolean multipartRequestParsed = false;

	WebAsyncManager asyncManager = WebAsyncUtils.getAsyncManager(request);

	try {
		ModelAndView mv = null;
		Exception dispatchException = null;

		try {
			// 检查当前请求是不是一个MultipartHttpServletRequest请求，（文件上传请求）
			processedRequest = checkMultipart(request);
			// 将是不是文件请求赋值给multipartRequestParsed 
			multipartRequestParsed = (processedRequest != request);

			// 确定当前请求程序，也就是根据请求类型，返回不同的handler。
			// getHandler()中遍历属性handlerMappings检查是否有HandlerMapping映射，有则调用mapping.getHandler()返回一个HandlerExecutionChain，无则返回null
			// HandlerExecutionChain有Object handler属性(运行时handler方法封装为HandlerMethod类赋在该属性上，HandlerMethod封装了反射中的Method类)，和interceptors属性(各个拦截器)
			// 若配置了<mvc:default-servlet-handler/>则，没有handler方法映射时使用tomcat默认的servlet当做静态资源处理，mappedHandler不会为null
			mappedHandler = getHandler(processedRequest);
			if (mappedHandler == null) {
				// 如果当前请求没有handler进行处理，那么就抛出404异常
				noHandlerFound(processedRequest, response);
				return;
			}

			// 将mappedHandler.getHandler()返回一个handler，将其传入getHandlerAdapter(),获取一个适配器
			HandlerAdapter ha = getHandlerAdapter(mappedHandler.getHandler());

			// 如果
			String method = request.getMethod();
			boolean isGet = "GET".equals(method);
			if (isGet || "HEAD".equals(method)) {
				long lastModified = ha.getLastModified(request, mappedHandler.getHandler());
				if (new ServletWebRequest(request, response).checkNotModified(lastModified) && isGet) {
					return;
				}
			}

			// 遍历调用拦截器中断prehandle()，返回false则本方法return
			if (!mappedHandler.applyPreHandle(processedRequest, response)) {
				return;
			}

			// 使用适配器ha，实际调用handler方法。将request,response和从HandlerExecutionChain mappedHandler中获取的handler传入，返回一个ModelAndView
			mv = ha.handle(processedRequest, response, mappedHandler.getHandler());

			if (asyncManager.isConcurrentHandlingStarted()) {
				return;
			}
			// 如果视图不为null，并且没有视图名称，并且有默认的视图名称，那么将视图的名称设置为默认的视图名称
			applyDefaultViewName(processedRequest, mv);
			// 遍历执行拦截器中的posthandle()
			mappedHandler.applyPostHandle(processedRequest, response, mv);
		} catch(Exception ex) {
			dispatchException = ex;
		} catch(Throwable err) {
			// As of 4.3, we're processing Errors thrown from handler methods as well,
			// making them available for @ExceptionHandler methods and other scenarios.
			dispatchException = new NestedServletException("Handler dispatch failed", err);
		}
		// 调用processDispatchResult()处理视图
		processDispatchResult(processedRequest, response, mappedHandler, mv, dispatchException);
	} catch(Exception ex) {
		triggerAfterCompletion(processedRequest, response, mappedHandler, ex);
	} catch(Throwable err) {
		triggerAfterCompletion(processedRequest, response, mappedHandler, new NestedServletException("Handler processing failed", err));
	} finally {
		if (asyncManager.isConcurrentHandlingStarted()) {
			// Instead of postHandle and afterCompletion
			if (mappedHandler != null) {
				mappedHandler.applyAfterConcurrentHandlingStarted(processedRequest, response);
			}
		} else {
			// Clean up any resources used by a multipart request.
			if (multipartRequestParsed) {
				cleanupMultipart(processedRequest);
			}
		}
	}
}
```

processDispatchResult()

```javascript
private void processDispatchResult(HttpServletRequest request, HttpServletResponse response, @Nullable HandlerExecutionChain mappedHandler, @Nullable ModelAndView mv, @Nullable Exception exception) throws Exception {

	boolean errorView = false;
	// 检查是否有异常
	if (exception != null) {
		if (exception instanceof ModelAndViewDefiningException) {
			logger.debug("ModelAndViewDefiningException encountered", exception);
			mv = ((ModelAndViewDefiningException) exception).getModelAndView();
		} else {
			Object handler = (mappedHandler != null ? mappedHandler.getHandler() : null);
			// 调用processHandlerException()处理异常，返回一个新的ModelAndView
			mv = processHandlerException(request, response, handler, exception);
			errorView = (mv != null);
		}
	}

	// 对ModelAndView判null，并检查ModelAndView中是否有view需要渲染
	if (mv != null && !mv.wasCleared()) {
		// 调用render()渲染视图，（即将模型数据model填充至视图中）
		render(mv, request, response);
		if (errorView) {
			WebUtils.clearErrorRequestAttributes(request);
		}
	} else {
		if (logger.isTraceEnabled()) {
			logger.trace("No view rendering, null ModelAndView returned.");
		}
	}

	if (WebAsyncUtils.getAsyncManager(request).isConcurrentHandlingStarted()) {
		// Concurrent handling started during a forward
		return;
	}

	if (mappedHandler != null) {
		// Exception (if any) is already handled..
		mappedHandler.triggerAfterCompletion(request, response, null);
	}
}
```

processHandlerException()

```javascript
@Nullable
protected ModelAndView processHandlerException(HttpServletRequest request, HttpServletResponse response, @Nullable Object handler, Exception ex) throws Exception {

	// Success and error responses may use different content types
	request.removeAttribute(HandlerMapping.PRODUCIBLE_MEDIA_TYPES_ATTRIBUTE);

	// 检查注册了的HandlerExceptionResolvers
	ModelAndView exMv = null;
	if (this.handlerExceptionResolvers != null) {
		for (HandlerExceptionResolver resolver: this.handlerExceptionResolvers) {
			// 使用HandlerExceptionResolver处理异常
			exMv = resolver.resolveException(request, response, handler, ex);
			if (exMv != null) {
				break;
			}
		}
	}
	if (exMv != null) {
		if (exMv.isEmpty()) {
			request.setAttribute(EXCEPTION_ATTRIBUTE, ex);
			return null;
		}
		// We might still need view name translation for a plain error model...
		if (!exMv.hasView()) {
			String defaultViewName = getDefaultViewName(request);
			if (defaultViewName != null) {
				exMv.setViewName(defaultViewName);
			}
		}
		if (logger.isTraceEnabled()) {
			logger.trace("Using resolved error view: " + exMv, ex);
		} else if (logger.isDebugEnabled()) {
			logger.debug("Using resolved error view: " + exMv);
		}
		WebUtils.exposeErrorRequestAttributes(request, ex, getServletName());
		return exMv;
	}

	throw ex;
}
```

render()

```javascript
protected void render(ModelAndView mv, HttpServletRequest request, HttpServletResponse response) throws Exception {
	// Determine locale for request and apply it to the response.
	Locale locale = (this.localeResolver != null ? this.localeResolver.resolveLocale(request) : request.getLocale());
	response.setLocale(locale);

	View view;
	String viewName = mv.getViewName();
	if (viewName != null) {
		// 调用resolveViewName()，解析ModelAndView的viewName属性，获取视图
		// resolveViewName()中遍历viewResolvers获取视图解析器，调用每个解析器的viewResolver.resolveViewName()，如果不返回null则返回其返回值
		view = resolveViewName(viewName, mv.getModelInternal(), locale, request);
		if (view == null) {
			throw new ServletException("Could not resolve view with name '" + mv.getViewName() + "' in servlet with name '" + getServletName() + "'");
		}
	} else {
		// No need to lookup: the ModelAndView object contains the actual View object.
		view = mv.getView();
		if (view == null) {
			throw new ServletException("ModelAndView [" + mv + "] neither contains a view name nor a " + "View object in servlet with name '" + getServletName() + "'");
		}
	}

	// Delegate to the View object for rendering.
	if (logger.isTraceEnabled()) {
		logger.trace("Rendering view [" + view + "] ");
	}
	try {
		if (mv.getStatus() != null) {
			response.setStatus(mv.getStatus().value());
		}
		// 调用view.render()渲染视图，在AbstrctView的render()中调用了renderMergedOutputModel()，其中进行了转发
		view.render(mv.getModelInternal(), request, response);
	} catch(Exception ex) {
		if (logger.isDebugEnabled()) {
			logger.debug("Error rendering view [" + view + "]", ex);
		}
		throw ex;
	}
}
```

