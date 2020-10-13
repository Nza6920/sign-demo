package com.niu.sign.controller;

import com.niu.sign.pojo.SysUser;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

/**
 * 用户控制器
 *
 * @author [nza]
 * @version 1.0 [2020/10/13 13:53]
 * @createTime [2020/10/13 13:53]
 */
@RestController
@RequestMapping("/user")
public class UserController {

    @GetMapping("/me")
    public SysUser me(@AuthenticationPrincipal Principal principal) {
        return (SysUser) ((UsernamePasswordAuthenticationToken) principal).getPrincipal();
    }
}
