package com.bookstore.orders.service;

import com.bookstore.orders.bean.Order;

import java.util.List;

/**
 * company: www.abc.com
 * Author: power
 * Create Data: 2019/5/13
 */
public interface OrderService {
    void insertOrder(Order order);

    void updatePayState(String order_id);

    List<Order> findOrderByUser(Integer id);

    Integer selectCount(Integer id);

    void deleteOrderById(String id);
}
