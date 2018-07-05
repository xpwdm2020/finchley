package com.github.dqqzj.common.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.github.dqqzj.common.util.convert.jackson.OffsetDateTimeToIso8601Serializer;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.io.Serializable;
import java.time.OffsetDateTime;
import java.time.ZoneOffset;

/**
 * @author qinzhongjian
 * @date created in 2018/6/25 22:30
 * @since 1.0.0
 */
@Data
@NoArgsConstructor
@ToString(callSuper = true)
@EqualsAndHashCode
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class IdentityDomain implements Serializable {

    private static final long serialVersionUID = 2806569656265256243L;

    @JsonSerialize(using = OffsetDateTimeToIso8601Serializer.class)
    public static final OffsetDateTime DEFAULT_DATE_TIME = OffsetDateTime.of(1970, 1, 1, 0, 0, 0, 0, ZoneOffset.ofHours(8));

    @JsonSerialize(using = OffsetDateTimeToIso8601Serializer.class)
    private OffsetDateTime createTime;

    @JsonSerialize(using = OffsetDateTimeToIso8601Serializer.class)
    private OffsetDateTime updateTime;

    @JsonSerialize(using = OffsetDateTimeToIso8601Serializer.class)
    private OffsetDateTime deleteTime;

}

