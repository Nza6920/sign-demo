package com.niu.sign.validate;

/**
 * 验证码类型
 *
 * @author [nza]
 * @version 1.0 [2020/10/12 13:56]
 * @createTime [2020/10/12 13:56]
 */
public enum ValidateCodeType {
    /**
     * 短信验证码
     */
    SMS {
        @Override
        public String getParamNameOnValidate() {
            return "smsCode";
        }

        @Override
        public String getRepoType() {
            return "redis";
        }
    },

    /**
     * 图片验证码
     */
    IMAGE {
        @Override
        public String getParamNameOnValidate() {
            return "imageCode";
        }

        @Override
        public String getRepoType() {
            return "session";
        }
    };

    public abstract String getParamNameOnValidate();

    public abstract String getRepoType();

}
