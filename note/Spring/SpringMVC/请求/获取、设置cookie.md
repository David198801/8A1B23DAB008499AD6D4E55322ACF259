@CookieValue

```javascript
@RequestMapping("testCookie")
public String testCookie(
        @CookieValue("JSESSIONID") String sessionid
){
    System.out.println("JSESSIONID="+sessionid);
    return "success";
}
```

@CookieValue("cookie名")修饰Cookie类型可以获取cookie所有信息