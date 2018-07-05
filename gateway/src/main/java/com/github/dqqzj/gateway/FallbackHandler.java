package com.github.dqqzj.gateway;

import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

/**
 * @author qinzhongjian
 * @date created in 2018/6/28 20:56
 * @since 1.0.0
 */
@Service
public class FallbackHandler {

    public Mono<ServerResponse> fallback(ServerRequest request){
        return ServerResponse.ok()
                .contentType(MediaType.TEXT_PLAIN)
                .body(BodyInserters.fromObject("fallback"));
    }
}

