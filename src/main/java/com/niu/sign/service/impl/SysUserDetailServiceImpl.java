package com.niu.sign.service.impl;

import com.niu.sign.pojo.SysUser;
import com.niu.sign.service.SysUserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

/**
 * 用户详情服务
 *
 * @author [nza]
 * @version 1.0 [2020/10/10 16:25]
 * @createTime [2020/10/10 16:25]
 */
@Service
@Slf4j
public class SysUserDetailServiceImpl implements SysUserService {

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        log.info("username： " + username);

        // todo: 自己登录实现逻辑

        SysUser user = new SysUser();
        user.setName("admin");
        user.setPassword(passwordEncoder.encode("admin"));
        return user;
    }

    @Override
    public SysUser loadUserByMobile(String mobile) throws UsernameNotFoundException {
        log.info("mobile： " + mobile);
        SysUser user = new SysUser();
        user.setName("admin");
        user.setPassword(passwordEncoder.encode("admin"));
        return user;
    }
}
