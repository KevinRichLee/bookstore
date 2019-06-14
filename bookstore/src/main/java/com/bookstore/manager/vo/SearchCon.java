package com.bookstore.manager.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SearchCon {
    //id,category,name,minprice,maxprice
    private Integer id;
    private String category;
    private String name;
    private Integer minprice;
    private Integer maxprice;

}
