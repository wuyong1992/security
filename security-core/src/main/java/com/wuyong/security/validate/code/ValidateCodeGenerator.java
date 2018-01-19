package com.wuyong.security.validate.code;

import org.springframework.web.context.request.ServletWebRequest;

/**
 * created by JianGuo
 * on 2018/1/19
 * description: 验证码的生成器
 */
public interface ValidateCodeGenerator {


    ImageCode generator(ServletWebRequest servletWebRequest);

}
