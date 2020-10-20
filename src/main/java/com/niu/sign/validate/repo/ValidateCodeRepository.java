package com.niu.sign.validate.repo;

import com.niu.sign.validate.ValidateCode;
import com.niu.sign.validate.ValidateCodeType;
import org.springframework.web.context.request.ServletWebRequest;

/**
 * 验证码存储
 *
 * @author [nza]
 * @version 1.0 [2020/10/12 13:55]
 * @createTime [2020/10/12 13:55]
 */
public interface ValidateCodeRepository {

    /**
     * 保存验证码
     *
     * @param request          请求
     * @param code             验证码
     * @param validateCodeType 验证码类型
     */
    void save(ServletWebRequest request, ValidateCode code, ValidateCodeType validateCodeType);

    /**
     * 获取验证码
     *
     * @param request          请求
     * @param validateCodeType 验证码类型
     * @return {@link ValidateCode}
     */
    ValidateCode get(ServletWebRequest request, ValidateCodeType validateCodeType);

    /**
     * 移除验证码
     *
     * @param request  请求
     * @param codeType 验证码类型
     */
    void remove(ServletWebRequest request, ValidateCodeType codeType);
}
