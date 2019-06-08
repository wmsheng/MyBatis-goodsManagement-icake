package com.imooc.icake.global;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author Bennett_Wang on 2019/6/8
 */
public class LoginFilter implements Filter {
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;
        Object object = request.getSession().getAttribute("ACCOUNT");
        if (object == null){
            response.sendRedirect("/toLogin.do");
        }else {
            filterChain.doFilter(request,response);
        }
    }

    public void destroy() {

    }
}
