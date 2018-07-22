package com.github.dqqzj.account.service;

import com.github.dqqzj.account.model.Shift;
import com.github.dqqzj.account.utils.OrikaMapper;
import com.google.common.base.Objects;
import com.google.common.base.Preconditions;
import com.github.dqqzj.account.domain.StatusCode;
import com.github.dqqzj.account.domain.User;
import com.github.dqqzj.account.model.request.LoginRequest;
import com.github.dqqzj.account.model.request.RegisterRequest;
import com.github.dqqzj.account.model.response.LoginResponse;
import com.github.dqqzj.account.model.response.RegisterResponse;
import com.github.dqqzj.account.repository.UserRepository;
import lombok.Data;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.DigestUtils;

import java.util.Optional;

/**
 * @author qinzhongjian
 * @date created in 2018/6/26 17:44
 * @since 1.0.0
 */
@Data
@Service
public class UserService {

    private UserRepository userRepository;

    public UserService(final UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Transactional(rollbackFor = Exception.class)
    public RegisterResponse register(RegisterRequest request) {
        Preconditions.checkNotNull(request);
        Optional<User> dbUser = this.userRepository.findById(request.getMobile());
        if (dbUser.isPresent()) {
            Shift.fatal(StatusCode.USER_EXISTS);
        }
        // 重新计算密码
        User transientUser = OrikaMapper.map(request, User.class);
        String salt = generateRandomPasswordSalt();
        String loginPassword = digestWithSalt(transientUser.getPassword(), salt);
        transientUser.setSalt(salt);
        transientUser.setPassword(loginPassword);
        // 混合盐后入库
        this.userRepository.saveAndFlush(transientUser);
        return OrikaMapper.map(transientUser, RegisterResponse.class);
    }

    @Deprecated
    public LoginResponse login(LoginRequest request) {
        Preconditions.checkNotNull(request);
        Optional<User> optional = this.userRepository.findById(request.getMobile());
        User user = optional.get();
        if (user == null) {
            Shift.fatal(StatusCode.USER_NOT_EXISTS);
        }
        // 登录用户的密码摘要
        String requestLoginPWd = digestWithSalt(request.getPassword(), user.getSalt());
        if (!Objects.equal(requestLoginPWd, user.getPassword())) {
            Shift.fatal(StatusCode.INVALID_CREDENTIAL);
        }
        final LoginResponse response = new LoginResponse();
        response.setMobile(user.getMobile());
        response.setBalance(user.getBalance());
        return response;
    }

    private String digestWithSalt(String content, String key) {
        String result = content+key;
        result = DigestUtils.md5DigestAsHex(result.getBytes());
        return result;
    }

    private String generateRandomPasswordSalt() {
        return DigestUtils.md5DigestAsHex(String.valueOf(System.nanoTime()).getBytes());
    }


}

