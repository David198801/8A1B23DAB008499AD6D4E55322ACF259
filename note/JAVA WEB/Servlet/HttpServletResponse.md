每次请求，都会有一个HttpServletResponse指向的对象用于响应



常用方法：



两个输出流:

resp.getOutPutStream()

resp.getWriter()

只能使用一个，不能同时使用



resp.setCharacterEncoding(“UTF-8”) ，设置字符集

resp.setHeader(“Content-Type”,"text/html; charset=UTF-8")，设置响应头，以类型+字符集为例



直接设置Content-Type，此方法不用再调用setCharacterEncoding()设置字符集，但必须在获取流之前设置

resp.setContentType("text/html; charset=UTF-8")



resp.setStatus(302)，设置响应码



resp.sendRedirect()，重定向