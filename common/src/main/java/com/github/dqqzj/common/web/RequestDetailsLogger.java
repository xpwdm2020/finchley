package com.github.dqqzj.common.web;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.google.common.collect.ImmutableMap;
import com.github.dqqzj.common.config.RequestAttributeConst;
import com.github.dqqzj.common.util.Jacksons;
import com.github.dqqzj.common.util.convert.jackson.OffsetDateTimeToIso8601Serializer;
import com.github.dqqzj.common.util.convert.jackson.StringToMapSerializer;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.servlet.http.HttpServletRequest;
import java.time.OffsetDateTime;
import java.util.Enumeration;
import java.util.Map;

/**
 * @author qinzhongjian
 * @date created in 2018/6/26 10:05
 * @since 1.0.0
 */
@Data
@NoArgsConstructor
@EqualsAndHashCode
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class RequestDetailsLogger {

    @JsonIgnore
    private final HttpServletRequest request = ServletContextHolder.getRequest();

    @JsonProperty("requestId")
    private String requestId = ServletContextHolder.fetchRequestId();

    @JsonProperty("url")
    private String url = request.getRequestURL().toString();

    @JsonProperty("method")
    private String method = request.getMethod();

    @JsonProperty("paramsMap")
    private ImmutableMap<String, Object> paramsMap = fetParamsMap(request);

    @JsonProperty("headers")
    private ImmutableMap<String, Object> headers = fetchHttpHeaders(request);

    @JsonProperty("apiDesc")
    private String apiDesc;

    @JsonProperty("requestBody")
    @JsonSerialize(using = StringToMapSerializer.class)
    private String requestBody = (String) request.getAttribute(RequestAttributeConst.REQUEST_BODY_KEY);

    @JsonProperty("requestTime")
    @JsonSerialize(using = OffsetDateTimeToIso8601Serializer.class)
    private OffsetDateTime requestTime = OffsetDateTime.now();

    @JsonProperty("responseTime")
    @JsonSerialize(using = OffsetDateTimeToIso8601Serializer.class)
    private OffsetDateTime responseTime;

    @JsonProperty("characterEncoding")
    private String characterEncoding = request.getCharacterEncoding();

    @JsonProperty("contentLength")
    private long contentLength = request.getContentLengthLong();

    @JsonProperty("remoteHost")
    private String remoteHost = request.getRemoteHost();

    @JsonProperty("remotePort")
    private int remotePort = request.getRemotePort();

    private ImmutableMap<String, Object> fetParamsMap(HttpServletRequest request) {
        final Map<String, String[]> parameterMap = request.getParameterMap();
        final ImmutableMap.Builder<String, Object> singleValueParams = ImmutableMap.builder();
        for (Map.Entry<String, String[]> entry : parameterMap.entrySet()) {
            singleValueParams.put(entry.getKey(), entry.getValue()[0]);
        }
        return singleValueParams.build();
    }

    private ImmutableMap<String, Object> fetchHttpHeaders(HttpServletRequest request) {
        final ImmutableMap.Builder<String, Object> headerBuilder = ImmutableMap.builder();
        final Enumeration<String> headerNames = request.getHeaderNames();
        while (headerNames.hasMoreElements()) {
            final String headerName = headerNames.nextElement();
            headerBuilder.put(headerName, request.getHeader(headerName));
        }
        return headerBuilder.build();
    }

    @Override
    public String toString() {
        return Jacksons.parseInPrettyMode(this);
    }
}
