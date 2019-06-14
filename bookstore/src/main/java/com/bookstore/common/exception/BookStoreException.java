package com.bookstore.common.exception;

import com.bookstore.common.emum.ExceptionEnum;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * company: www.abc.com
 * Author: KevinLee
 * Create Data: 2019/4/21
 */
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class BookStoreException extends RuntimeException  {
    @Autowired
    private ExceptionEnum exceptionEnum;

}
