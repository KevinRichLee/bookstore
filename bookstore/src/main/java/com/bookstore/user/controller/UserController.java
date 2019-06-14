package com.bookstore.user.controller;

import com.bookstore.common.emum.ExceptionEnum;
import com.bookstore.common.exception.BookStoreException;
import com.bookstore.user.bean.User;
import com.bookstore.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

/**
 * company: www.abc.com
 * Author: KevinLee
 * Create Data: 2019/4/21
 */
@Controller
@RequestMapping("user")
public class UserController {
    @Autowired
    private UserService userService;

    @PostMapping("register.do")
    public String registerUser(User user, String verifiCode, HttpSession session, HttpServletRequest request){
        session.setAttribute("registerUser",user);
        String checkcode_session = (String) session.getAttribute("checkcode_session");
        if (!checkcode_session.equals(verifiCode)){
            session.setAttribute("codeError","验证码不正确");
            return "redirect:/client/register.jsp";
        }
        int count = userService.addUser(user,request);
        if(count!=1){
            throw new BookStoreException(ExceptionEnum.USER_ADD_ERROR);
        }
        return "redirect:/client/registersuccess.jsp";
    }
    @GetMapping("activeCode.do")
    @ResponseBody
    public String activeCode(@RequestParam("activeCode")String activeCode){
        int count = userService.modifyCode(activeCode);
        if(count!=1){
            throw new BookStoreException(ExceptionEnum.ACTIVE_CODE_FILE);
        }
        return "Success!";
    }
    @PostMapping("checkEmail.do")
    @ResponseBody
    public String checkEmail(String email) {
        System.out.println(email);
        if (email != null) {
            User user = userService.checkEmail(email);
            if (user == null) {
                return "OK";
            }
            return "EXIST";
        }
        return "null";

    }
    @PostMapping("checkUsername.do")
    @ResponseBody
    public String checkUsername(String username){
        if (username != null) {
            User user = userService.checkUsername(username);
            if (user == null) {
                return "OK";
            }
            return "EXIST";
        }
        return "null";
    }
    @RequestMapping("myAccount.do")
    public String checkMyAccount(HttpSession session,HttpServletRequest request){
        User user = (User) session.getAttribute("login_user");
        if(user==null){
            autologin(request);
            return "/client/login.jsp";
        }
        return "/client/myAccount.jsp";
    }

    private void autologin(HttpServletRequest request) {
        Cookie[] cookies = request.getCookies();
        for (Cookie cookie : cookies) {
            if("bookstore_username".equals(cookie.getName())){
                try {
                    URLEncoder.encode(cookie.getValue(),"utf-8");
                } catch (UnsupportedEncodingException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    @PostMapping("login.do")
    public String checkLogin(User user, HttpSession session,HttpServletRequest request, Model model,HttpServletResponse response, String remember, String autologin){

        User login_user = userService.checkLoginUser(user);
        if(login_user!=null){
            if(login_user.getState()==1){
                if("1".equals(autologin)){
                    addCookie(autologin,user,response,request);
                }else if("1".equals(remember)){
                    addCookie(remember,user,response,request);
                }
                session.setAttribute("login_user",login_user);
                return "/client/index.jsp";
            }else{
                model.addAttribute("errorLogin","请激活后登录");
                return "/client/login.jsp";
            }

        }
        model.addAttribute("errorLogin","用户名或密码错误请重试");
        return "/client/login.jsp";
    }

    private void addCookie(String autologin, User user,HttpServletResponse response,HttpServletRequest request) {
        Cookie cookie1 = null;
        try {
            cookie1 = new Cookie("bookstore_username", URLEncoder.encode(user.getUsername(),"UTF-8"));
            cookie1.setMaxAge(60*60*24);
            cookie1.setPath(request.getContextPath()+"/");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        response.addCookie(cookie1);
        if("1".equals(autologin)){
            Cookie cookie2 = new Cookie("bookstore_password",user.getPassword());
            cookie2.setMaxAge(60*60*24);
            cookie2.setPath(request.getContextPath()+"/");
            response.addCookie(cookie2);
        }
    }

    @PostMapping("modifyUser.do")
    public String modifyUser(User user){
        int count = userService.modifyUser(user);
        if(count>0){
            return "/client/login.jsp";
        }
        return "";
    }
    @GetMapping("logout.do")
    public String logoutUser(HttpSession session,HttpServletResponse response){
        session.removeAttribute("login_user");
        Cookie cookie1 = new Cookie("bookstore_username",null);
        cookie1.setMaxAge(0);
        response.addCookie(cookie1);
        Cookie cookie2 = new Cookie("bookstore_password",null);
        cookie2.setMaxAge(0);
        response.addCookie(cookie2);
        return "/client/login.jsp";
    }
}
