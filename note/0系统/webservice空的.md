webservice没有注册 http://127.0.0.1:8088/sofa/services是空的

启动顺序问题

cxf先启动，业务组件启动时再通过osgi调用注册webservice服务，所以开发环境需要空着启动，再手动添加组件部署

另外cxf如果重启，webservice会再次清空