package com.github.dqqzj.account.config;

import com.github.dqqzj.account.domain.StatusCode;
import com.github.dqqzj.common.config.SwaggerTemplate;
import com.github.dqqzj.common.model.swagger.SwaggerApiInfo;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.ResponseMessageBuilder;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.service.ResponseMessage;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.LinkedList;
import java.util.List;

/**
 * @author qinzhongjian
 * @date created in 2018/6/26 12:52
 * @since 1.0.0
 */
@EnableSwagger2
@Configuration
public class SwaggerConfiguration extends SwaggerTemplate {

    @Bean
    public SwaggerApiInfo swaggerApiInfo(final ApiInfo apiInfo) {
        return new SwaggerApiInfo(apiInfo,extractStatusCodes());
    }

    @Bean
    public ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title("Account MicroService")  //标题
                .description("Springfox petstore API")  //描述
                .termsOfServiceUrl("https://github.com/dqqzj/docker")  //超链接
                .contact(new Contact("qinzhongjian","https://github.com/dqqzj","798078824@qq.com"))   // 联系方式
                .version("2.0")
                .license("Apache License Version 2.0")
                .licenseUrl("https://github.com/springfox/springfox/blob/master/LICENSE")
                .build();
    }

    private List<ResponseMessage> extractStatusCodes() {
        final LinkedList<ResponseMessage> list = new LinkedList<>();
        for (StatusCode statusCodes : StatusCode.values()) {
            final ResponseMessageBuilder builder = new ResponseMessageBuilder();
            final ResponseMessage message = builder
                    .code(statusCodes.code())
                    .message(statusCodes.message())
                    .build();
            list.add(message);
        }
        return list;
    }

}
