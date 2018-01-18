package com.wuyong.security.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * created by JianGuo
 * on 2018/1/18
 * description: 核心配置文件
 */
@ConfigurationProperties(prefix = "wuyong.security")
@Data
public class SecurityProperties {

    BrowserProperties browser = new BrowserProperties();
}
