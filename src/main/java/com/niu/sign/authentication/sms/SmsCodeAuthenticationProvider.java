package com.niu.sign.authentication.sms;

import com.niu.sign.service.SysUserService;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.InternalAuthenticationServiceException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;

/**
 * SmsCodeAuthenticationProvider
 *
 * @author [nza]
 * @version 1.0 [2020/10/15 15:08]
 * @createTime [2020/10/15 15:08]
 */
public class SmsCodeAuthenticationProvider implements AuthenticationProvider {

    /**
     * 用户业务类
     */
    private final SysUserService sysUserService;

    public SmsCodeAuthenticationProvider(UserDetailsService userDetailsService) {
        this.sysUserService = (SysUserService) userDetailsService;
    }

    /**
     * 短信登录认证逻辑
     *
     * @param authentication token
     * @return {@link org.springframework.security.core.Authentication}
     * @author nza
     * @createTime 2020/10/15 15:10
     */
    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        SmsCodeAuthenticationToken token = (SmsCodeAuthenticationToken) authentication;
        UserDetails userDetails = sysUserService.loadUserByMobile((String) token.getPrincipal());

        if (userDetails == null) {
            throw new InternalAuthenticationServiceException("无法获取用户信息");
        }

        // 调用已认证构造函数
        SmsCodeAuthenticationToken res = new SmsCodeAuthenticationToken(userDetails, userDetails.getAuthorities());

        res.setDetails(token.getDetails());

        return res;
    }

    /**
     * 判断当前provider是否支持当前登录方式
     *
     * @param aClass 登陆方式
     * @return boolean
     * @author nza
     * @createTime 2020/10/15 15:09
     */
    @Override
    public boolean supports(Class<?> aClass) {
        return SmsCodeAuthenticationToken.class.isAssignableFrom(aClass);
    }
}
