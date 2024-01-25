https://segmentfault.com/q/1010000000713614

https://www.cnblogs.com/sablier/p/11099909.html



Cross-site request forgery 跨站请求伪造

form标签不受跨域限制，设置form的action为其他域，就可以携带其他域的cookie



csrf token

访问登录页时请求后端，生成一个token存到session中，并将token返回前端，放到localStorage/sessionStorage/全局变量中（模板可以也放到页面中）

前端表单参数加一个token，通过隐藏域或ajax发送到后端

后端验证表单的token和session中的token是否一致



csrf token也可以放到cookie中，因为csrf可以使用其他域的cookie，但是cookie的内容对攻击者是不可见、无法获取的

后端验证表单和cookie中的token即可

```javascript
//判断令牌
if(token != null && !"".equals(token.trim())){ 
   String token_sessionid = csrfTokenManage.getToken(request);//获取令牌
   if(token_sessionid != null && !"".equals(token_sessionid.trim())){
      if(!token_sessionid.equals(token)){
         error.put("token", ErrorView._13.name());//令牌错误
      }
   }else{
      error.put("token", ErrorView._12.name());//令牌过期
   }
}else{
   error.put("token", ErrorView._11.name());//令牌为空
}
```



```javascript
/**
 * CSRF令牌管理
 *
 */
@Component("csrfTokenManage")
public class CSRFTokenManage {
   
   /**
    * 设置令牌
    * @param request
    * @param response
    */
   public void setToken(HttpServletRequest request,HttpServletResponse response){
      String token = this.getToken(request);
      if(token == null || "".equals(token.trim())){
         String new_token = UUIDUtil.getUUID32();
         //将令牌添加到Cookie
         WebUtil.addCookie(response, "cms_token",new_token , 0);
         CSRFTokenThreadLocal.set(new_token);
      }
   }
   
   /**
    * 获取令牌
    * @param request
    * @return
    */
   public String getToken(HttpServletRequest request){
      //获取token
      return WebUtil.getCookieByName(request, "cms_token");
   }
   /**
    * 删除令牌
    * @param response
    * @return
    */
   public void deleteToken(HttpServletResponse response){
      WebUtil.deleteCookie(response, "cms_token");
   }
}
```

