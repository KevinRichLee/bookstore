package com.bookstore.common.advice;

import com.bookstore.common.emum.ExceptionEnum;
import com.bookstore.common.exception.BookStoreException;
import com.bookstore.common.exception.ExceptionResult;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

/**
 * company: www.abc.com
 * Author: KevinLee
 * Create Data: 2019/3/24
 */
//处理所有Controller异常
@ControllerAdvice

public class CommonExceptionHandler {
    @ExceptionHandler(BookStoreException.class)
    public ResponseEntity<ExceptionResult> handlerException(BookStoreException e){
        ExceptionEnum em = e.getExceptionEnum();
        return ResponseEntity.status(e.getExceptionEnum().getCode()).body(new ExceptionResult(e.getExceptionEnum()));
    }
}
