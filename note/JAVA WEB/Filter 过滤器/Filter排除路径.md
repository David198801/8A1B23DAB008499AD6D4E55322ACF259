使用初始化参数split然后遍历排除

```javascript
<filter>
	<filter-name>LoginFilter</filter-name>
	<filter-class>filter.LoginFilter</filter-class>
	<init-param>
        <param-name>excludedPages</param-name>
        <param-value>/servlet/doLogin,/login.jsp</param-value>
    </init-param>
</filter>
<filter-mapping>
	<filter-name>LoginFilter</filter-name>
	<url-pattern>*.jsp</url-pattern>
	<url-pattern>/servlet/*</url-pattern>
</filter-mapping>
```



```javascript
package filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import entity.User;

public class LoginFilter implements Filter{
	
	private String[] excludedPageArray;
	
	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		//读取初始化参数中的排除路径
		excludedPageArray = filterConfig.getInitParameter("excludedPages").split(",");
	}

	
	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		//获取session，user
		HttpServletRequest req = (HttpServletRequest)request;
		HttpSession session = req.getSession();
		User user = (User)session.getAttribute("user");
		

		System.out.println(req.getServletPath());
		System.out.println(req.getRequestURI());
		System.out.println(req.getRequestURL());
		
		//遍历判断是否为登录路径
		String uri = req.getServletPath();
		boolean isLoginPath = false;
		for (String path : excludedPageArray) {
			if(path.equals(uri)) {
				isLoginPath = true;
				break;
			}
		}
		
		//登录路径或已登录则放行
		if(null!=user || isLoginPath) {
			chain.doFilter(request, response);
		}else {
			((HttpServletResponse)response).sendRedirect(req.getContextPath()+"/login.jsp");
			//req.getRequestDispatcher("/login.jsp").forward(request, response);
		}
		
	}
	
	@Override
	public void destroy() {
		// TODO Auto-generated method stub
		
	}

	

}

```

