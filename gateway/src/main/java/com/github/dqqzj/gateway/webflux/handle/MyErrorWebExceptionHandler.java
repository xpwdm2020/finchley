package com.github.dqqzj.gateway.webflux.handle;

import org.springframework.boot.autoconfigure.web.ResourceProperties;
import org.springframework.boot.autoconfigure.web.reactive.error.AbstractErrorWebExceptionHandler;
import org.springframework.boot.web.reactive.error.ErrorAttributes;
import org.springframework.context.ApplicationContext;
import org.springframework.http.codec.HttpMessageReader;
import org.springframework.http.codec.HttpMessageWriter;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import org.springframework.web.reactive.result.view.ViewResolver;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import java.util.List;
import java.util.Map;

/**
 * @author qinzhongjian
 * @date created in 2018/7/25 15:34
 * @since 1.0.0
 */
public class MyErrorWebExceptionHandler extends AbstractErrorWebExceptionHandler {
    public MyErrorWebExceptionHandler(ErrorAttributes errorAttributes, ResourceProperties resourceProperties, ApplicationContext applicationContext) {
        super(errorAttributes, resourceProperties, applicationContext);
    }

    @Override
    public void setMessageWriters(List<HttpMessageWriter<?>> messageWriters) {
        super.setMessageWriters(messageWriters);
    }

    @Override
    public void setMessageReaders(List<HttpMessageReader<?>> messageReaders) {
        super.setMessageReaders(messageReaders);
    }

    @Override
    public void setViewResolvers(List<ViewResolver> viewResolvers) {
        super.setViewResolvers(viewResolvers);
    }

    @Override
    protected Map<String, Object> getErrorAttributes(ServerRequest request, boolean includeStackTrace) {
        return super.getErrorAttributes(request, includeStackTrace);
    }

    @Override
    protected Throwable getError(ServerRequest request) {
        return super.getError(request);
    }

    @Override
    protected boolean isTraceEnabled(ServerRequest request) {
        return super.isTraceEnabled(request);
    }

    @Override
    protected Mono<ServerResponse> renderErrorView(String viewName, ServerResponse.BodyBuilder responseBody, Map<String, Object> error) {
        return super.renderErrorView(viewName, responseBody, error);
    }

    @Override
    protected Mono<ServerResponse> renderDefaultErrorView(ServerResponse.BodyBuilder responseBody, Map<String, Object> error) {
        return super.renderDefaultErrorView(responseBody, error);
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        super.afterPropertiesSet();
    }

    @Override
    public Mono<Void> handle(ServerWebExchange exchange, Throwable throwable) {
        return super.handle(exchange, throwable);
    }

    @Override
    protected RouterFunction<ServerResponse> getRoutingFunction(ErrorAttributes errorAttributes) {
        return null;
    }
}
