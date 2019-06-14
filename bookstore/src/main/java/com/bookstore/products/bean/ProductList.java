package com.bookstore.products.bean;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductList {
    private String id;
    private String name;
    private String  salenum;
}
