package com.wuyong.security.async;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.Callable;

/**
 * created by JianGuo
 * on 2018/1/18
 * description: 异步处理
 */
@RestController
@Slf4j
public class AsyncController {

    @RequestMapping("/order")
    public Callable<String> order() throws InterruptedException {
        log.info("主线程:{}开始",Thread.currentThread().getName());
//        Thread.sleep(1000);

        Callable callable = new Callable() {
            @Override
            public Object call() throws Exception {
                log.info("副线程:{},开始",Thread.currentThread().getName());
                Thread.sleep(1000);
                log.info("副线程结束");
                return "SUCCESS";
            }
        };
        log.info("主线程返回");
        return callable;
    }
}
