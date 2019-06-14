package com.bookstore.orders.dao;

import com.bookstore.orders.bean.Order;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * company: www.abc.com
 * Author: power
 * Create Data: 2019/5/13
 */
public interface OrderDao {
    void insertOrder(Order order);

    void updatePayState(@Param("id") String id);

    List<Order> findOrderByUser(@Param("id") Integer id);

    Integer selectCount(Integer id);

    void deleteOrderById(String id);
}
