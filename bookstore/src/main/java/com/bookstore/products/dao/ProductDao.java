package com.bookstore.products.dao;

import com.bookstore.common.utils.PageModel;
import com.bookstore.products.bean.Notice;
import com.bookstore.products.bean.Product;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * company: www.abc.com
 * Author: KevinLee
 * Create Data: 2019/4/27
 */
@Mapper
public interface ProductDao {
    List<Product> selectByCategory(@Param("category") String category, @Param("pageModel") PageModel pageModel);

    List<Product> selectProductByName(@Param("name")String name,@Param("pageModel") PageModel pageModel);

    Integer selectCount(@Param("category") String category);

    Integer selectCountName(@Param(("name")) String name);

    Product selectProductById(@Param(("id"))String id);

    void updateNum(@Param("pnum") Integer pnum,@Param("id") String id);

    void updateNumAdd(@Param("pnum") Integer pnum, @Param("id") String id);

    Notice selectRecentNotice();

    List<Product> selectWeekHotProdects();
}
