package com.bookstore.common.exception;

import com.bookstore.common.emum.ExceptionEnum;
import lombok.Data;

/**
 * company: www.abc.com
 * Author: KevinLee
 * Create Data: 2019/3/24
 */
//把返回的结果封装成一个对象
//根据实际情况想返回的什么数据都可以在这里添加
@Data
public class ExceptionResult {
    private Integer status;
    private String message;
    private Long timestamp;//时间戳

    public ExceptionResult(ExceptionEnum em) {
        this.status = em.getCode();
        this.message = em.getMsg();
        this.timestamp = System.currentTimeMillis();
    }
}
