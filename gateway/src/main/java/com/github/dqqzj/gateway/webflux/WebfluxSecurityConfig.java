package com.github.dqqzj.gateway.webflux;

import com.github.dqqzj.gateway.consts.SecurityConstants;
import com.github.dqqzj.gateway.webflux.handle.MyHttpBasicAuthenticationEntryPoint;
import com.github.dqqzj.gateway.webflux.handle.MyRedirectServerLogoutSuccessHandler;
import com.github.dqqzj.gateway.webflux.handle.MyServerAuthenticationEntryPointFailureHandler;
import com.github.dqqzj.gateway.webflux.handle.MyWebFilterChainServerAuthenticationSuccessHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.reactive.PathRequest;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity;
import org.springframework.security.config.web.server.SecurityWebFiltersOrder;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.server.SecurityWebFilterChain;
import org.springframework.security.web.server.util.matcher.ServerWebExchangeMatchers;

/**
 * @author qinzhongjian
 * @date created in 2018/7/23 21:42
 * @since 1.0.0
 */
@EnableWebFluxSecurity
public class WebfluxSecurityConfig {

    @Autowired
    MyServerAuthenticationEntryPointFailureHandler myServerAuthenticationEntryPointFailureHandler;

    @Autowired
    MyWebFilterChainServerAuthenticationSuccessHandler myWebFilterChainServerAuthenticationSuccessHandler;

    @Autowired
    MyHttpBasicAuthenticationEntryPoint myHttpBasicAuthenticationEntryPoint;

    @Autowired
    MyRedirectServerLogoutSuccessHandler myRedirectServerLogoutSuccessHandler;

    @Autowired
    MyAuthenticationWebFilter myAuthenticationWebFilter;

    @Bean
    SecurityWebFilterChain springSecurityFilterChain(ServerHttpSecurity http) {
        http.csrf().disable()
                .authorizeExchange()
                .matchers(PathRequest.toStaticResources().atCommonLocations())
                .permitAll()
                .anyExchange()
                .permitAll()
                .and()
                .httpBasic()
                .and()
                .addFilterAt(myAuthenticationWebFilter,SecurityWebFiltersOrder.LOGIN_PAGE_GENERATING)
                .formLogin()
                .loginPage(SecurityConstants.DEFAULT_SIGN_IN_PROCESSING_URL_FORM)
                .requiresAuthenticationMatcher(ServerWebExchangeMatchers.pathMatchers(HttpMethod.POST,SecurityConstants.DEFAULT_SIGN_IN_PROCESSING_URL_FORM))
                .authenticationFailureHandler(myServerAuthenticationEntryPointFailureHandler)
                .authenticationSuccessHandler(myWebFilterChainServerAuthenticationSuccessHandler)
                .authenticationEntryPoint(myHttpBasicAuthenticationEntryPoint)
                .and()
                .logout()
                .logoutSuccessHandler(myRedirectServerLogoutSuccessHandler);
        return http.build();
    }
    @Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder(){
        return new BCryptPasswordEncoder();
    }
}

