package com.github.dqqzj.gateway.webflux;

import com.github.dqqzj.gateway.cache.GoogleGuavaCache;
import com.github.dqqzj.gateway.entity.UserDTO;
import com.github.dqqzj.gateway.enums.StatusEnum;
import com.github.dqqzj.gateway.exception.RestStatusException;
import com.github.dqqzj.gateway.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.ReactiveUserDetailsService;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import java.time.LocalDateTime;

/**
 * @author qinzhongjian
 * @date created in 2018/7/23 22:11
 * @since 1.0.0
 */
@Service
public class WebfluxReactiveUserDetailsService implements ReactiveUserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public Mono<UserDetails> findByUsername(String username) {
        UserDTO userDTO = this.userRepository.findById(username).orElseThrow(()->new RestStatusException(StatusEnum.valueOfCode(2002).getMessage()));
        GoogleGuavaCache.CACHE.put(username,userDTO.getLastLoginTime());
        userDTO.setLastLoginTime(LocalDateTime.now());
        this.userRepository.save(userDTO);
        return userDTO == null ? Mono.empty() : Mono.just(userDTO);
    }

}
