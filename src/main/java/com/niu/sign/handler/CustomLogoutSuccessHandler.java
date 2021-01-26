package com.niu.sign.handler;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.niu.sign.support.SimpleResponse;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 退出登录成功处理器
 *
 * @author [nza]
 * @version 1.0 [2020/10/12 09:52]
 * @createTime [2020/10/12 09:52]
 */
@Slf4j
public class CustomLogoutSuccessHandler implements LogoutSuccessHandler {

    public CustomLogoutSuccessHandler(String sigOutUrl) {
        this.signOutUrl = sigOutUrl;
    }

    /**
     * 退出登录URL
     */
    private String signOutUrl;

    /**
     * json
     */
    private final ObjectMapper objectMapper = new ObjectMapper();

    @Override
    public void onLogoutSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {

        log.info("退出成功");

        if (StringUtils.isNotEmpty(signOutUrl)) {
            response.setContentType("application/json;charset=UTF-8");
            response.getWriter().write(objectMapper.writeValueAsString(new SimpleResponse("退出成功")));
        } else {
            response.sendRedirect(signOutUrl);
        }
    }

    public String getSignOutUrl() {
        return signOutUrl;
    }

    public void setSignOutUrl(String signOutUrl) {
        this.signOutUrl = signOutUrl;
    }
}
