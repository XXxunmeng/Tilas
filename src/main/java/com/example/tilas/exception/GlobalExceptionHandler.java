package com.example.tilas.exception;

import com.example.tilas.pojo.Result;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {
    // 全局异常处理器
    @ExceptionHandler(Exception.class) // 指定捕获所有异常
    public Result ex(Exception e) {
        e.printStackTrace();
        return Result.error("操作失败，请联系管理员");
    }
}
