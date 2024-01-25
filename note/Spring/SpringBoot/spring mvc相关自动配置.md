https://docs.spring.io/spring-boot/docs/current/reference/html/spring-boot-features.html#boot-features-spring-mvc-auto-configuration







Spring Boot为Spring MVC提供了自动配置，可与大多数应用程序一起很好地工作。

自动配置在Spring的默认值之上添加了以下功能：

- 包含ContentNegotiatingViewResolver和BeanNameViewResolver。

- 支持服务静态资源，包括对WebJars的支持（在本文档的后面部分中有介绍）。

- 自动注册Converter，GenericConverter和Formatter豆类。

- 支持HttpMessageConverters（在本文档后面介绍）。

- 自动注册MessageCodesResolver（在本文档后面介绍）。

- 静态index.html支持。

- 自动使用ConfigurableWebBindingInitializerbean（在本文档后面部分中介绍）。



如果要保留这些Spring Boot MVC定制并进行更多的MVC定制（拦截器，格式化程序，视图控制器和其他功能），则可以添加自己@Configuration的type类，WebMvcConfigurer但不添加 @EnableWebMvc。

如果你想提供的定制情况RequestMappingHandlerMapping，RequestMappingHandlerAdapter或者ExceptionHandlerExceptionResolver，仍然保持弹簧引导MVC自定义，你可以声明类型的豆WebMvcRegistrations，并用它来提供这些组件的定制实例。

如果你想利用Spring MVC中的完全控制，你可以添加自己的@Configuration注解为@EnableWebMvc，或者添加自己的@Configuration-annotatedDelegatingWebMvcConfiguration中的Javadoc中所述@EnableWebMvc。



！！

Spring MVC使用ConversionService与用于从您的application.properties或application.yaml文件转换值的方法不同的方法。这意味着Period，Duration和DataSize转换器不可用，并且@DurationUnit和@DataSizeUnit注释将被忽略。

如果要定制ConversionServiceSpring MVC使用的，可以为WebMvcConfigurerBean提供一个addFormatters方法。通过此方法，您可以注册所需的任何转换器，也可以委托给上可用的静态方法ApplicationConversionService。