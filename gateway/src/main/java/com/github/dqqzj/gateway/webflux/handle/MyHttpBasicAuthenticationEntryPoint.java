package com.github.dqqzj.gateway.webflux.handle;

import com.github.dqqzj.gateway.enums.StatusEnum;
import com.github.dqqzj.gateway.exception.RestStatusException;
import com.github.dqqzj.gateway.model.ErrorEntity;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.io.buffer.DataBuffer;
import org.springframework.core.io.buffer.DataBufferFactory;
import org.springframework.core.io.buffer.DefaultDataBufferFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.server.DefaultServerRedirectStrategy;
import org.springframework.security.web.server.ServerAuthenticationEntryPoint;
import org.springframework.security.web.server.ServerRedirectStrategy;
import org.springframework.security.web.server.savedrequest.ServerRequestCache;
import org.springframework.security.web.server.savedrequest.WebSessionServerRequestCache;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import java.net.URI;


/**
 * Created by qzj on 2018/4/26
 */

@Slf4j
@Component
public class MyHttpBasicAuthenticationEntryPoint implements ServerAuthenticationEntryPoint {

    private URI location = URI.create("/");
    private ServerRedirectStrategy redirectStrategy = new DefaultServerRedirectStrategy();
    private ServerRequestCache requestCache = new WebSessionServerRequestCache();

    @Override
    public Mono<Void> commence(ServerWebExchange serverWebExchange, AuthenticationException e) {
        ServerHttpRequest request = serverWebExchange.getRequest();
        log.info("【HttpBasicAuthenticationEntryPoint】spring security错误端点处理 -->> 请求路径:"+request.getPath());
        DataBufferFactory dataBufferFactory = new DefaultDataBufferFactory();
        ServerHttpResponse response = serverWebExchange.getResponse();
        HttpHeaders headers = request.getHeaders();
        String ajax = headers.getFirst("X-Requested-With");
        boolean isAjax = "XMLHttpRequest".equals(ajax);
        if (isAjax) {
            log.info("【HttpBasicAuthenticationEntryPoint】处理ajax错误请求");
            ErrorEntity errorEntity = new ErrorEntity(StatusEnum.valueOfCode(7001),request.getPath());
            String error = errorEntity.toString();
            log.info("打印输出异常体【ErrorEntity】-->>"+error);
            DataBuffer dataBuffer = dataBufferFactory.wrap(error.getBytes());
            return response.writeWith(Mono.just(dataBuffer));
        } else if (!response.isCommitted()) {
            log.info("【HttpBasicAuthenticationEntryPoint】处理非ajax错误请求");
            return this.requestCache.getRedirectUri(serverWebExchange).defaultIfEmpty(this.location).flatMap((location) -> {
            return this.redirectStrategy.sendRedirect(serverWebExchange, location);
        });

        }
        return Mono.error(new RestStatusException(HttpStatus.INTERNAL_SERVER_ERROR.name()));
    }
}
