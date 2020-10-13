package com.niu.sign.validate;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.time.LocalDateTime;

/**
 * 验证码
 *
 * @version 1.0 [2020/10/12 13:51]
 * @author [nza]
 * @createTime [2020/10/12 13:51]
 */
@Data
@Accessors(chain = true)
@NoArgsConstructor
public class ValidateCode {

    private static final long serialVersionUID = 1845420002343674268L;

    public ValidateCode(String code, int expireTime) {
        this.code = code;
        this.expireTime = LocalDateTime.now().plusSeconds(expireTime);
    }

    public ValidateCode(String code, LocalDateTime expireTime) {
        this.code = code;
        this.expireTime = expireTime;
    }

    /**
     * 验证码值
     */
    private String code;

    /**
     * 过期时间
     */
    private LocalDateTime expireTime;

    public boolean isExpired() {
        return LocalDateTime.now().isAfter(expireTime);
    }
}
