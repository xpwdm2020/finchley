package com.github.dqqzj.gateway.controller;

import com.github.dqqzj.gateway.consts.SecurityConstants;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import reactor.core.publisher.Mono;

/**
 * @author qinzhongjian
 * @date created in 2018/7/22 13:57
 * @since 1.0.0
 */
@Controller
public class WebfluxViewController {

    @GetMapping(SecurityConstants.DEFAULT_URL)
    public Mono<String> index() {
        return Mono.create(monoSink -> monoSink.success("index"));
    }

    @GetMapping(SecurityConstants.DEFAULT_UNAUTHENTICATION_URL)
    public Mono<String> login() {
        return Mono.create(monoSink -> monoSink.success("login"));
    }
    @GetMapping(SecurityConstants.DEFAULT_REGISTER_URL)
    public Mono<String> register() {
        return Mono.create(monoSink -> monoSink.success("register"));
    }




}
