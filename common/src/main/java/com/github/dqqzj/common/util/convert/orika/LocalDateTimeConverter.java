package com.github.dqqzj.common.util.convert.orika;

import ma.glasnost.orika.MappingContext;
import ma.glasnost.orika.converter.BidirectionalConverter;
import ma.glasnost.orika.metadata.Type;

import java.time.LocalDateTime;

/**
 * @author qinzhongjian
 * @date created in 2018/6/25 23:32
 * @since 1.0.0
 */
public class LocalDateTimeConverter extends BidirectionalConverter<LocalDateTime, LocalDateTime> {
    @Override
    public LocalDateTime convertTo(LocalDateTime localDateTime, Type<LocalDateTime> type, MappingContext mappingContext) {
        return LocalDateTime.from(localDateTime);
    }

    @Override
    public LocalDateTime convertFrom(LocalDateTime localDateTime, Type<LocalDateTime> type, MappingContext mappingContext) {
        return LocalDateTime.from(localDateTime);
    }
}

