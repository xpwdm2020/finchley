package com.github.dqqzj.gateway.session;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.web.DefaultRedirectStrategy;
import org.springframework.security.web.RedirectStrategy;
import org.springframework.security.web.server.WebFilterExchange;
import org.springframework.security.web.util.UrlUtils;
import org.springframework.util.AntPathMatcher;
import org.springframework.util.Assert;

import java.io.IOException;

/**
 * Created on 2018/1/27 0027.
 *
 * @author qzj
 * @since 1.0
 */

@Slf4j
public class AbstractSessionStrategy {

    /**
     * 过期跳转的url
     */
    private String destinationUrl;

    /**
     * 重定向策略
     */
    private RedirectStrategy redirectStrategy = new DefaultRedirectStrategy();

    private ObjectMapper objectMapper = new ObjectMapper();

    /**
     * 验证请求url与配置得url是否匹配得工具类
     */
    private AntPathMatcher pathMatcher = new AntPathMatcher();

    public AbstractSessionStrategy(String invalidSessionUrl) {
        Assert.isTrue(UrlUtils.isValidRedirectUrl(invalidSessionUrl), "url must start with '/' or with 'http(s)'");
        //Assert.isTrue(StringUtils.endsWithIgnoreCase(invalidSessionUrl, ".html"), "url must end with '.html'");
        this.destinationUrl = invalidSessionUrl;
    }

    /*
     * (non-Javadoc)
     *
     * @see org.springframework.security.web.session.InvalidSessionStrategy#
     * onInvalidSessionDetected(javax.servlet.http.HttpServletRequest,
     * javax.servlet.http.HttpServletResponse)
     */
    protected void onSessionInvalid(WebFilterExchange webFilterExchange) throws IOException {
        log.info("session已经失效，进行AbstractSessionStrategy操作");
//        // 跳转前创建新的session
//        webFilterExchange.getExchange().
//        request.getSession();
//        //首先判断是否是具有权限的路径
//        String requestUrl = request.getRequestURI();
//        if (pathMatcher.match("/manage/**", requestUrl) || pathMatcher.match("/adminManage/**", requestUrl) || pathMatcher.match("/transaction/**", requestUrl)){
//            if ("XMLHttpRequest".equalsIgnoreCase((request).getHeader("X-Requested-With"))) {
//                response.setStatus(HttpStatus.UNAUTHORIZED.value());
//                response.setContentType("application/json;charset=UTF-8");
//                response.getWriter().write(objectMapper.writeValueAsString(StatusEnum.SESSION_INVALID));
//            } else {
//                log.info("session失效,跳转到:" + destinationUrl);
//                redirectStrategy.sendRedirect(request, response, destinationUrl);
//            }
//        }else {
//            log.info("session失效,访问的不是具有权限的路径，不进行请求跳转");
//            redirectStrategy.sendRedirect(request, response, destinationUrl);
//        }
    }


}
