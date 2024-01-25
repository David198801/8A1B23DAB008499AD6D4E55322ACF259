SpringMVC IOC容器中的bean可以引用Spring IOC容器中的bean，反之则不行



ContextLoaderListener使用XmlWebApplicationContext创建Spring IOC容器，而DispatcherServlet中创建SpringMVC IOC容器，并Spring IOC容器设为SpringMVC IOC容器的父容器

https://blog.csdn.net/dhaiuda/article/details/80026354



spring多个容器之间可以设置为父子关系，以实现良好的解耦

