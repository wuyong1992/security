package com.wuyong.security.browser.authentication;

import com.google.gson.Gson;
import com.wuyong.security.browser.support.SimpleResponse;
import lombok.extern.slf4j.Slf4j;

import org.apache.http.HttpStatus;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * created by JianGuo
 * on 2018/1/19
 * description: 自定义认证失败处理器
 */

@Component
@Slf4j
public class MyAuthenticationFailureHandler implements AuthenticationFailureHandler {
    @Override
    public void onAuthenticationFailure(HttpServletRequest request,
                                        HttpServletResponse response,
                                        AuthenticationException exception) throws IOException, ServletException {
      log.info("认证失败,error：{}",exception);
        response.setStatus(HttpStatus.SC_INTERNAL_SERVER_ERROR);
        Gson gson = new Gson();
        response.setContentType("application/json;charset=utf-8");
//        response.getWriter().write(gson.toJson(exception));
        response.getWriter().write(gson.toJson(new SimpleResponse(exception.getMessage())));
    }
}
