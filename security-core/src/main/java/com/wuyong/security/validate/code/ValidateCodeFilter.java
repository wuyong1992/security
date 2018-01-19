package com.wuyong.security.validate.code;

import lombok.Getter;
import lombok.Setter;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.social.connect.web.HttpSessionSessionStrategy;
import org.springframework.social.connect.web.SessionStrategy;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.ServletRequestBindingException;
import org.springframework.web.bind.ServletRequestUtils;
import org.springframework.web.context.request.ServletWebRequest;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * created by JianGuo
 * on 2018/1/19
 * description: 图形验证码过滤器
 */
@Getter@Setter
public class ValidateCodeFilter extends OncePerRequestFilter {

    @Autowired
    private AuthenticationFailureHandler authenticationFailureHandler;

    private SessionStrategy sessionStrategy = new HttpSessionSessionStrategy();

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        // 编写自己的逻辑
        if (StringUtils.equals("/authentication/form", request.getRequestURI())
                && StringUtils.equalsIgnoreCase("post", request.getMethod())) {
            try {
                validate(new ServletWebRequest(request));
            } catch (ValidateCodeException ex) {
                // 如果有异常 使用自定义的认证失败处理器
                // 已经注册为自己编写的错误处理器了
                authenticationFailureHandler.onAuthenticationFailure(request, response, ex);
                return; // 处理完失败就直接return，不能继续往下走了 否则还是会进入UsernamePasswordAuthenticationFilter去校验账号密码
            }
        }
        // 如果不是登录请求
        filterChain.doFilter(request, response);
    }

    private void validate(ServletWebRequest servletWebRequest) throws ServletRequestBindingException {
        // 从session中获取imageCode对象
        ImageCode imageCodeFromSession = (ImageCode) sessionStrategy.getAttribute(servletWebRequest, ValidateCodeController.SESSION_KEY);
        // 从request请求中获取name = imageCode 的登录输入的验证码值
        String codeFromRequest = ServletRequestUtils.getStringParameter(servletWebRequest.getRequest(), "imageCode");

        if (StringUtils.isBlank(codeFromRequest)) {
            throw new ValidateCodeException("验证码不能为空");
        }
        if (imageCodeFromSession == null) {
            throw new ValidateCodeException("验证码不存在");
        }
        if (imageCodeFromSession.isExpried()) {
            throw new ValidateCodeException("验证码过期");
        }
        if (!StringUtils.equals(imageCodeFromSession.getCode(), codeFromRequest)) {
            throw new ValidateCodeException("验证码不匹配");
        }
        sessionStrategy.removeAttribute(servletWebRequest, ValidateCodeController.SESSION_KEY);
    }
}
