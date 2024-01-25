如分页中，前台分页和后台分页，用到了不同的servlet链接

/client/bookServlet?action=page

/manager/bookServlet?action=page

无法直接提取为一个jsp

可以将链接提取为Page类的属性，在对应manager和client的servlet中set

jsp中的链接从page对象中获取