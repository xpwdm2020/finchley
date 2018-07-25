package com.github.dqqzj.gateway.controller;

import com.aliyuncs.exceptions.ClientException;
import com.github.dqqzj.gateway.aliyun.AliyunSMS;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

/**
 * Created by qzj on 2018/2/26
 */

@Slf4j
@RestController
@RequestMapping("/aliyun")
public class SmsCodeController {

    @GetMapping("/send/{phone}")
    public Mono<String> sendSms(@PathVariable String phone){
        try {
            return Mono.just(AliyunSMS.sendSms(phone));
        } catch (ClientException e) {
            log.error("获取阿里云验证码失败",e);
        }
        return Mono.empty();
    }

}
