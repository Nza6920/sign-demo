package com.niu.sign.sms;

/**
 * 短信发送接口
 *
 * @author [nza]
 * @version 1.0 [2020/10/15 14:31]
 * @createTime [2020/10/15 14:31]
 */
public interface SmsCodeSender {

    /**
     * 发送短信验证码
     *
     * @param mobile 手机号
     * @param code   验证码
     * @author nza
     * @createTime 2020/10/15 14:31
     */
    void send(String mobile, String code);
}
