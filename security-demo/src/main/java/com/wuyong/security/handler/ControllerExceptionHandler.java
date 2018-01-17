package com.wuyong.security.handler;

import com.google.common.collect.Maps;
import com.wuyong.security.exception.UserNotExistExcepton;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.Map;

/**
 * created by JianGuo
 * on 2018/1/17
 * description: controller 里面抛出的异常都到这里面处理
 */
@ControllerAdvice
public class ControllerExceptionHandler {

    // @ExceptionHandler 表示要处理的异常
    @ExceptionHandler(UserNotExistExcepton.class)
    @ResponseBody
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public Map<String, Object> userNotExistExceptionHandler(UserNotExistExcepton excepton) {
        Map<String, Object> map = Maps.newHashMap();
        map.put("id", excepton.getId());
        map.put("message", excepton.getMessage());
        return map;
    }

}
