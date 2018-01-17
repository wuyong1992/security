package com.wuyong.security.filter;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import javax.servlet.*;
import java.io.IOException;
import java.util.Date;

/**
 * created by JianGuo
 * on 2018/1/17
 * description: 过滤器的使用
 * 1、加上component注解即可使用，过滤所有路径
 * 2、例如第三方的过滤器需要些配置项
 */
@Slf4j
//@Component
public class TimeFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        log.info("TimeFilter init");
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        long time = new Date().getTime();
        log.info("TimeFilter start time :{}", time);
        chain.doFilter(request, response);
        log.info("TimeFilter finish time:{}", new Date().getTime() - time);
    }

    @Override
    public void destroy() {
        log.info("TimeFilter destroy");
    }
}
