package com.bookstore.products.service;

import com.bookstore.common.utils.PageModel;
import com.bookstore.products.bean.Notice;
import com.bookstore.products.bean.Product;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * company: www.abc.com
 * Author: KevinLee
 * Create Data: 2019/4/27
 */
public interface ProductService {
    List<Product> queryByCategory(String category, PageModel pageModel);

    List<Product> queryProductByName(String name,PageModel pageModel);

    Integer selectRecordCount(String category);

    Integer selectRecordCountName(String name);

    Product queryProductById(String id);

    void updateNum(@Param("pnum") Integer pnum, @Param("id") String id);

    void updateNumAdd(Integer integer, String id);

    Notice queryRecentNotice();

    List<Product> queryWeekHotProdects();
}
