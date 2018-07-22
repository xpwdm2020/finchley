package com.github.dqqzj.account.model.response;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import com.google.common.collect.ImmutableList;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import java.util.Collection;

/**
 * @author qinzhongjian
 * @date created in 2018/6/26 16:53
 * @since 1.0.0
 */
@Data
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
@JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy.class)
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class ObjectCollectionResponse<T> extends RestfulResponse {
    private static final long serialVersionUID = 1862906172390850647L;

    private Collection<T> dataSet;

    public ObjectCollectionResponse(Collection<T> dataSet) {
        this.dataSet = ImmutableList.copyOf(dataSet);
    }
}

