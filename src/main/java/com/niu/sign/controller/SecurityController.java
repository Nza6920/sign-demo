package com.niu.sign.controller;

import com.niu.sign.support.SimpleResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.security.web.DefaultRedirectStrategy;
import org.springframework.security.web.RedirectStrategy;
import org.springframework.security.web.savedrequest.HttpSessionRequestCache;
import org.springframework.security.web.savedrequest.RequestCache;
import org.springframework.security.web.savedrequest.SavedRequest;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 处理认证请求
 *
 * @author [nza]
 * @version 1.0 [2020/10/10 16:02]
 * @createTime [2020/10/10 16:02]
 */
@RestController
@Slf4j
public class SecurityController {

    /**
     * session 工具类
     */
    private RequestCache requestCache = new HttpSessionRequestCache();

    /**
     * 跳转器
     */
    private RedirectStrategy redirectStrategy = new DefaultRedirectStrategy();

    /**
     * 当需要身份认证时跳转到这里
     *
     * @param request  请求
     * @param response 响应
     * @return {@link SimpleResponse}
     */
    @RequestMapping("/authentication/require")
    @ResponseStatus(code = HttpStatus.UNAUTHORIZED)
    public SimpleResponse requireAuthentication(HttpServletRequest request, HttpServletResponse response) throws IOException {

        SavedRequest savedRequest = requestCache.getRequest(request, response);
        if (savedRequest != null) {
            String target = savedRequest.getRedirectUrl();
            log.info("引发跳转的请求是: " + target);
            if (StringUtils.endsWithIgnoreCase(target, ".html")) {
                redirectStrategy.sendRedirect(request, response, "/my-login.html");
            }
        }
        return new SimpleResponse("访问的服务需要认证, 请引导用户到登录页");
    }
}
