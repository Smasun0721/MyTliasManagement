package org.example.tliaswebmanagement.exception;

import lombok.extern.slf4j.Slf4j;
import org.example.tliaswebmanagement.exception.custom.ClassNotNull;
import org.example.tliaswebmanagement.exception.custom.UsernameOrPasswordError;
import org.example.tliaswebmanagement.pojo.Result;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler
    public Result handleException(Exception e){
    log.info("捕获异常:",e);
    return Result.error("出错啦，请联系管理员~");
    }

    @ExceptionHandler
    public Result handleDuplicateKeyException(DuplicateKeyException e){
        log.info("捕获异常:",e);
        String message = e.getMessage();
        int i = message.indexOf("Duplicate entry");
        String errMsg = message.substring(i);
        String[] arr = errMsg.split(" ");
        return Result.error(arr[2]+"已存在");
    }

    @ExceptionHandler
    public Result handleClassNotNullException(ClassNotNull e){
        log.info("捕获异常:",e);
        String message = e.getMessage();
        return Result.error(message);
    }

    @ExceptionHandler
    public Result handleUsernameOrPasswordError(UsernameOrPasswordError e){
        log.info("登陆失败",e);
        String message =e.getMessage();
        return Result.error(message);
    }
}
