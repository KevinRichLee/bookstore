package com.bookstore.products.service;

import com.bookstore.common.utils.PageModel;
import com.bookstore.products.bean.Notice;
import com.bookstore.products.bean.Product;
import com.bookstore.products.dao.ProductDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * company: www.abc.com
 * Author: KevinLee
 * Create Data: 2019/4/27
 */
@Service
public class ProductServiceImpl implements ProductService {
    @Autowired
    private ProductDao productDao;
    @Override
    public List<Product> queryByCategory(String category, PageModel pageModel) {
        return productDao.selectByCategory(category,pageModel);
    }

    @Override
    public List<Product> queryProductByName(String name,PageModel pageModel) {
        return productDao.selectProductByName(name,pageModel);
    }

    @Override
    public Integer selectRecordCount(String category) {
        return productDao.selectCount(category);
    }

    @Override
    public Integer selectRecordCountName(String name) {
        return productDao.selectCountName(name);
    }

    @Override
    public Product queryProductById(String id) {
        return productDao.selectProductById(id);
    }

    @Override
    public void updateNum(Integer num,String id) {
        productDao.updateNum(num,id);
    }

    @Override
    public void updateNumAdd(Integer pnum, String id) {
        productDao.updateNumAdd(pnum,id);
    }

    @Override
    public Notice queryRecentNotice() {
        return productDao.selectRecentNotice();
    }

    @Override
    public List<Product> queryWeekHotProdects() {
        return productDao.selectWeekHotProdects();
    }
}
