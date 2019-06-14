package com.bookstore.manager.dao;

import com.bookstore.common.utils.PageModel;
import com.bookstore.manager.vo.SearchCon;
import com.bookstore.orders.bean.Order;
import com.bookstore.products.bean.Notice;
import com.bookstore.products.bean.Product;
import com.bookstore.products.bean.ProductList;
import org.apache.ibatis.annotations.Param;
import org.aspectj.weaver.ast.Not;

import java.util.List;

public interface ManagerDao {
    Integer selectCount(@Param("searchCondition")SearchCon searchCondition);

    List<Product> selectAllByCondition(@Param("searchCondition") SearchCon searchCondition,@Param("pageModel") PageModel pageModel);

    Integer deleteProduct(Integer id);

    void insertProduct(Product product);

    void updateProduct(Product product);

    List<ProductList> selectSalesList(@Param("year") String year, @Param("month") String month);

    Integer selectOrderCount(@Param("order")Order order);

    List<Order> selectOrderList(@Param("order") Order order, @Param("pageModel") PageModel pageModel);

    Order selectOrderById(@Param("id")String id);

    void delOrderById(String id);

    List<Notice> selectNoticeList();

    int insertNotice(Notice notice);

    Notice selectNoticeById(Integer id);

    int updateNotice(Notice notice);

    int deleteNoticeById(Integer n_id);
}
