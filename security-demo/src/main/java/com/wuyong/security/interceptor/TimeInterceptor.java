package com.wuyong.security.interceptor;

import lombok.extern.slf4j.Slf4j;
import org.springframework.lang.Nullable;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * created by JianGuo
 * on 2018/1/17
 * description: 拦截器
 */

@Slf4j
public class TimeInterceptor implements HandlerInterceptor{


    // 控制器的方法被调用之前调用
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        log.info("TimeInterceptor preHandle");
        return false;
    }

    // 控制器的方法被调用之后调用，如果出现异常，则不会被调用
    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, @Nullable ModelAndView modelAndView) throws Exception {
        log.info("TimeInterceptor postHandle");
    }

    // 不管控制器的方法被调用还是出现异常之后都会调用
    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, @Nullable Exception ex) throws Exception {
        log.info("TimeInterceptor afterCompletion");
    }
}
