package com.wuyong.security.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * created by JianGuo
 * on 2018/1/17
 * description:
 */
@RestController
public class HelloController {


    @RequestMapping("/hello")
    public String sayHello(){
        return "Hello Spring Security!";
    }
}
