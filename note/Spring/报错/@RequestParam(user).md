严重: 在路径为/springm的上下文中，Servlet[DispacherServlet]的Servlet.service（）引发了具有根本原因的异常Request processing failed; nested exception is java.lang.IllegalStateException: An Errors/BindingResult argument is expected to be declared immediately after the model attribute, the @RequestBody or the @RequestPart arguments to which they apply: public java.lang.String spring.handler.SpringMvcTest.testConversion(spring.bean.User,org.springframework.validation.BindingResult)

java.lang.IllegalStateException: An Errors/BindingResult argument is expected to be declared immediately after the model attribute, the @RequestBody or the @RequestPart arguments to which they apply: public java.lang.String spring.handler.SpringMvcTest.testConversion(spring.bean.User,org.springframework.validation.BindingResult)



BindingResult应该声明在model参数或者会被封装到model中的参数后面，另外在自动映射实体类参数上错误添加了@RequestParam也会报这个错误