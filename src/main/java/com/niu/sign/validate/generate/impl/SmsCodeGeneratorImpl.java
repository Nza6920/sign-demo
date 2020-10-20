package com.niu.sign.validate.generate.impl;

import com.niu.sign.validate.ValidateCode;
import com.niu.sign.validate.generate.ValidateCodeGenerator;
import org.apache.commons.lang.RandomStringUtils;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.ServletWebRequest;

/**
 * 短信验证码生成器
 *
 * @author [nza]
 * @version 1.0 [2020/10/15 15:48]
 * @createTime [2020/10/15 15:48]
 */
@Component("smsCodeGenerator")
public class SmsCodeGeneratorImpl implements ValidateCodeGenerator {
    @Override
    public ValidateCode generate(ServletWebRequest req) {
        String code = RandomStringUtils.randomNumeric(4);
        return new ValidateCode(code, 60);
    }
}
