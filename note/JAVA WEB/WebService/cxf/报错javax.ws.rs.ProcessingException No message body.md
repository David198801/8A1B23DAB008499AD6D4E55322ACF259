javax.ws.rs.ProcessingException: No message body writer has been found for class com.lzb.cxf.rs.client.User, ContentType: application/json



出现这种问题的原因是在webService间传递的对象不能json化,只需要在传递的对象上加注解:@XmlRootElement(name = "User")