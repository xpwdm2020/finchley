package com.github.dqqzj.account.model.response;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 * @author qinzhongjian
 * @date created in 2018/6/25 22:45
 * @since 1.0.0
 */
@Data
@NoArgsConstructor
@ToString(callSuper = true)
@EqualsAndHashCode
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class RestfulResponse implements Response {

    private static final long serialVersionUID = -7443304902819898146L;

    public static final int DEFAULT_OK = 20000;

    /**
     * [M] 平台状态码
     */
    @JsonProperty("code")
    @ApiModelProperty(value = "平台状态码", example = "20000", required = true)
    private int code = DEFAULT_OK;
}
