WARN  --> [SOFA-062862C]：未找到可用的jms服务，因为在sofa_home/global/message-queue.xml中禁用了jms服务[enabled="false"]

处理方法：

	如果需要使用jms需要将sofa_home/global/message-queue.xml中的配置属性enabled设置为true［enabled="true"］并且需要确保对应的消息中间件为启动状态。



