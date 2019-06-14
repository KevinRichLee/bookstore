package com.bookstore.user.service;

import com.bookstore.user.bean.User;

import javax.servlet.http.HttpServletRequest;

/**
 * company: www.abc.com
 * Author: KevinLee
 * Create Data: 2019/4/21
 */
public interface UserService {
    int addUser(User user, HttpServletRequest request);

    int modifyCode(String name);

    User checkEmail(String email);

    User checkUsername(String username);

    User checkLoginUser(User user);

    int modifyUser(User user);
}
