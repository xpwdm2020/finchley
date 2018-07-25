package com.github.dqqzj.gateway.webflux;

import com.github.dqqzj.gateway.cache.GoogleGuavaCache;
import com.github.dqqzj.gateway.consts.SecurityConstants;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.ReactiveAuthenticationManager;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.ReactiveSecurityContextHolder;
import org.springframework.security.core.context.SecurityContextImpl;
import org.springframework.security.web.server.ServerHttpBasicAuthenticationConverter;
import org.springframework.security.web.server.WebFilterExchange;
import org.springframework.security.web.server.authentication.*;
import org.springframework.security.web.server.context.NoOpServerSecurityContextRepository;
import org.springframework.security.web.server.context.ServerSecurityContextRepository;
import org.springframework.security.web.server.util.matcher.ServerWebExchangeMatcher;
import org.springframework.security.web.server.util.matcher.ServerWebExchangeMatchers;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import org.springframework.web.server.WebFilter;
import org.springframework.web.server.WebFilterChain;
import reactor.core.publisher.Mono;

import java.util.concurrent.ExecutionException;
import java.util.function.Function;


/**
 * @author qinzhongjian
 * @date created in 2018/7/23 22:45
 * @since 1.0.0
 */
@Slf4j
@Component
public class MyAuthenticationWebFilter implements WebFilter {

    private WebfluxReactiveUserDetailsService webfluxReactiveUserDetailsService = new WebfluxReactiveUserDetailsService();
    private  ReactiveAuthenticationManager authenticationManager = new MyUserDetailsRepositoryReactiveAuthenticationManager(webfluxReactiveUserDetailsService);
    @Autowired
    private ServerAuthenticationSuccessHandler authenticationSuccessHandler;
    private Function<ServerWebExchange, Mono<Authentication>> authenticationConverter = new ServerHttpBasicAuthenticationConverter();
    @Autowired
    private ServerAuthenticationFailureHandler authenticationFailureHandler;
    private ServerSecurityContextRepository securityContextRepository = NoOpServerSecurityContextRepository.getInstance();
    private ServerWebExchangeMatcher requiresAuthenticationMatcher = ServerWebExchangeMatchers.pathMatchers(HttpMethod.POST,SecurityConstants.DEFAULT_SIGN_IN_PROCESSING_URL_MOBILE);

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, WebFilterChain chain) {
        return this.requiresAuthenticationMatcher.matches(exchange)
                .filter((matchResult) -> matchResult.isMatch())
                .filter(matchResult -> {
                    log.info("【MyAuthenticationWebFilter】短信登陆过滤器开始执行");
                    String phone = exchange.getRequest().getQueryParams().getFirst(SecurityConstants.DEFAULT_PARAMETER_NAME_MOBILE);
                    String sms = exchange.getRequest().getQueryParams().getFirst(SecurityConstants.DEFAULT_PARAMETER_NAME_CODE_SMS);
                    try {
                        String cache = (String) GoogleGuavaCache.CACHE.get(phone,() -> null);
                        //GoogleGuavaCache.CACHE.invalidate(phone);
                        return sms.equals(cache);
                    } catch (ExecutionException e) {
                        log.error("【GoogleGuavaCache】获取上次登陆时间错误！", e);
                    }
                    return false;
                })
                .flatMap((matchResult) -> (Mono)this.authenticationConverter.apply(exchange))
                .switchIfEmpty(chain.filter(exchange).then(Mono.empty()))
                .flatMap((token) -> this.authenticate(exchange, chain, (MyAuthenticationToken) token));
    }
    private Mono<Void> authenticate(ServerWebExchange exchange, WebFilterChain chain, Authentication token) {
        WebFilterExchange webFilterExchange = new WebFilterExchange(exchange, chain);
        return this.authenticationManager.authenticate(token).flatMap((authentication) -> this.onAuthenticationSuccess(authentication, webFilterExchange))
            .onErrorResume(AuthenticationException.class, (e) -> this.authenticationFailureHandler.onAuthenticationFailure(webFilterExchange, e)
        );
    }
    private Mono<Void> onAuthenticationSuccess(Authentication authentication, WebFilterExchange webFilterExchange) {
        ServerWebExchange exchange = webFilterExchange.getExchange();
        SecurityContextImpl securityContext = new SecurityContextImpl();
        securityContext.setAuthentication(authentication);
        return this.securityContextRepository.save(exchange, securityContext).then(this.authenticationSuccessHandler.onAuthenticationSuccess(webFilterExchange, authentication)).subscriberContext(ReactiveSecurityContextHolder.withSecurityContext(Mono.just(securityContext)));
    }

}

