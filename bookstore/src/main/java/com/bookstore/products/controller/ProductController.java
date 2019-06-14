package com.bookstore.products.controller;

import com.bookstore.common.emum.ExceptionEnum;
import com.bookstore.common.exception.BookStoreException;
import com.bookstore.common.utils.PageModel;
import com.bookstore.products.bean.Notice;
import com.bookstore.products.bean.Product;
import com.bookstore.products.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
     * company: www.abc.com
     * Author: KevinLee
     * Create Data: 2019/4/27
     */
    @Controller
    @RequestMapping("client")
    public class ProductController {
        @Autowired
        private ProductService productService;

        @GetMapping("product_list.do")
        public String categorySearch(@RequestParam(defaultValue = "1")Integer pageIndex,@RequestParam(value = "category",required = false)String category,
                                     Model model){
            PageModel pageModel = new PageModel();
            pageModel.setPageIndex(pageIndex);
            Integer totalCount = productService.selectRecordCount(category);
            pageModel.setRecordCount(totalCount);
            List<Product> products =  productService.queryByCategory(category,pageModel);
            System.out.println(products);
            model.addAttribute("products",products);
            model.addAttribute("category",category);
            model.addAttribute("pageModel",pageModel);
            return "/client/product_list.jsp";
        }
        @GetMapping("search/product.do")
        public String searchProduct(@RequestParam(defaultValue = "1")Integer pageIndex,String name,Model model){
                PageModel pageModel = new PageModel();
                pageModel.setPageIndex(pageIndex);
                Integer totalCount = productService.selectRecordCountName(name);
                pageModel.setRecordCount(totalCount);
                model.addAttribute("name",name);
                System.out.println(name);
                List<Product> products = productService.queryProductByName(name,pageModel);
                model.addAttribute("products",products);
                model.addAttribute("pageModel",pageModel);
                return "/client/product_list.jsp";
            }
        @GetMapping("findProductById.do")
        public String findProduct(@RequestParam("id")String id,Model model){
               Product product =  productService.queryProductById(id);
               if(product==null){
                   throw new BookStoreException(ExceptionEnum.SELECT_PRODUCTS_IS_NOT_FOUND);
               }
               model.addAttribute("p",product);
               return "/client/info.jsp";
            }
        @GetMapping("addCart.do")
        public String addCart(@RequestParam("id")String id, HttpSession session){
            Product product = productService.queryProductById(id);
            Map<Product,Integer> cart = (Map<Product, Integer>) session.getAttribute("cart");

            if(CollectionUtils.isEmpty(cart)){
                cart=new HashMap<Product,Integer>();

            }
            Integer count =cart.put(product, 1);
            if(count!=null){
                cart.put(product, count+1);
            }
            // else if((product).equals(cart.get(product))){
            //     System.out.println(cart.get(product));
            //     cart.put(product, cart.get(product).intValue()+1);
            // }
            session.setAttribute("cart", cart);
            return "/client/cart.jsp";
    }
    @RequestMapping("changeCart.do")
    public String changeBookNum(String id,Integer count,
                                HttpSession session){
        System.out.println(id+",,"+count);
        Product product = productService.queryProductById(id);
        Map<Product,Integer> cart = (Map<Product, Integer>) session.getAttribute("cart");
        if(count==0){
            System.out.println("kkk");
            cart.remove(product);
        }else{
            cart.put(product,count);
        }

        return "/client/cart.jsp";
    }
    @GetMapping("showIndex.do")
    public String showIndex(Model model){
            Notice notice = productService.queryRecentNotice();
            List<Product> products = productService.queryWeekHotProdects();
            model.addAttribute("n",notice);
            model.addAttribute("products",products);
            return "/client/index.jsp";
    }

}
