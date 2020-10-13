package com.niu.sign.service;

import com.niu.sign.pojo.SysUser;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
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
public class SysUserDetailServiceImpl implements UserDetailsService {
    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        log.info("username： " + username);
        SysUser user = new SysUser();
        user.setName("nza");
        user.setPassword(passwordEncoder.encode("123456"));
        return user;
    }
}
