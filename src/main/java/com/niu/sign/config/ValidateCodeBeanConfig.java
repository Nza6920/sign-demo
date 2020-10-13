package com.niu.sign.config;

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
    @Bean
    @ConditionalOnMissingBean(name = "imageCodeGenerator")
    public ValidateCodeGenerator imageCodeGenerator() {
        return new ImageCodeGeneratorImpl();
    }
}
