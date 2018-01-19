package com.wuyong.security.validate.code;

import lombok.Data;

import java.awt.image.BufferedImage;
import java.time.LocalDateTime;

/**
 * created by JianGuo
 * on 2018/1/19
 * description: 图形验证码 公用模块
 */

@Data
public class ImageCode {

    private BufferedImage image;    // 验证码图片

    private String code;    // 随机数

    private LocalDateTime expireTime;   // 过期时间
    private boolean expried;

    /**
     * 构造方法重载
     * @param image 图片
     * @param code  随机数
     * @param expireTime    过期时间点
     */
    public ImageCode(BufferedImage image, String code, LocalDateTime expireTime) {
        this.image = image;
        this.code = code;
        this.expireTime = expireTime;
    }

    /**
     * 构造方法重载
     * @param image 图片
     * @param code  随机数
     * @param expireIn  有效期，单位秒
     */
    public ImageCode(BufferedImage image, String code, int expireIn) {
        this.image = image;
        this.code = code;
        this.expireTime = LocalDateTime.now().plusSeconds(expireIn);

    }

    public boolean isExpried() {
        // 是否过去
        return LocalDateTime.now().isAfter(expireTime);
    }
}
