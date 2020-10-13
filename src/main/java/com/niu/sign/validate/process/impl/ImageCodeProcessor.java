package com.niu.sign.validate.process.impl;

import com.niu.sign.validate.ImageCode;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.ServletWebRequest;

import javax.imageio.ImageIO;

/**
 * 图像验证码流程器
 *
 * @author [nza]
 * @version 1.0 [2020/10/12 14:57]
 * @createTime [2020/10/12 14:57]
 */
@Component("imageValidateCodeProcessor")
public class ImageCodeProcessor extends AbstractValidateCodeProcessor<ImageCode> {
    @Override
    protected void send(ServletWebRequest req, ImageCode imageCode) throws Exception {
        ImageIO.write(imageCode.getImage(), "JPEG", req.getResponse().getOutputStream());
    }
}
