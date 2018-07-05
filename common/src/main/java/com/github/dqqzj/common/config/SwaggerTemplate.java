package com.github.dqqzj.common.config;

import com.fasterxml.classmate.TypeResolver;
import com.google.common.collect.ImmutableList;
import com.github.dqqzj.common.model.swagger.SwaggerApiInfo;
import com.github.dqqzj.common.model.swagger.SwaggerPaginationResponse;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.data.web.SpringDataWebProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Import;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.RequestMethod;
import springfox.bean.validators.configuration.BeanValidatorPluginsConfiguration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.schema.AlternateTypeRules;
import springfox.documentation.schema.WildcardType;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.service.ResponseMessage;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger.web.UiConfiguration;

import java.util.Collection;
import java.util.List;

/**
 * @author qinzhongjian
 * @date created in 2018/6/26 12:15
 * @since 1.0.0
 */
@Import(BeanValidatorPluginsConfiguration.class)
public class SwaggerTemplate {

    @Bean
    @ConditionalOnMissingBean
    public SwaggerApiInfo swaggerApiInfo(final ApiInfo apiInfo) {
        return new SwaggerApiInfo(apiInfo,null);

    }

    @Bean
    @ConditionalOnMissingBean
    public ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title("Springfox petstore API")  //标题
                .termsOfServiceUrl("http://springfox.io")//超链接
                .contact(new Contact("qinzhongjian","https://github.com/dqqzj","798078824@qq.com"))   // 联系方式
                .version("2.0")
                .license("Apache License Version 2.0")
                .licenseUrl("https://github.com/springfox/springfox/blob/master/LICENSE")
                .build();
    }


    @Bean
    public Docket configure(SwaggerApiInfo swaggerApiInfo, TypeResolver typeResolver) {
        return new Docket(DocumentationType.SWAGGER_2)
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.github.dqqzj"))
                .paths(PathSelectors.any())
                .build()
                .pathMapping("/")
                .useDefaultResponseMessages(false)
                .globalResponseMessage(RequestMethod.OPTIONS, swaggerApiInfo.getResponseMessages())
                .apiInfo(swaggerApiInfo.getApiInfo())
                .alternateTypeRules(
                        AlternateTypeRules.newRule(
                                typeResolver.resolve(Page.class, WildcardType.class),
                                typeResolver.resolve(SwaggerPaginationResponse.class, WildcardType.class)),
                        AlternateTypeRules.newRule(
                                typeResolver.resolve(Collection.class, WildcardType.class),
                                typeResolver.resolve(List.class, WildcardType.class))
                )
                .enableUrlTemplating(true)
                .forCodeGeneration(false);
    }


    @Bean
    UiConfiguration uiConfig() {
        return new UiConfiguration(
                null,           // url
                "list",       // docExpansion          => none | list
                "alpha",      // apiSorter             => alpha
                "schema",     // defaultModelRendering => schema
                UiConfiguration.Constants.DEFAULT_SUBMIT_METHODS,
                true,        // enableJsonEditor      => true | false
                true,
                null);
    }

}

