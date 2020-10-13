package com.niu.sign.controller;

import com.niu.sign.validate.process.ValidateCodeProcessor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.ServletWebRequest;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;

/**
 * 验证码控制器
 *
 * @author [nza]
 * @version 1.0 [2020/10/12 13:38]
 * @createTime [2020/10/12 13:38]
 */
@RestController
@Slf4j
public class ValidateCodeController {

    @Autowired
    private Map<String, ValidateCodeProcessor> validateCodeProcessors;

    /**
     * 创建验证码
     *
     * @param req  请求
     * @param resp 响应
     * @param type 类型
     * @throws {@link Exception} 生成验证码失败抛出
     * @author nza
     * @createTime 2020/10/12 13:41
     */
    @GetMapping("/code/{type}")
    public void createCode(HttpServletRequest req, HttpServletResponse resp, @PathVariable String type) throws Exception {
        validateCodeProcessors.get(type + "ValidateCodeProcessor").create(new ServletWebRequest(req, resp));
    }
}
