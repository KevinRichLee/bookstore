package com.bookstore.common.interceptor;

import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

public class AdminLoginInterceptor extends HandlerInterceptorAdapter {
    // @Override
    // public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
    //     String uri = request.getRequestURI();
    //     if (uri.endsWith("login.do") || uri.endsWith("login.jsp")||uri.endsWith("")) {
    //         return true;
    //     }
    //     User adminUser = (User) request.getSession().getAttribute("loginAdminUser");
    //     if (adminUser != null) {
    //         return true;
    //     }else {
    //         request.setAttribute("error", "权限不足,请登录后访问");
    //         request.getRequestDispatcher(request.getContextPath() + "/admin/error/privilege.jsp").forward(request, response);
    //     }
    //     return false;
    // }
}
