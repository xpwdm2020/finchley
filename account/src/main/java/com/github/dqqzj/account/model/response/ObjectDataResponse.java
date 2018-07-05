package com.github.dqqzj.account.model.response;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.github.dqqzj.common.model.response.RestfulResponse;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

/**
 * @author qinzhongjian
 * @date created in 2018/6/26 16:19
 * @since 1.0.0
 */
@Data
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class ObjectDataResponse<T> extends RestfulResponse {

    private static final long serialVersionUID = 1862906172390850647L;

    private T data;

    public ObjectDataResponse(T data) {
        this.data = data;
    }
}
