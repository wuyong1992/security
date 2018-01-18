package com.wuyong.security.browser;

import com.wuyong.security.browser.support.SimpleResponse;
import com.wuyong.security.properties.SecurityProperties;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.web.DefaultRedirectStrategy;
import org.springframework.security.web.RedirectStrategy;
import org.springframework.security.web.savedrequest.HttpSessionRequestCache;
import org.springframework.security.web.savedrequest.RequestCache;
import org.springframework.security.web.savedrequest.SavedRequest;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * created by JianGuo
 * on 2018/1/17
 * description:
 */
@RestController
@Slf4j
public class BrowserSecurityController {

    // 将请求缓存进session
    private RequestCache requestCache = new HttpSessionRequestCache();

    // 跳转工具
    private RedirectStrategy redirectStrategy = new DefaultRedirectStrategy();

    @Autowired
    private SecurityProperties securityProperties;

    /**
     * 当需要身份认证的时候进入该controller
     * @param request
     * @param response
     * @return
     */
    @RequestMapping("/authentication/require")
    @ResponseStatus(code = HttpStatus.UNAUTHORIZED)
    public SimpleResponse requireAuthenticaiton(HttpServletRequest request, HttpServletResponse response) throws IOException {
        // 从session中拿出缓存的请求信息
        SavedRequest savedRequest = requestCache.getRequest(request, response);
        if (savedRequest != null) {
            String targetUrl = savedRequest.getRedirectUrl();
            log.info("请求的url：{}", targetUrl);
            // 如果请求是html请求
            if (StringUtils.endsWithIgnoreCase(targetUrl, ".html")) {
                // securityProperties.getBrowser().getLoginPage() 跳转路径
                redirectStrategy.sendRedirect(request, response, securityProperties.getBrowser().getLoginPage());
            }
        }

        return new SimpleResponse("访问服务需要身份认证，请登录");
    }


}
