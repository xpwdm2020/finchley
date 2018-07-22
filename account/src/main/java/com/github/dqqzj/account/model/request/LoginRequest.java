package com.github.dqqzj.account.model.request;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

/**
 * @author qinzhongjian
 * @date created in 2018/6/26 17:11
 * @since 1.0.0
 */
@Data
@NoArgsConstructor
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class LoginRequest extends RestfulRequest {

    private static final long serialVersionUID = -3286520204644035655L;

    @NotNull
    @NotBlank
    @Pattern(regexp = "^\\d{11}$", message = "请输入11位手机号")
    @JsonProperty("mobile")
    @ApiModelProperty(value = "手机号", example = "13146701494", required = true)
    private String mobile;

    @NotNull
    @Size(min = 6, max = 20, message = "请输入6~20位的密码")
    @JsonProperty("password")
    @ApiModelProperty(value = "登录与支付密码", example = "123123123", required = true)
    private String password;

}

