package com.bookstore.orders.dao;

import com.bookstore.orders.bean.OrderItem;

import java.util.List;

/**
 * company: www.abc.com
 * Author: power
 * Create Data: 2019/5/13
 */
public interface OrderItemDao {
    void insertOrderItem(OrderItem orderItem);

    List<OrderItem> selectOrderById(String id);

    void deleteOrderItemById(String order_id);
}
