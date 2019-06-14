package com.bookstore.manager.service;

import com.bookstore.common.utils.PageModel;
import com.bookstore.manager.dao.ManagerDao;
import com.bookstore.manager.vo.SearchCon;
import com.bookstore.orders.bean.Order;
import com.bookstore.products.bean.Notice;
import com.bookstore.products.bean.Product;
import com.bookstore.products.bean.ProductList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ManagerServiceImpl implements ManagerService {
    @Autowired
    private ManagerDao managerDao;
    @Override
    public Integer selectCount(SearchCon searchCondition) {
        return managerDao.selectCount(searchCondition);
    }

    @Override
    public List<Product> findAllByCondition(SearchCon searchCondition, PageModel pageModel) {
        return managerDao.selectAllByCondition(searchCondition,pageModel);
    }

    @Override
    public Integer deleteProduct(Integer id) {
        return managerDao.deleteProduct(id);
    }

    @Override
    public void addProduct(Product product) {
        managerDao.insertProduct(product);
    }

    @Override
    public void updateProduct(Product product) {
        managerDao.updateProduct(product);
    }

    @Override
    public List<ProductList> querySalesList(String year, String month) {
        return managerDao.selectSalesList(year,month);
    }

    @Override
    public Integer selectOrderCount(Order order) {
        return managerDao.selectOrderCount(order);
    }

    @Override
    public List<Order> queryOrderList(Order order, PageModel pageModel) {
        return managerDao.selectOrderList(order,pageModel);
    }

    @Override
    public Order queryOrderById(String id) {
        return managerDao.selectOrderById(id);
    }

    @Override
    public void delOrderById(String id) {
        managerDao.delOrderById(id);
    }

    @Override
    public List<Notice> queryNoticeList() {
        return managerDao.selectNoticeList();
    }

    @Override
    public int addNotice(Notice notice) {
        return managerDao.insertNotice(notice);
    }

    @Override
    public Notice queryNoticeById(Integer id) {
        return managerDao.selectNoticeById(id);
    }

    @Override
    public int modifyNotice(Notice notice) {
        return managerDao.updateNotice(notice);
    }

    @Override
    public int deleteNoticeById(Integer n_id) {
        return managerDao.deleteNoticeById(n_id);
    }
}
