package com.wuyong.security.browser.support;

import lombok.Data;

/**
 * created by JianGuo
 * on 2018/1/18
 * description: 简单封装返回值
 */
@Data
public class SimpleResponse {

    private Object content;

    public SimpleResponse(Object content) {
        this.content = content;
    }
}
