package com.github.dqqzj.gateway.config;

//import org.apache.catalina.connector.Connector;
//import org.apache.coyote.http11.Http11NioProtocol;
//import org.springframework.boot.web.embedded.netty.NettyReactiveWebServerFactory;
//import org.springframework.boot.web.embedded.netty.NettyServerCustomizer;
//import org.springframework.boot.web.embedded.tomcat.TomcatConnectorCustomizer;
//import org.springframework.boot.web.embedded.tomcat.TomcatServletWebServerFactory;
import org.springframework.boot.web.server.WebServerFactoryCustomizer;
import org.springframework.boot.web.servlet.server.ConfigurableServletWebServerFactory;
import org.springframework.stereotype.Component;

/**
 * @author qinzhongjian
 * @date created in 2018/7/21 14:08
 * @since 1.0.0
 */
@Component
public class EmbeddedTomcatConfig implements WebServerFactoryCustomizer<ConfigurableServletWebServerFactory> {

    @Override
    public void customize(ConfigurableServletWebServerFactory factory) {
//        ((NettyReactiveWebServerFactory)factory).addServerCustomizers(new NettyServerCustomizer() {
//            @Override
//            public void customize(reactor.ipc.netty.http.server.HttpServerOptions.Builder builder) {
//
//            }
//        });
    }
}

