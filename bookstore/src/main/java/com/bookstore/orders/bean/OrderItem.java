package com.bookstore.orders.bean;

import com.bookstore.products.bean.Product;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * company: www.abc.com
 * Author: power
 * Create Data: 2019/5/13
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderItem {
    private Order order;
    private Product product;
    private Integer buynum;
}
