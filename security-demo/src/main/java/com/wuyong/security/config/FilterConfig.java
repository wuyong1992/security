package com.wuyong.security.config;

import com.google.common.collect.Lists;
import com.wuyong.security.filter.LogFilter;
import com.wuyong.security.filter.TimeFilter;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

/**
 * created by JianGuo
 * on 2018/1/17
 * description: 注册filter
 */
@Configuration
public class FilterConfig {

    // 有几个注册几个
    @Bean
    public FilterRegistrationBean timeFilter(){
        FilterRegistrationBean frb = new FilterRegistrationBean();
        TimeFilter timeFilter = new TimeFilter();
        frb.setFilter(timeFilter);

        // 可以指定作用路径
        List<String> urls = Lists.newArrayList();
        urls.add("/*");
        frb.setUrlPatterns(urls);
        return frb;
    }
    @Bean
    public FilterRegistrationBean logFilter(){
        FilterRegistrationBean frb = new FilterRegistrationBean();
        LogFilter logFilter = new LogFilter();
        frb.setFilter(logFilter);

        // 可以指定作用路径
        List<String> urls = Lists.newArrayList();
        urls.add("/*");
        frb.setUrlPatterns(urls);
        return frb;
    }

}
