package com.niu.sign.authentication.sms;

import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;

import java.util.Collection;

/**
 * 短信短信登录认证Token
 *
 * @version 1.0 [2020/10/15 14:45]
 * @author [nza]
 * @createTime [2020/10/15 14:45]
 */
public class SmsCodeAuthenticationToken extends AbstractAuthenticationToken {

    private static final long serialVersionUID = -6833557082904186230L;

    private final Object principal;

    /**
     * 未认证调用
     */
    public SmsCodeAuthenticationToken(Object mobile) {
        super(null);
        this.principal = mobile;
        this.setAuthenticated(false);
    }

    /**
     * 已认证调用
     */
    public SmsCodeAuthenticationToken(Object principal, Collection<? extends GrantedAuthority> authorities) {
        super(authorities);
        this.principal = principal;
        super.setAuthenticated(true);
    }

    @Override
    public Object getCredentials() {
        return null;
    }

    @Override
    public Object getPrincipal() {
        return principal;
    }

    @Override
    public void setAuthenticated(boolean authenticated) {
        super.setAuthenticated(authenticated);
    }
}
