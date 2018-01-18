package com.wuyong.security.aspect;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.Date;

/**
 * created by JianGuo
 * on 2018/1/18
 * description: Time 切片
 */
@Aspect
@Component
@Slf4j
public class TimeAspect {

    /**
     * Around 包围方式
     * 第一个*表示返回值，这里表示任何返回值
     * 第二个*表示UserController里面的任意方法
     * (..)表示任意参数
     * https://docs.spring.io/spring/docs/5.0.3.BUILD-SNAPSHOT/spring-framework-reference/core.html#aop-pointcuts
     */
    @Around("execution(* com.wuyong.security.controller.UserController.*(..))")
    public Object handlerControllerMethod(ProceedingJoinPoint pjp) throws Throwable {
        // ProceedingJoinPoint 程序切入点
        log.info("===TimeAspect start at :{}===", new Date());
        Object[] args = pjp.getArgs();
        Arrays.stream(args).forEach(
                (arg) -> {
                    log.info("arg is :{}", arg);
                }
        );
        Object object = pjp.proceed();
        log.info("===TimeAspect end at :{}===", new Date());
        return object;
    }

}
