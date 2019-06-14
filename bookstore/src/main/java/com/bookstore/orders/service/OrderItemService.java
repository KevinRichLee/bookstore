package com.bookstore.orders.service;

import com.bookstore.orders.bean.OrderItem;

import java.util.List;

/**
 * company: www.abc.com
 * Author: power
 * Create Data: 2019/5/13
 */
public interface OrderItemService {

    void insertOrderItem(OrderItem orderItem);

    List<OrderItem> findOrderById(String id);

    void deleteOrderItemById(String id);
}
