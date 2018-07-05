package com.github.dqqzj.account.controller;

import com.github.dqqzj.common.model.response.Response;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author qinzhongjian
 * @date created in 2018/6/26 10:38
 * @since 1.0.0
 */
@Api(tags = "_status", description = "状态码列表")
@RestController
public class RestStatusGenerator {

    @ApiOperation(value = "状态码列表")
    @RequestMapping(value = "/status", method = RequestMethod.OPTIONS)
    public Response requireStatusCodes() {
        return null;
    }

}

