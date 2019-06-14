package com.bookstore.common.emum;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * company: www.abc.com
 * Author: KevinLee
 * Create Data: 2019/4/21
 */
@NoArgsConstructor
@Getter
@AllArgsConstructor
public enum  ExceptionEnum {
    USER_ADD_ERROR(500,"用户新增失败"),
    ACTIVE_CODE_FILE(500,"激活失败"),
    SELECT_PRODUCTS_IS_NOT_FOUND(404,"查询商品不存在"),
    ;
    private Integer code;
    private String msg;
}
