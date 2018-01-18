package com.wuyong.security.browser.authentication;

import com.google.gson.Gson;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * created by JianGuo
 * on 2018/1/18
 * description: 自定义的认证通过handler
 */
@Component
@Slf4j
public class MyAuthenticationSuccessHandler implements AuthenticationSuccessHandler {



    @Override
    public void onAuthenticationSuccess(HttpServletRequest request,
                                        HttpServletResponse response,
                                        Authentication authentication) throws IOException, ServletException {
        log.info("登录成功，authentication:{}", authentication);
        response.setContentType("application/json;charset=UTF-8");
        Gson gson = new Gson();
        response.getWriter().write(gson.toJson(authentication));
    }
}
