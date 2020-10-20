package com.niu.sign.validate.repo.impl;

import com.niu.sign.validate.ValidateCode;
import com.niu.sign.validate.ValidateCodeType;
import com.niu.sign.validate.repo.ValidateCodeRepository;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.ServletWebRequest;

/**
 * 基于session的验证码存取器
 *
 * @author [nza]
 * @version 1.0 [2020/10/12 14:25]
 * @createTime [2020/10/12 14:25]
 */
@Component("sessionValidateCodeRepository")
public class SessionValidateCodeRepository implements ValidateCodeRepository {

    /**
     * 验证码放入session时的前缀
     */
    private static final String SESSION_KEY_PREFIX = "SESSION_KEY_FOR_CODE_";

    @Override
    public void save(ServletWebRequest request, ValidateCode code, ValidateCodeType validateCodeType) {
        request.setAttribute(getSessionKey(validateCodeType), code, 1);
    }

    @Override
    public ValidateCode get(ServletWebRequest request, ValidateCodeType validateCodeType) {
        return (ValidateCode) request.getAttribute(getSessionKey(validateCodeType), 1);
    }

    @Override
    public void remove(ServletWebRequest request, ValidateCodeType codeType) {
        request.removeAttribute(getSessionKey(codeType), 1);
    }

    /**
     * 获取Session Key
     *
     * @param validateCodeType 验证码类型
     * @return {@link java.lang.String} key
     * @author nza
     * @createTime 2020/10/12 14:30
     */
    private String getSessionKey(ValidateCodeType validateCodeType) {
        return SESSION_KEY_PREFIX + validateCodeType.toString().toUpperCase();
    }
}
