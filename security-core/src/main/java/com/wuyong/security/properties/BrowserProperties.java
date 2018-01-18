package com.wuyong.security.properties;

import lombok.Data;

/**
 * created by JianGuo
 * on 2018/1/18
 * description:
 */

@Data
public class BrowserProperties {
    // 用户如果没有配置登录也则使用默认登录页
    private String loginPage = "/login.html";
}
