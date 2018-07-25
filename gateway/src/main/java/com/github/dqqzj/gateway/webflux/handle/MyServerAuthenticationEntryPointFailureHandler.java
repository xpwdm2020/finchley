package com.github.dqqzj.gateway.webflux.handle;

import com.github.dqqzj.gateway.enums.StatusEnum;
import com.github.dqqzj.gateway.model.ErrorEntity;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.io.buffer.DataBuffer;
import org.springframework.core.io.buffer.DataBufferFactory;
import org.springframework.core.io.buffer.DefaultDataBufferFactory;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.server.WebFilterExchange;
import org.springframework.security.web.server.authentication.ServerAuthenticationFailureHandler;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;


/**
 * 自定义登录失败处理
 * Created by qzj on 2018/2/2
 */

@Slf4j
@Component
public class MyServerAuthenticationEntryPointFailureHandler implements ServerAuthenticationFailureHandler {

//    private final ServerAuthenticationEntryPoint authenticationEntryPoint;
//
//    public MyServerAuthenticationEntryPointFailureHandler(ServerAuthenticationEntryPoint authenticationEntryPoint) {
//        Assert.notNull(authenticationEntryPoint, "authenticationEntryPoint cannot be null");
//        this.authenticationEntryPoint = authenticationEntryPoint;
//    }

    @Override
    public Mono<Void> onAuthenticationFailure(WebFilterExchange webFilterExchange, AuthenticationException e) {
        ServerHttpRequest request = webFilterExchange.getExchange().getRequest();
        ServerHttpResponse response = webFilterExchange.getExchange().getResponse();
        log.info("【MyServerAuthenticationEntryPointFailureHandler】请求路径："+webFilterExchange.getExchange().getRequest().getPath(),e);
        DataBufferFactory dataBufferFactory = new DefaultDataBufferFactory();
        ErrorEntity errorEntity = new ErrorEntity(StatusEnum.valueOfCode(1001),request.getPath());
        String error = errorEntity.toString();
        log.info("打印输出异常体【ErrorEntity】-->> "+error);
        DataBuffer dataBuffer = dataBufferFactory.wrap(StatusEnum.valueOfCode(1001).getMessage().getBytes());
        return response.writeWith(Mono.just(dataBuffer));
    }
}
