package com.wuyong.security.interceptor;

import lombok.extern.slf4j.Slf4j;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Date;

/**
 * created by JianGuo
 * on 2018/1/17
 * description: 拦截器
 */

@Slf4j
@Component  // 和filter不同的是，这里声明component并不能直接使用，还需要配置
public class TimeInterceptor implements HandlerInterceptor {


    // 控制器的方法被调用之前调用
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        // log.info("===TimeInterceptor preHandle on :{}===", new Date());

        // 通过反射获取handler信息
        // 并不能拿到对象具体参数的值
        // log.info("===handler bean 类型：{}===", ((HandlerMethod) handler).getBean().getClass().getName());
        // log.info("===handler bean 方法名：{}===", ((HandlerMethod) handler).getMethod().getName());

        // 为了在preHandle与postHandle之间传递信息
        request.setAttribute("startTime", new Date().getTime());
        // 返回值决定了后面是否执行，返回false 后面不会被执行，进入不了方法体,返回true进入方法体
        return true;
    }

    // 控制器的方法被调用之后调用，如果抛出异常，则不会被调用
    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, @Nullable ModelAndView modelAndView) throws Exception {
        // log.info("===TimeInterceptor postHandle on :{}===", new Date());

        Long startTime = (Long) request.getAttribute("startTime");
        // log.info("===TimeInterceptor postHandle 耗时=== ：{}", new Date().getTime() - startTime);
    }

    // 不管控制器的方法被正常调用还是抛出异常之后都会调用
    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, @Nullable Exception ex) throws Exception {
        // log.info("===TimeInterceptor afterCompletion on :{}===", new Date());

        Long startTime = (Long) request.getAttribute("startTime");
        // log.info("===TimeInterceptor postHandle 耗时=== ：{}", new Date().getTime() - startTime);
        // 如果对抛出的异常配置了异常处理器则这里捕捉不到异常，为null，如果抛出的异常并没有配置额外的处理器，则会被这里处理
        // 如果被这里捕获日志往上翻会看到异常信息
        // 不仅拦截自己写的异常，spring本身框架的异常处理机制也会被拦截
        // log.info("===TimeInterceptor postHandle exception is:{}===", ex);
    }
}
