package com.niu.sign.validate.repo.impl;

import com.niu.sign.exception.ValidateCodeException;
import com.niu.sign.validate.ValidateCode;
import com.niu.sign.validate.ValidateCodeType;
import com.niu.sign.validate.repo.ValidateCodeRepository;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.ServletWebRequest;

import java.util.concurrent.TimeUnit;

/**
 * 基于redis的验证码存取器
 *
 * @author [nza]
 * @version 1.0 [2020/10/15 14:21]
 * @createTime [2020/10/15 14:21]
 */
@Component("redisValidateCodeRepository")
public class RedisValidateCodeRepository implements ValidateCodeRepository {

    @Autowired
    private RedisTemplate<Object, Object> redisTemplate;

    @Override
    public void save(ServletWebRequest request, ValidateCode code, ValidateCodeType type) {
        redisTemplate.opsForValue().set(buildKey(request, type), code, 30, TimeUnit.MINUTES);
    }

    @Override
    public ValidateCode get(ServletWebRequest request, ValidateCodeType type) {
        Object value = redisTemplate.opsForValue().get(buildKey(request, type));
        if (value == null) {
            return null;
        }
        return (ValidateCode) value;
    }

    @Override
    public void remove(ServletWebRequest request, ValidateCodeType type) {
        redisTemplate.delete(buildKey(request, type));
    }

    /**
     * 构建Redis Key, 获取请求中的 deviceId 参数作为 redis 的 key
     *
     * @param request 请求
     * @param type    验证码类型
     * @return {@link java.lang.String}
     * @author nza
     * @createTime 2020/10/15 14:22
     */
    private String buildKey(ServletWebRequest request, ValidateCodeType type) {
        String deviceId = request.getHeader("deviceId");
        if (StringUtils.isBlank(deviceId)) {
            throw new ValidateCodeException("请在请求头中携带deviceId参数");
        }
        return "code:" + type.toString().toLowerCase() + ":" + deviceId;
    }
}
