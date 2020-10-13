package com.niu.sign.validate;

import com.niu.sign.exception.ValidateCodeException;
import com.niu.sign.validate.process.ValidateCodeProcessor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Map;

/**
 * 验证码处理器拥有者
 *
 * @author [nza]
 * @version 1.0 [2020/10/13 13:31]
 * @createTime [2020/10/13 13:31]
 */
@Component
public class ValidateCodeProcessorHolder {

    @Autowired
    private Map<String, ValidateCodeProcessor> validateCodeProcessors;

    public ValidateCodeProcessor findValidateCodeProcessor(ValidateCodeType type) {
        return findValidateCodeProcessor(type.toString().toLowerCase());
    }

    public ValidateCodeProcessor findValidateCodeProcessor(String type) {
        String name = type.toLowerCase() + ValidateCodeProcessor.class.getSimpleName();
        ValidateCodeProcessor processor = validateCodeProcessors.get(name);
        if (processor == null) {
            throw new ValidateCodeException("验证码处理器" + name + "不存在");
        }
        return processor;
    }
}
