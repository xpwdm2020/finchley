package com.github.dqqzj.gateway.webflux.handle;

import com.github.dqqzj.gateway.consts.SecurityConstants;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.server.DefaultServerRedirectStrategy;
import org.springframework.security.web.server.ServerRedirectStrategy;
import org.springframework.security.web.server.WebFilterExchange;
import org.springframework.security.web.server.authentication.logout.ServerLogoutSuccessHandler;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

import java.net.URI;


/**
 * Created by qzj on 2018/2/25
 */

@Slf4j
@Component
public class MyRedirectServerLogoutSuccessHandler implements ServerLogoutSuccessHandler {

    public static final String DEFAULT_LOGOUT_SUCCESS_URL = SecurityConstants.DEFAULT_URL;

    private ServerRedirectStrategy redirectStrategy = new DefaultServerRedirectStrategy();

    private URI logoutSuccessUrl = URI.create(DEFAULT_LOGOUT_SUCCESS_URL);

    public MyRedirectServerLogoutSuccessHandler() {
    }
    @Override
    public Mono<Void> onLogoutSuccess(WebFilterExchange webFilterExchange, Authentication authentication) {
        log.info(webFilterExchange.getExchange().getPrincipal()+"正在执行退出系统成功流程处理");
        return this.redirectStrategy.sendRedirect(webFilterExchange.getExchange(), this.logoutSuccessUrl);
    }

}
