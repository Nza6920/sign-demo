package com.niu.sign.sms.impl;

import com.niu.sign.sms.SmsCodeSender;
import lombok.extern.slf4j.Slf4j;

/**
 * 默认的短信验证码发送器
 *
 * @version 1.0 [2020/10/15 14:32]
 * @author [nza]
 * @createTime [2020/10/15 14:32]
 */
@Slf4j
public class DefaultSmsCodeSender implements SmsCodeSender {
    @Override
    public void send(String mobile, String code) {
        log.info("发送短信验证码, mobile: {}, code: {}", mobile, code);
    }
}
