package com.niu.sign.service;

import com.niu.sign.pojo.SysUser;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

/**
 * 用户业务类
 *
 * @author [nza]
 * @version 1.0 [2020/10/15 15:19]
 * @createTime [2020/10/15 15:19]
 */
public interface SysUserService extends UserDetailsService {

    /**
     * 根据手机号查询用户
     *
     * @param mobile
     * @author nza
     * @createTime 2020/10/15 15:21
     * @return    {@link com.niu.sign.pojo.SysUser}
     */
    SysUser loadUserByMobile(String mobile) throws UsernameNotFoundException;
}
