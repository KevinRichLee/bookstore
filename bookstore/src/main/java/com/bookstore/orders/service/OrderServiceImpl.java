package com.bookstore.orders.service;

import com.bookstore.orders.bean.Order;
import com.bookstore.orders.dao.OrderDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * company: www.abc.com
 * Author: power
 * Create Data: 2019/5/13
 */
@Service
public class OrderServiceImpl implements OrderService {
    @Autowired
    private OrderDao orderDao;

    @Override
    public void insertOrder(Order order) {
        orderDao.insertOrder(order);
    }

    @Override
    public void updatePayState(String order_id) {
        orderDao.updatePayState(order_id);
    }

    @Override
    public List<Order> findOrderByUser(Integer id) {
        return orderDao.findOrderByUser(id);
    }

    @Override
    public Integer selectCount(Integer id) {
        return orderDao.selectCount(id);
    }

    @Override
    public void deleteOrderById(String id) {
        orderDao.deleteOrderById(id);
    }
}
