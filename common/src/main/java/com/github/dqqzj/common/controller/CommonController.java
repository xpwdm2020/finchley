package com.github.dqqzj.common.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


/**
 * Created by qzj on 2018/2/26
 */

@RestController
@RequestMapping("/test")
@Api(tags = "CommonController",description = "测试控制器")
public class CommonController {

    @GetMapping("/send/{phone}")
    @ApiOperation(value = "获取验证码", notes = "该验证码适用于任何地方")
    public ResponseEntity sendSms(@PathVariable String phone){
            return ResponseEntity.status(HttpStatus.OK).body(phone);
    }
}
