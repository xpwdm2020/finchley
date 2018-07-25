package com.github.dqqzj.gateway.service;

import com.github.dqqzj.gateway.entity.UserDTO;
import com.github.dqqzj.gateway.enums.RoleEnum;
import com.github.dqqzj.gateway.exception.RestStatusException;
import com.github.dqqzj.gateway.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@Primary
public class BaseUserService {

    @Autowired
    private UserRepository userRepository;

    BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();

    public BaseUserService(UserRepository userRepository){
        this.userRepository = userRepository;
    }

    /**
     * 注册用户
     * @param userDTO
     * @return
     */
    public UserDTO register(UserDTO userDTO) {
        log.info("新用户开始进行注册操作");
        String username = userDTO.getUsername();
        if (exist(username))
            throw new RestStatusException("该用户已存在");
        userDTO.setPassword(bCryptPasswordEncoder.encode(userDTO.getPassword()));
        userDTO.setRole(RoleEnum.valueOfCode(2).getRole());
        return this.userRepository.save(userDTO);
    }

    /**
     * 判断用户是否存在
     * @param username 账号
     * @return 密码
     */
    private boolean exist(String username){
        log.info("检查用户是否存在");
        return this.userRepository.findById(username).isPresent();
    }

}
