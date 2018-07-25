package com.github.dqqzj.gateway.webflux;


import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.ReactiveAuthenticationManager;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.ReactiveUserDetailsService;
import org.springframework.util.Assert;
import reactor.core.publisher.Mono;

/**
 * @author qinzhongjian
 * @date created in 2018/7/23 22:49
 * @since 1.0.0
 */
public class MyUserDetailsRepositoryReactiveAuthenticationManager implements ReactiveAuthenticationManager{

    private  ReactiveUserDetailsService userDetailsService;

    public MyUserDetailsRepositoryReactiveAuthenticationManager(ReactiveUserDetailsService userDetailsService) {
        Assert.notNull(userDetailsService, "userDetailsService cannot be null");
        this.userDetailsService = userDetailsService;
    }

    public Mono<Authentication> authenticate(Authentication authentication) {
        String username = authentication.getName();
        return this.userDetailsService.findByUsername(username).switchIfEmpty(Mono.defer(() ->
             Mono.error(new BadCredentialsException("Invalid Credentials"))
        )).map(userDetails -> new MyAuthenticationToken(userDetails,userDetails.getAuthorities()));

    }

}
