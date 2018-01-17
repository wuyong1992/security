package com.wuyong.security.exception;

import lombok.Getter;
import lombok.Setter;

/**
 * created by JianGuo
 * on 2018/1/17
 * description: 用户不存在异常
 */

@Getter@Setter
public class UserNotExistExcepton extends RuntimeException {

    private static final long serialVersionUID = -1569107689698328458L;


    private String id;

    public UserNotExistExcepton(String id) {
        super("User not exist");
        this.id = id;
    }
}
