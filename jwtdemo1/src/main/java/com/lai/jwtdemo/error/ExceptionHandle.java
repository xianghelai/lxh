package com.lai.jwtdemo.error;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @Author: bbdw
 * @Date: 2019/7/18 14:54
 * @Version 1.0
 */
@ControllerAdvice
public class ExceptionHandle {
    @ExceptionHandler(value = Exception.class)
    @ResponseBody
     public ErrorMsg handlerSellerException(Exception e){
        e.printStackTrace();
         return new ErrorMsg("500",e.getMessage());
      }
}
