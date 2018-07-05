package com.github.dqqzj.account.model.response;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.github.dqqzj.common.model.response.RestfulResponse;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 * @author qinzhongjian
 * @date created in 2018/6/26 16:59
 * @since 1.0.0
 */
@Data
@NoArgsConstructor
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class RegisterResponse extends RestfulResponse {
    private static final long serialVersionUID = 7883775815440213351L;

    @JsonProperty("mobile")
    @ApiModelProperty(value = "手机号", example = "13146701494", required = true)
    private String mobile;

    @JsonProperty("balance")
    @ApiModelProperty(value = "用户的初始化余额", example = "100000000", required = true)
    private Long balance;
}
