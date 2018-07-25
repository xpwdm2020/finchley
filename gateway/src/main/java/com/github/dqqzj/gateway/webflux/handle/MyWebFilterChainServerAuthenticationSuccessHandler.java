package com.github.dqqzj.gateway.webflux.handle;

import com.github.dqqzj.gateway.cache.GoogleGuavaCache;
import com.github.dqqzj.gateway.utils.Jacksons;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.io.buffer.DataBuffer;
import org.springframework.core.io.buffer.DataBufferFactory;
import org.springframework.core.io.buffer.DefaultDataBufferFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.server.DefaultServerRedirectStrategy;
import org.springframework.security.web.server.ServerRedirectStrategy;
import org.springframework.security.web.server.WebFilterExchange;
import org.springframework.security.web.server.authentication.RedirectServerAuthenticationSuccessHandler;
import org.springframework.security.web.server.savedrequest.ServerRequestCache;
import org.springframework.security.web.server.savedrequest.WebSessionServerRequestCache;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.net.URI;
import java.util.concurrent.ExecutionException;


/**
 * 自定义登录失败处理
 * Created by qzj on 2018/2/2
 */

@Slf4j
@Component
public class MyWebFilterChainServerAuthenticationSuccessHandler extends RedirectServerAuthenticationSuccessHandler {

    private URI location = URI.create("/");
    private ServerRedirectStrategy redirectStrategy = new DefaultServerRedirectStrategy();
    private ServerRequestCache requestCache = new WebSessionServerRequestCache();

    public MyWebFilterChainServerAuthenticationSuccessHandler() {
    }

    public MyWebFilterChainServerAuthenticationSuccessHandler(String location) {
        this.location = URI.create(location);
    }

    public void setRequestCache(ServerRequestCache requestCache) {
        Assert.notNull(requestCache, "requestCache cannot be null");
        this.requestCache = requestCache;
    }

    public Mono<Void> onAuthenticationSuccess(WebFilterExchange webFilterExchange, Authentication authentication) {
        log.info("【ajaxAuthSuccessHandler】 onAuthenticationSuccess authentication={" + authentication.toString() + "}");
        ServerWebExchange exchange = webFilterExchange.getExchange();
        MultiValueMap<String, String> headers = new LinkedMultiValueMap();
        headers.add(HttpHeaders.AUTHORIZATION, Jacksons.parse(authentication));
        try {
            Object lastLoginTime = GoogleGuavaCache.CACHE.get(authentication.getName(), () -> null);
            headers.add("lastLoginTime",lastLoginTime.toString());
            GoogleGuavaCache.CACHE.invalidate(authentication.getName());
        } catch (ExecutionException e) {
            log.error("【GoogleGuavaCache】获取上次登陆时间错误！", e);
        }
        exchange.getResponse()
                .getHeaders()
                .addAll(headers);
        ServerHttpResponse response = webFilterExchange.getExchange().getResponse();
        DataBufferFactory dataBufferFactory = new DefaultDataBufferFactory();
        DataBuffer dataBuffer = dataBufferFactory.wrap(HttpStatus.OK.name().getBytes());
        return response.writeWith(Flux.just(dataBuffer));
//        return this.requestCache.getRedirectUri(exchange).defaultIfEmpty(this.location).flatMap((location) -> {
//            return this.redirectStrategy.sendRedirect(exchange, location);
//        });
    }

    public void setLocation(URI location) {
        Assert.notNull(location, "location cannot be null");
        this.location = location;
    }

    public void setRedirectStrategy(ServerRedirectStrategy redirectStrategy) {
        Assert.notNull(redirectStrategy, "redirectStrategy cannot be null");
        this.redirectStrategy = redirectStrategy;
    }

}
