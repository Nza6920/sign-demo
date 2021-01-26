package com.niu.sign.validate;

import java.awt.image.BufferedImage;
import java.time.LocalDateTime;

/**
 * 图像验证码
 *
 * @author [nza]
 * @version 1.0 [2020/10/12 14:16]
 * @createTime [2020/10/12 14:16]
 */
public class ImageCode extends ValidateCode {

    private static final long serialVersionUID = 807900290364374277L;

    public ImageCode(BufferedImage image, String code, int expireTime) {
        super(code, expireTime);
        this.image = image;
    }

    public ImageCode(BufferedImage image, String code, LocalDateTime expireTime) {
        super(code, expireTime);
        this.image = image;

    }

    /**
     * 图片
     */
    private BufferedImage image;

    public BufferedImage getImage() {
        return image;
    }

    public void setImage(BufferedImage image) {
        this.image = image;
    }
}
