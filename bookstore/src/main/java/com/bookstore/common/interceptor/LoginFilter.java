package com.bookstore.common.interceptor;

import com.bookstore.user.bean.User;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * company: www.abc.com
 * Author: power
 * Create Data: 2019/5/24
 */

public class LoginFilter implements Filter {
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        //如果用户访问login.jsp或login.do能直接访问；
        //访问其他页面或处理器方法只能用户登录访问；
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;
        String uri=request.getRequestURI();
        if(uri.endsWith("login.jsp")||uri.endsWith("login.do")){
            /*filterChain.doFilter(reqeust,response)和 filterChain.doFilter(servletRequest,servletResponse);
            有区别吗？             */
            filterChain.doFilter(servletRequest,servletResponse);
        }else{
            User loginAdminUser= (User) request.getSession().getAttribute("loginAdminUser");
            if(loginAdminUser!=null){
                if("超级管理员".equals(loginAdminUser.getRole())){
                    filterChain.doFilter(request,response);
                }else{
                    //请求转发
                     System.out.println("路径："+request.getContextPath());
                    response.sendRedirect(request.getContextPath()+"/admin/error/privilege.jsp");
                }
            }else{
                response.sendRedirect(request.getContextPath()+"/admin/error/privilege.jsp");
            }
        }
    }

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void destroy() {

    }
}
