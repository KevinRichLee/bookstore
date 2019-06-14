package com.bookstore.common.utils;

import com.bookstore.user.bean.User;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.PageContext;
import javax.servlet.jsp.tagext.SimpleTagSupport;
import java.io.IOException;

/**
 * company: www.abc.com
 * Author: power
 * Create Data: 2019/4/27
 */
public class AdminLoginTag extends SimpleTagSupport {
    public void doTag() throws JspException, IOException {
        PageContext context = (PageContext) this.getJspContext();
        HttpServletResponse response = (HttpServletResponse) context.getResponse();
        HttpServletRequest request = (HttpServletRequest) context.getRequest();
        User adminLoginUser =(User) context.getSession().getAttribute("loginAdminUser");

        if(adminLoginUser==null){
            response.sendRedirect(request.getContextPath()+"/admin/error/privilege.jsp");
        }
    }
}
