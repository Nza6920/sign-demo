package com.niu.sign.config;

import com.niu.sign.handler.CustomLogoutSuccessHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.RememberMeServices;
import org.springframework.security.web.authentication.rememberme.JdbcTokenRepositoryImpl;
import org.springframework.security.web.authentication.rememberme.PersistentTokenRepository;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;
import java.io.IOException;

/**
 * security 配置
 *
 * @author [nza]
 * @version 1.0 [2020/10/10 15:59]
 * @createTime [2020/10/10 15:59]
 */
@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private AuthenticationSuccessHandler customAuthenticationSuccessHandler;

    @Autowired
    private AuthenticationFailureHandler customAuthenticationFailureHandler;

    @Autowired
    private UserDetailsService userDetailsService;

    @Autowired
    private SmsCodeAuthenticationSecurityConfig smsCodeAuthenticationSecurityConfig;

    @Autowired
    private ValidateCodeSecurityConfig validateCodeSecurityConfig;

    @Autowired
    private DataSource dataSource;

    /**
     * URL 白名单
     */
    private static final String[] URL_WHITE_LIST = new String[]{
            "/authentication/require",
            "/my-login.html",
            "/favicon.ico",
            "/code/*"};

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.apply(validateCodeSecurityConfig)
                .and()
                .formLogin()
                // 登录页
                .loginPage("/authentication/require")
                // 登录处理url
                .loginProcessingUrl("/authentication/form")
                // 登录成功处理器
                .successHandler(customAuthenticationSuccessHandler)
                // 登录失败处理器
                .failureHandler(customAuthenticationFailureHandler)
                .and()
                .apply(smsCodeAuthenticationSecurityConfig)
                .and()
                .rememberMe()
                .rememberMeParameter("remember-me")
                // 记住我存储器
                .tokenRepository(persistentTokenRepository())
                .tokenValiditySeconds(3600)
                .userDetailsService(userDetailsService)
                .and()
                .authorizeRequests()
                // 白名单
                .antMatchers(URL_WHITE_LIST)
                .permitAll()
                .anyRequest()
                .authenticated()
                .and()
                .csrf().disable()
                .logout()
                // 退出登录url
                .logoutUrl("/signOut")
                // 退出登录成功处理器
                .logoutSuccessHandler(new CustomLogoutSuccessHandler("/signOut"))
                .deleteCookies("JSESSIONID")
                .and()
                .exceptionHandling()
                // 异常处理
                .authenticationEntryPoint(new AuthenticationEntryPoint() {
                    @Override
                    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException) throws IOException, ServletException {
                        response.setHeader("Access-Control-Allow-Origin", "*");
                        response.setHeader("Cache-Control","no-cache");
                        response.setCharacterEncoding("UTF-8");
                        response.setContentType("application/json");
                        response.getWriter().println(authException.getMessage());
                        response.getWriter().flush();
                    }
                });
    }

    @Bean
    public PersistentTokenRepository persistentTokenRepository() {
        // 记住我
        JdbcTokenRepositoryImpl jdbcTokenRepository = new JdbcTokenRepositoryImpl();
        jdbcTokenRepository.setDataSource(dataSource);
        return jdbcTokenRepository;
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
