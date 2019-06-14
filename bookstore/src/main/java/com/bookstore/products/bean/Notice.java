package com.bookstore.products.bean;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Notice {
    private Integer n_id;
    private String title;
    private String details;
    private Timestamp n_time;
}
