package com.wuyong.security.properties;

import lombok.Data;

/**
 * created by JianGuo
 * on 2018/1/19
 * description: 图形验证码的配置类
 */
@Data
public class ImageCodeProperties {

    // 图形验证码背景图宽度
    private int imageWidth = 67;
    // 图形验证码背景图高度
    private int imageHeight = 23;
    // 图形验证码随机数个数
    private int codeLength = 4;
    // 过期时间 单位秒
    private int expireIn = 60;

    // 组装过滤的路劲
    private String url;


}
