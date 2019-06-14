package com.bookstore.user.bean;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * company: www.abc.com
 * Author: KevinLee
 * Create Data: 2019/4/21
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class User {
    private Integer id;
    private String username;
    private String password;
    private String gender;
    private String email;
    private String telephone;
    private String introduce;
    private String activeCode;
    private Integer state;
    private String role;
}
