package com.github.dqqzj.common.model.swagger;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.google.common.collect.ImmutableList;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.ResponseMessage;

import java.util.List;

/**
 * @author qinzhongjian
 * @date created in 2018/6/26 11:54
 * @since 1.0.0
 */
@Data
@Builder
@ToString(callSuper = true)
@EqualsAndHashCode
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class SwaggerApiInfo {

    private ApiInfo apiInfo;

    private List<ResponseMessage> responseMessages;

    public SwaggerApiInfo(ApiInfo apiInfo,List<ResponseMessage> responseMessages){
        this.apiInfo = apiInfo;
        this.responseMessages = responseMessages;
    }


}

