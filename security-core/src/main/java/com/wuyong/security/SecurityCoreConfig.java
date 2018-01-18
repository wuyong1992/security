package com.wuyong.security;

import com.wuyong.security.properties.SecurityProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * created by JianGuo
 * on 2018/1/18
 * description:
 */
@Configuration  // 配置类
@EnableConfigurationProperties(SecurityProperties.class)    // 使SecurityProperties读取配置文件生效
public class SecurityCoreConfig {
}
