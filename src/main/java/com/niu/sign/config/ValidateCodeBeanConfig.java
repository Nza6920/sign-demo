package com.niu.sign.config;

import com.niu.sign.sms.SmsCodeSender;
import com.niu.sign.sms.impl.DefaultSmsCodeSender;
import com.niu.sign.validate.generate.ValidateCodeGenerator;
import com.niu.sign.validate.generate.impl.ImageCodeGeneratorImpl;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 验证码Bean配置类
 *
 * @author [nza]
 * @version 1.0 [2020/10/12 14:20]
 * @createTime [2020/10/12 14:20]
 */
@Configuration
public class ValidateCodeBeanConfig {

    /**
     * 配置图片验证码发送器
     *
     * @return {@link ValidateCodeGenerator}
     */
    @Bean
    @ConditionalOnMissingBean(name = "imageCodeGenerator")
    public ValidateCodeGenerator imageCodeGenerator() {
        return new ImageCodeGeneratorImpl();
    }

    /**
     * 配置短信验证码发送器
     *
     * @return {@link SmsCodeSender}
     */
    @Bean
    @ConditionalOnMissingBean(SmsCodeSender.class)
    public SmsCodeSender smsCodeSender() {
        return new DefaultSmsCodeSender();
    }
}
