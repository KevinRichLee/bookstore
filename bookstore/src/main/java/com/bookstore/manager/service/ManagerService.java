package com.bookstore.manager.service;

import com.bookstore.common.utils.PageModel;
import com.bookstore.manager.vo.SearchCon;
import com.bookstore.orders.bean.Order;
import com.bookstore.products.bean.Notice;
import com.bookstore.products.bean.Product;
import com.bookstore.products.bean.ProductList;

import java.util.List;

public interface ManagerService {
    Integer selectCount(SearchCon searchCondition);

    List<Product> findAllByCondition(SearchCon searchCondition, PageModel pageModel);

    Integer deleteProduct(Integer id);

    void addProduct(Product product);

    void updateProduct(Product product);

    List<ProductList> querySalesList(String year, String month);

    Integer selectOrderCount(Order order);

    List<Order> queryOrderList(Order order, PageModel pageModel);

    Order queryOrderById(String id);

    void delOrderById(String id);

    List<Notice> queryNoticeList();

    int addNotice(Notice notice);

    Notice queryNoticeById(Integer id);

    int modifyNotice(Notice notice);

    int deleteNoticeById(Integer n_id);
}
