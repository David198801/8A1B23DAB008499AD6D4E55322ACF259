commons-fileupload 中文乱码问题的解决

1.原页面我用utf-8编码

2.设置一个filter,统一用utf-8

3.文件域乱码解决:

upload.setHeaderEncoding("UTF-8");

或

String encode=request.getCharacterEncoding();

ServletFileUpload upload = new ServletFileUpload(factory);

upload.setHeaderEncoding(encoding);



4.表单域乱码解决:

item.getString("UTF-8");