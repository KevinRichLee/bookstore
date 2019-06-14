package com.bookstore.orders.service;

import com.bookstore.orders.bean.OrderItem;
import com.bookstore.orders.dao.OrderItemDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * company: www.abc.com
 * Author: power
 * Create Data: 2019/5/13
 */
@Service
public class OrderItemServiceImpl implements OrderItemService{
    @Autowired
    private OrderItemDao orderItemDao;

    @Override
    public void insertOrderItem(OrderItem orderItem) {
        orderItemDao.insertOrderItem(orderItem);
    }

    @Override
    public List<OrderItem> findOrderById(String id) {
        return orderItemDao.selectOrderById(id);
    }

    @Override
    public void deleteOrderItemById(String id) {
        orderItemDao.deleteOrderItemById(id);
    }
}
