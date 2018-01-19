package com.wuyong.security.validate.code;

import com.wuyong.security.properties.SecurityProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * created by JianGuo
 * on 2018/1/19
 * description: 验证码Bean的配置类
 */


@Configuration
public class ValidateBeanConfig {


    @Autowired
    private SecurityProperties securityProperties;

    @Bean
    /**
     * 以增量的方式适应变化
     * 在spring容器在其他地方找不到imageCodeGenerator实例的时候才会去注册这个bean，
     * 例如业务更改，需要在新的图形验证码生成器（逻辑），则不需要改动原有的代码，新写一个生成器，并加上注解@component(name="imageCodeGenerator")即可
     * spring找到这个bean之后就不会再来这里注册了
     */
    @ConditionalOnMissingBean(name = "imageCodeGenerator")
    public ImageCodeGenerator imageCodeGenerator(){
        ImageCodeGenerator imageCodeGenerator = new ImageCodeGenerator();
        imageCodeGenerator.setSecurityProperties(securityProperties);
        return imageCodeGenerator;
    }


}
