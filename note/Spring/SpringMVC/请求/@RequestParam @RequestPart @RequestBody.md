https://blog.csdn.net/wd2014610/article/details/79727061/

https://blog.csdn.net/zy1471162851/article/details/88702936



@RequestBody注解常用来处理content-type不是默认的application/x-www-form-urlcoded编码的内容，比如说：application/json或者是application/xml等。一般情况下来说常用其来处理application/json类型。



@RequestParam和@RequestPart 

1.@RequestPart这个注解用在multipart/form-data表单提交请求的方法上。 

2.支持的请求方法的方式MultipartFile，属于Spring的MultipartResolver类。这个请求是通过http协议传输的。 

3.@RequestParam也同样支持multipart/form-data请求。 

4.他们最大的不同是，当请求方法的请求参数类型不再是String类型的时候。 

5.@RequestParam适用于name-valueString类型的请求域，@RequestPart适用于复杂的请求域（像JSON，XML）



字符串用@RequestParam

json用@RequestBody

文件用@RequestParam或@RequestPart