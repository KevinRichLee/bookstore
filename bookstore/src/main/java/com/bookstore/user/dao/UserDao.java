package com.bookstore.user.dao;

import com.bookstore.user.bean.User;
import org.apache.ibatis.annotations.Mapper;

/**
 * company: www.abc.com
 * Author: KevinLee
 * Create Data: 2019/4/21
 */
@Mapper
public interface UserDao {
    int addUser(User user);

    int updateCode(String name);

    User selectEmail(String email);

    User selectUsername(String username);

    User checkLoginUser(User user);

    int updateUser(User user);
}
