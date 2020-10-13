package com.niu.sign.validate.repo;

import com.niu.sign.validate.ValidateCode;
import com.niu.sign.validate.ValidateCodeType;
import org.springframework.web.context.request.ServletWebRequest;

/**
 * 验证码存储
 *
 * @version 1.0 [2020/10/12 13:55]
 * @author [nza]
 * @createTime [2020/10/12 13:55]
 */
public interface ValidateCodeRepository {
    /**
     * 保存验证码
     *
     * @param request
     * @param code
     * @param validateCodeType
     */
    void save(ServletWebRequest request, ValidateCode code, ValidateCodeType validateCodeType);

    /**
     * 获取验证码
     *
     * @param request
     * @param validateCodeType
     * @return
     */
    ValidateCode get(ServletWebRequest request, ValidateCodeType validateCodeType);

    /**
     * 移除验证码
     *
     * @param request
     * @param codeType
     */
    void remove(ServletWebRequest request, ValidateCodeType codeType);
}
