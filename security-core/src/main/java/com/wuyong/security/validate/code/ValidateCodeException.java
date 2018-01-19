package com.wuyong.security.validate.code;


import org.springframework.security.core.AuthenticationException;

/**
 * created by JianGuo
 * on 2018/1/19
 * description: 自定义的异常
 */
public class ValidateCodeException extends AuthenticationException {

    public ValidateCodeException(String msg) {
        super(msg);
    }
}
