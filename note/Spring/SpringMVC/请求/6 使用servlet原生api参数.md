handler方法可以接受这些servlet参数

HttpServletRequest

HttpServletResponse

HttpSession

java.security.Principal

Locale

InputStream

OutputStream

Reader

Writer



```javascript
@RequestMapping("testServlet")
public String testServlet(HttpServletRequest req, HttpServletResponse resp){
    System.out.println(req);
    //org.apache.catalina.connector.RequestFacade@2848b76f
    System.out.println(resp);
    // org.apache.catalina.connector.ResponseFacade@2de8aa90
    return "success";
}
```

