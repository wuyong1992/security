package com.wuyong.security.validate.code;

import com.wuyong.security.properties.SecurityProperties;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.ServletRequestUtils;
import org.springframework.web.context.request.ServletWebRequest;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.Random;

/**
 * created by JianGuo
 * on 2018/1/19
 * description: 图形验证码生成器
 * 不是用component，而是使用配置类的方式去配置，具体见配置类
 */
//@Component
@Slf4j
@Data
public class ImageCodeGenerator implements ValidateCodeGenerator {


    private SecurityProperties securityProperties;


    @Override
    public ImageCode generator(ServletWebRequest servletWebRequest) {
        // 图片宽、高
        // 从request里面取相应的数值，如果没有，则配置默认值为配置文件中的值，如果配置文件没有配置，则使用类初始化的时候的默认值
        int width = ServletRequestUtils.getIntParameter(servletWebRequest.getRequest(), "imageWidth", securityProperties.getCode().getImageCode().getImageWidth());
        int height = ServletRequestUtils.getIntParameter(servletWebRequest.getRequest(), "imageHeight", securityProperties.getCode().getImageCode().getImageHeight());

        log.info("width:{};height:{}", width, height);

        BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
        // 图形对象
        Graphics graphics = image.getGraphics();
        Random random = new Random();

        // 背景条纹
        graphics.setColor(getRandColor(200, 250));
        graphics.fillRect(0, 0, width, height);
        graphics.setColor(getRandColor(160, 200));

        for (int i = 0; i < 155; i++) {
            int x = random.nextInt(width);
            int y = random.nextInt(height);
            int xl = random.nextInt(12);
            int yl = random.nextInt(12);
            graphics.drawLine(x, y, x + xl, y + yl);
        }
        String sRand = "";

        // 随机数
        for (int i = 0; i < securityProperties.getCode().getImageCode().getCodeLength(); i++) {
            String rand = String.valueOf(random.nextInt(10));
            sRand += rand;
            graphics.setColor(new Color(20 + random.nextInt(110), 20 + random.nextInt(110), 20 + random.nextInt(110)));
            graphics.drawString(rand, 13 * i + 6, 16);
        }

        graphics.dispose();

        return new ImageCode(image, sRand, 60);
    }

    /**
     * 生成随机背景条纹
     *
     * @param fc
     * @param bc
     * @return
     */
    private Color getRandColor(int fc, int bc) {
        Random random = new Random();
        if (fc > 255) {
            fc = 255;
        }
        if (bc > 255) {
            bc = 255;
        }
        int r = fc + random.nextInt(bc - fc);
        int g = fc + random.nextInt(bc - fc);
        int b = fc + random.nextInt(bc - fc);
        return new Color(r, g, b);
    }
}
