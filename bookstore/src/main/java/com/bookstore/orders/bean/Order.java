package com.bookstore.orders.bean;

import com.bookstore.products.bean.Product;
import com.bookstore.user.bean.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;
import java.util.List;

/**
 * company: www.abc.com
 * Author: power
 * Create Data: 2019/5/13
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Order {
    private String id;
    private Double money;
    private String receiverAddress;
    private String receiverName;
    private String receiverPhone;
    private String paystate;
    private Timestamp ordertime;
    private Integer user_id;
    private User user;
    private List<Product> products;
}
