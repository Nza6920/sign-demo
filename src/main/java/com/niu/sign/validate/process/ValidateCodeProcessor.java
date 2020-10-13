package com.niu.sign.validate.process;

import org.springframework.web.context.request.ServletWebRequest;

/**
 * 验证码流程接口
 *
 * @author [nza]
 * @version 1.0 [2020/10/12 13:39]
 * @createTime [2020/10/12 13:39]
 */
public interface ValidateCodeProcessor {

    /**
     * 验证码放入session时的前缀
     */
    String SESSION_KEY_PREFIX = "SESSION_KEY_FOR_CODE_";

    /**
     * 创建验证码
     *
     * @param request 请求
     * @throws {@link Exception} 生成验证码失败抛出
     * @author nza
     * @createTime 2020/10/12 13:42
     */
    void create(ServletWebRequest request) throws Exception;

    /**
     * 校验验证码
     *
     * @param servletWebRequest adapter for req, resp
     * @author nza
     * @createTime 2020/10/12 13:43
     */
    void validate(ServletWebRequest servletWebRequest);
}
