package com.niu.sign.validate.generate;

import com.niu.sign.validate.ValidateCode;
import org.springframework.web.context.request.ServletWebRequest;

/**
 * 验证码生成接口
 *
 * @version 1.0 [2020/10/12 13:54]
 * @author [nza]
 * @createTime [2020/10/12 13:54]
 */
public interface ValidateCodeGenerator {

    /**
     * 验证码生成
     * @param req 请求
     * @return {@link ValidateCode}
     */
    ValidateCode generate(ServletWebRequest req);
}
