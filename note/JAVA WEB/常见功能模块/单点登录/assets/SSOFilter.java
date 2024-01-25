package com.book.filter;

import com.book.bean.User;
import com.book.service.UserService;
import com.book.service.impl.UserServiseImpl;
import com.book.utils.HttpUtils;
import com.book.utils.StringUtils;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;

public class SSOFilter implements Filter {
    UserService service = new UserServiseImpl();


    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;
        String code = servletRequest.getParameter("code");

        if(request.getSession().getAttribute("user")==null){
            if(StringUtils.isNotEmpty(code)){
                getAccessToken(code, request);

                User user = service.loginByUsername(code);
                if(user!=null){
                    request.getSession().setAttribute("user",user);
                }
            }else{
                String redirectUrl = "http://server.smart-sso.com:8081/login?appId=demo1&redirectUri=";
                redirectUrl += URLEncoder.encode(getCurrentUrl(request), "utf-8");
                response.sendRedirect(redirectUrl);
                return;
            }
        }
        filterChain.doFilter(servletRequest,servletResponse);
    }

    private void getAccessToken(String code, HttpServletRequest request) {
        String url = "http://server.smart-sso.com:8081/oauth2/access_token";
        Map<String, String> paramMap = new HashMap<>();
        paramMap.put("grantType", "authorization_code");
        paramMap.put("appId", "demo1");
        paramMap.put("appSecret", "123456");
        paramMap.put("code", code);
        String jsonStr = HttpUtils.get(url, paramMap);

        ObjectMapper objectMapper = new ObjectMapper();
        try {
            Map<String,Object> map = objectMapper.readValue(jsonStr, Map.class);

            Map<String,Object> dataMap = (Map<String, Object>) map.get("data");

            Map<String,String> userMap = (Map<String, String>) dataMap.get("user");

            String username = userMap.get("username");

            loginByUsername(request,username);

        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
    }

    private void loginByUsername(HttpServletRequest request, String username) {
        User user = service.loginByUsername(username);
        if(user!=null){
            request.getSession().setAttribute("user",user);
        }
    }

    private String getCurrentUrl(HttpServletRequest request) {
        return new StringBuilder().append(request.getRequestURL())
                .append(request.getQueryString() == null ? "" : "?" + request.getQueryString()).toString();
    }

    @Override
    public void destroy() {

    }
}
