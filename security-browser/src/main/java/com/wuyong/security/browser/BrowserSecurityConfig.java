package com.wuyong.security.browser;

import com.alibaba.druid.pool.DruidDataSource;
import com.wuyong.security.browser.authentication.MyAuthenticationFailureHandler;
import com.wuyong.security.browser.authentication.MyAuthenticationSuccessHandler;
import com.wuyong.security.properties.SecurityProperties;
import com.wuyong.security.validate.code.ValidateCodeFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.authentication.rememberme.JdbcTokenRepositoryImpl;
import org.springframework.security.web.authentication.rememberme.PersistentTokenRepository;

import javax.sql.DataSource;


/**
 * created by JianGuo
 * on 2018/1/17
 * description:
 * spring boot 静态文件默认加载路径
 * "classpath:/META-INF/resources/", "classpath:/resources/", classpath:/static/", "classpath:/public/"
 */
@Configuration
public class BrowserSecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private SecurityProperties securityProperties;
    @Autowired
    private MyAuthenticationSuccessHandler myAuthenticationSuccessHandler;
    @Autowired
    private MyAuthenticationFailureHandler myAuthenticationFailureHandler;
    @Autowired
    private MyUserDetailsService myUserDetailsService;

    // todo question
    @Autowired
    private DataSource dataSource;


    @Override
    protected void configure(HttpSecurity http) throws Exception {

        ValidateCodeFilter validateCodeFilter = new ValidateCodeFilter();
        // 设置错误处理器
        validateCodeFilter.setAuthenticationFailureHandler(myAuthenticationFailureHandler);
        validateCodeFilter.setSecurityProperties(securityProperties);
        // 调用初始化方法
        validateCodeFilter.afterPropertiesSet();

        http.addFilterBefore(validateCodeFilter, UsernamePasswordAuthenticationFilter.class)
                .formLogin()    // 认证方式 这里是表单登录
//                  .loginPage("/login.html") // 自定义的登录界面
                    .loginPage("/authentication/require")   // 自定义登录的controller
                    .loginProcessingUrl("/authentication/form") // 自定义的表单上传路径
                    .successHandler(myAuthenticationSuccessHandler) // 配置自定义的成功处理器
                    .failureHandler(myAuthenticationFailureHandler) //配置自定义的认证失败处理器
                .and()
                .rememberMe()
                    .tokenRepository(persistentTokenRepository())
                    .tokenValiditySeconds(securityProperties.getBrowser().getRememberMeSeconds())
                    .userDetailsService(myUserDetailsService)
                .and()
                .authorizeRequests()    // 对下面请求进行授权
                // .antMatchers("/**").permitAll() //符合路劲规范的可以放心
//                .antMatchers("/login.html").permitAll()   // 自身的这个页面必须放行
                .antMatchers("/authentication/require",
                        securityProperties.getBrowser().getLoginPage(),
                        "/code/image").permitAll()
                .anyRequest()   // 任何请求
                .authenticated()    // 都需要身份认证
                .and()
                .csrf().disable();  // 临时关闭跨站伪造防护的功能
    }


    @Bean
    public PasswordEncoder passwordEncoder() {
        // 或者实现自己的PasswordEncoder
        return new BCryptPasswordEncoder();
    }

    @Bean
    public PersistentTokenRepository persistentTokenRepository(){
        JdbcTokenRepositoryImpl jdbcTokenRepository = new JdbcTokenRepositoryImpl();
        jdbcTokenRepository.setDataSource(dataSource);
        jdbcTokenRepository.setCreateTableOnStartup(true);
        return jdbcTokenRepository;
    }
}
