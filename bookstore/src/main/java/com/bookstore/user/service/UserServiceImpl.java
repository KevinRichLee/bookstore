package com.bookstore.user.service;

import com.bookstore.common.utils.ActiveCodeUtils;
import com.bookstore.common.utils.MailUtils;
import com.bookstore.user.bean.User;
import com.bookstore.user.dao.UserDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import javax.servlet.http.HttpServletRequest;

/**
 * company: www.abc.com
 * Author: KevinLee
 * Create Data: 2019/4/21
 */
@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserDao userDao;
    @Override
    public int addUser(User user, HttpServletRequest request) {
        user.setActiveCode(ActiveCodeUtils.createActiveCode());
        System.out.println(user);
        String emailMsg = "请点击<a href='http://localhost:65511"
                +request.getContextPath()+"/user/activeCode.do?activeCode="+user.getActiveCode()+"'>激活</a>登录领奖！";
        try {
            MailUtils.sendMail(user.getEmail(),emailMsg);

        } catch (MessagingException e) {
            e.printStackTrace();
        }
        return userDao.addUser(user);
    }

    @Override
    public int modifyCode(String name) {
        return userDao.updateCode(name);
    }

    @Override
    public User checkEmail(String email) {
        return userDao.selectEmail(email);
    }

    @Override
    public User checkUsername(String username) {
        return userDao.selectUsername(username);
    }

    @Override
    public User checkLoginUser(User user) {
        return userDao.checkLoginUser(user);
    }

    @Override
    public int modifyUser(User user) {
        return userDao.updateUser(user);
    }
}
