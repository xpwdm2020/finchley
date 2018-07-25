package com.github.dqqzj.gateway.controller;

import com.aliyuncs.exceptions.ClientException;
import com.github.dqqzj.gateway.aliyun.AliyunSMS;
import com.github.dqqzj.gateway.enums.StatusEnum;
import com.github.dqqzj.gateway.exception.RestStatusException;
import com.github.dqqzj.gateway.utils.Jacksons;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;

/**
 * @author qinzhongjian
 * @date created in 2018/7/25 10:01
 * @since 1.0.0
 */
@RestController
@RequestMapping("/aliyun")
public class AliyunController {
    @PostMapping("/send/{mobile}")
    public Flux<String> send(@PathVariable(value = "mobile") String mobile){
        try {
            String status = AliyunSMS.sendSms(mobile);
            return Flux.just(status);
        } catch (ClientException e) {
            throw new RestStatusException(Jacksons.parse(StatusEnum.valueOfCode(1003)));
        }

    }
}
