package com.github.dqqzj.common.web.filter;

import com.google.common.base.Charsets;
import com.github.dqqzj.common.config.RequestAttributeConst;
import com.github.dqqzj.common.web.ServletContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author qinzhongjian
 * @date created in 2018/6/26 11:08
 * @since 1.0.0
 */
public class ResettableRequestFilter extends OncePerRequestFilter {

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {
        ResettableStreamHttpServletRequest wrapperRequest = new ResettableStreamHttpServletRequest(request);
        byte[] body = wrapperRequest.getRequestBody();
        if (body != null) {
            ServletContextHolder.getRequest().setAttribute(RequestAttributeConst.REQUEST_BODY_KEY, new String(body, Charsets.UTF_8));
        }
        super.doFilter(wrapperRequest, response, filterChain);
    }

}

