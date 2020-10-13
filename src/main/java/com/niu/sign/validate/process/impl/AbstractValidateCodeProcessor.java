package com.niu.sign.validate.process.impl;

import com.niu.sign.validate.ValidateCode;
import com.niu.sign.validate.ValidateCodeType;
import com.niu.sign.validate.generate.ValidateCodeGenerator;
import com.niu.sign.validate.process.ValidateCodeProcessor;
import com.niu.sign.validate.repo.ValidateCodeRepository;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.ServletRequestBindingException;
import org.springframework.web.bind.ServletRequestUtils;
import org.springframework.web.context.request.ServletWebRequest;

import java.util.Map;

/**
 * 生成验证码流程
 *
 * @author [nza]
 * @version 1.0 [2020/10/12 13:50]
 * @createTime [2020/10/12 13:50]
 */
public abstract class AbstractValidateCodeProcessor<C extends ValidateCode> implements ValidateCodeProcessor {
    /**
     * 收集系统中所有的 {@link ValidateCodeGenerator} 接口的实现 - 依赖搜索
     */
    @Autowired
    private Map<String, ValidateCodeGenerator> validateCodeGenerators;

    @Autowired
    private ValidateCodeRepository validateCodeRepository;

    @Override
    public void create(ServletWebRequest request) throws Exception {
        C validateCode = generate(request);
        save(request, validateCode);
        send(request, validateCode);
    }

    /**
     * 生成验证码
     *
     * @param request 请求
     * @return {@link ValidateCode} 验证码
     * @author nza
     * @createTime 2020/10/12 14:00
     */
    @SuppressWarnings("unchecked")
    private C generate(ServletWebRequest request) {
        String type = getProcessorType(request);
        ValidateCodeGenerator validateCodeGenerator = validateCodeGenerators.get(type + "CodeGenerator");
        return (C) validateCodeGenerator.generate(request);
    }

    /**
     * 保存
     *
     * @param request      请求
     * @param validateCode 验证码
     * @author nza
     * @createTime 2020/10/12 14:05
     */
    private void save(ServletWebRequest request, C validateCode) {
        ValidateCode code = new ValidateCode(validateCode.getCode(), validateCode.getExpireTime());
        validateCodeRepository.save(request, code, getValidateCodeType(request));
    }

    /**
     * 发送校验码，由子类实现
     *
     * @param request      请求
     * @param validateCode 验证码
     * @throws {@link Exception} 验证码发送失败抛出
     * @author nza
     * @createTime 2020/10/12 14:06
     */
    protected abstract void send(ServletWebRequest request, C validateCode) throws Exception;

    /**
     * 获取验证码流程类型
     *
     * @param request 请求
     * @return {@link java.lang.String} 流程值
     * @author nza
     * @createTime 2020/10/12 14:07
     */
    private String getProcessorType(ServletWebRequest request) {
        return StringUtils.substringAfter(request.getRequest().getRequestURI(), "/code/");
    }

    /**
     * 获取验证码类型
     *
     * @param request 请求
     * @return {@link com.niu.sign.validate.ValidateCodeType} 验证码类型
     * @author nza
     * @createTime 2020/10/12 14:08
     */
    private ValidateCodeType getValidateCodeType(ServletWebRequest request) {
        String type = StringUtils.substringBefore(getClass().getSimpleName(), "CodeProcessor");
        return ValidateCodeType.valueOf(type.toUpperCase());
    }

    @Override
    public void validate(ServletWebRequest request) {

        ValidateCodeType codeType = getValidateCodeType(request);

        ValidateCode codeInStore = validateCodeRepository.get(request, codeType);

        String codeInRequest;
        try {
            codeInRequest = ServletRequestUtils.getStringParameter(request.getRequest(),
                    codeType.getParamNameOnValidate());
        } catch (ServletRequestBindingException e) {
            throw new RuntimeException("获取验证码的值失败");
        }

        if (StringUtils.isBlank(codeInRequest)) {
            throw new RuntimeException(codeType + "验证码的值不能为空");
        }

        if (codeInStore == null) {
            throw new RuntimeException(codeType + "验证码不存在");
        }

        if (codeInStore.isExpired()) {
            validateCodeRepository.remove(request, codeType);
            throw new RuntimeException(codeType + "验证码已过期");
        }

        if (!StringUtils.equals(codeInStore.getCode(), codeInRequest)) {
            throw new RuntimeException(codeType + "验证码不匹配");
        }

        validateCodeRepository.remove(request, codeType);
    }
}
