package com.bookstore.products.bean;

import com.bookstore.orders.bean.OrderItem;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * company: www.abc.com
 * Author: KevinLee
 * Create Data: 2019/4/27
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Product {
    private String id;
    private String name;
    private Double price;
    private String category;
    private Integer pnum;
    private String imgurl;
    private String description;
    private Integer buynum;
//    public Integer getBuynum() {
//        return buynum;
//    }
//
//    public void setBuynum(Integer buynum) {
//        this.buynum = buynum;
//    }
//    public String getId() {
//        return id;
//    }
//
//    public void setId(String id) {
//        this.id = id;
//    }
//
//    public String getName() {
//        return name;
//    }
//
//    public void setName(String name) {
//        this.name = name;
//    }
//
//    public Double getPrice() {
//        return price;
//    }
//
//    public void setPrice(Double price) {
//        this.price = price;
//    }
//
//    public String getCategory() {
//        return category;
//    }
//
//    public void setCategory(String category) {
//        this.category = category;
//    }
//
//    public Integer getPnum() {
//        return pnum;
//    }
//
//    public void setPnum(Integer pnum) {
//        this.pnum = pnum;
//    }
//
//    public String getImgurl() {
//        return imgurl;
//    }
//
//    public void setImgurl(String imgurl) {
//        this.imgurl = imgurl;
//    }
//
//    public String getDescription() {
//        return description;
//    }
//
//    public void setDescription(String description) {
//        this.description = description;
//    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((id == null) ? 0 : id.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        //两个对象是同一个对象，返回true
        if (this == obj)
            return true;
        //如果equals后的对象为null，返回false
        if (obj == null)
            return false;
        //如果两个对象的类型不一致，返回false
        if (getClass() != obj.getClass())
            return false;
        //把obj强转成Product
        Product other = (Product) obj;
        //如果equals前的对象的id为null，但obj的id不为null，返回false
        if (id == null) {
            if (other.id != null)
                return false;
        } else if (!id.equals(other.id))//如果两个对象的id不相同，返回false
            return false;
        //如果两个对象的id相同，返回true
        return true;
    }
}
