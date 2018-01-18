package com.wuyong.security.filter;

import lombok.extern.slf4j.Slf4j;

import javax.servlet.*;
import java.io.IOException;
import java.util.Date;

/**
 * created by JianGuo
 * on 2018/1/18
 * description: 测试日志过滤器
 */
@Slf4j
public class LogFilter implements Filter{
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
      // log.info("===LogFilter init===");
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        // log.info("===LogFilter start at : {}===",new Date());
        chain.doFilter(request, response);
        // log.info("===LogFilter finish at : {}===", new Date());
    }

    @Override
    public void destroy() {
        log.info("===LogFilter init===");
    }
}
