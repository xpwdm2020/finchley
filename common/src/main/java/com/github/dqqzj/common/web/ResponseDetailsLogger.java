package com.github.dqqzj.common.web;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.google.common.collect.ImmutableMap;
import com.github.dqqzj.common.util.Jacksons;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.servlet.http.HttpServletResponse;

/**
 * @author qinzhongjian
 * @date created in 2018/6/26 10:34
 * @since 1.0.0
 */
@Data
@NoArgsConstructor
@EqualsAndHashCode
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class ResponseDetailsLogger {

    @JsonIgnore
    private final HttpServletResponse response = ServletContextHolder.getResponse();

    @JsonProperty("headers")
    private ImmutableMap<String, Object> headers = fetchHttpHeaders(response);

    @JsonProperty("responseBody")
    private Object responseBody;

    private ResponseDetailsLogger(Object responseBody) {
        this.responseBody = responseBody;
    }

    public static ResponseDetailsLogger with(Object responseBody) {
        return new ResponseDetailsLogger(responseBody);
    }

    private ImmutableMap<String, Object> fetchHttpHeaders(HttpServletResponse response) {
        final ImmutableMap.Builder<String, Object> headerBuilder = ImmutableMap.builder();
        for (String headerName : response.getHeaderNames()) {
            headerBuilder.put(headerName, response.getHeader(headerName));
        }
        return headerBuilder.build();
    }

    @Override
    public String toString() {
        return Jacksons.parseInPrettyMode(this);
    }
}

