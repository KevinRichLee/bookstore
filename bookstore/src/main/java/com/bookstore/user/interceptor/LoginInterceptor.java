package com.bookstore.user.interceptor;

import com.bookstore.user.bean.User;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class  LoginInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String uri = request.getRequestURI();
        if(uri.endsWith("login.jsp")||uri.endsWith("login.do")||uri.endsWith("index.jsp")){
            return true;
        }
        User user = (User) request.getSession().getAttribute("loginUser");
        // System.out.println("user="+user);
        if(user==null){
            System.out.println("尚未登录，请重新登录");
            request.setAttribute("error","尚未登录,请重新登录");
//            response.sendRedirect("/hrm_war_exploded/index.jsp");
            request.getRequestDispatcher(request.getContextPath()+"/index.jsp").forward(request,response);
            return false;
        }

        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        // System.out.println("postHandle");
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        // System.out.println("afterCompletion");
    }
}
