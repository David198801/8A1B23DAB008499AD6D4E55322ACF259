1.Filter类检查session中用户信息

```javascript
package com.book.filter;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class ManagerFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {

        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpSession session = request.getSession();
        if (null == session.getAttribute("username")) {
            ((HttpServletResponse) servletResponse).sendRedirect(request.getContextPath() + "/pages/user/login.jsp");
        } else {
            filterChain.doFilter(servletRequest, servletResponse);
        }
    }

    @Override
    public void destroy() {

    }
}
```

2.xml配置后台目录，注意页面和servlet都要过滤

```javascript
<filter>
    <filter-name>ManagerFilter</filter-name>
    <filter-class>com.book.filter.ManagerFilter</filter-class>
</filter>
<filter-mapping>
    <filter-name>ManagerFilter</filter-name>
    <url-pattern>/pages/manager/*</url-pattern>
    <url-pattern>/manager/bookServlet</url-pattern>
</filter-mapping>
```

