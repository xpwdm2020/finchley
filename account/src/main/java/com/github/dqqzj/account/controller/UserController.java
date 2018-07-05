package com.github.dqqzj.account.controller;

import com.github.dqqzj.account.domain.StatusCode;
import com.github.dqqzj.account.domain.User;
import com.github.dqqzj.account.model.request.RechargeRequest;
import com.github.dqqzj.account.model.request.RegisterRequest;
import com.github.dqqzj.account.model.response.ObjectCollectionResponse;
import com.github.dqqzj.account.model.response.ObjectDataResponse;
import com.github.dqqzj.account.model.response.RegisterResponse;
import com.github.dqqzj.account.service.UserService;
import com.github.dqqzj.common.annotation.Delay;
import com.github.dqqzj.common.annotation.RandomlyThrowsException;
import com.github.dqqzj.common.model.Shift;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

/**
 * @author qinzhongjian
 * @date created in 2018/6/26 12:54
 * @since 1.0.0
 */
@Api(tags = "UserController",description = "用户操作表")
@RestController
@RequestMapping(value = "/api/v1", produces = {MediaType.APPLICATION_JSON_UTF8_VALUE}, consumes = {MediaType.APPLICATION_JSON_UTF8_VALUE})
public class UserController {

    @Autowired
    private UserService userService;


    @Delay
    @RandomlyThrowsException
    @ApiOperation(value = "根据手机号码获取用户", notes = "")
    @RequestMapping(value = "/user/{mobile}", method = RequestMethod.GET, consumes = MediaType.ALL_VALUE)
    public ObjectDataResponse<User> findUser(@PathVariable String mobile) {

        Optional<User> optional = this.userService.getUserRepository().findById(mobile);
        if (!optional.isPresent()) {
            Shift.fatal(StatusCode.USER_NOT_EXISTS);
        }
        return new ObjectDataResponse<>(optional.get());
    }

    @Delay
    @RandomlyThrowsException
    @ApiOperation(value = "获取全部用户", notes = "")
    @RequestMapping(value = "/users", method = RequestMethod.GET, consumes = MediaType.ALL_VALUE)
    public ObjectCollectionResponse<User> findAll() {
        final List<User> userList = this.userService.getUserRepository().findAll();
        return new ObjectCollectionResponse<>(userList);
    }

    @Delay
    @RandomlyThrowsException
    @ApiOperation(value = "用户注册", notes = "注册新用户, 余额自定义, 用于下单等一系列操作")
    @RequestMapping(value = "/user", method = RequestMethod.POST)
    public RegisterResponse register(@Valid @RequestBody RegisterRequest request) {
        return this.userService.register(request);
    }

    @Delay
    @RandomlyThrowsException
    @ApiOperation(value = "用户余额变更", notes = "直接变更指定用户的余额")
    @RequestMapping(value = "/user/{mobile}/balance", method = RequestMethod.PATCH)
    public ObjectDataResponse<User> recharge(@PathVariable String mobile, @Valid @RequestBody RechargeRequest request, BindingResult error) {
        Optional<User> optional = this.userService.getUserRepository().findById(mobile);

        if (!optional.isPresent()) {
            Shift.fatal(StatusCode.USER_NOT_EXISTS);
        }
        User user = optional.get();
        user.setBalance(request.getBalance());
        this.userService.getUserRepository().saveAndFlush(user);
        return new ObjectDataResponse<>(user);
    }

}
