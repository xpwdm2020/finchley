package com.github.dqqzj.common.util.convert.orika;

import ma.glasnost.orika.MappingContext;
import ma.glasnost.orika.converter.BidirectionalConverter;
import ma.glasnost.orika.metadata.Type;

import java.time.LocalTime;

/**
 * @author qinzhongjian
 * @date created in 2018/6/25 23:33
 * @since 1.0.0
 */
public class LocalTimeConverter extends BidirectionalConverter<LocalTime, LocalTime> {
    @Override
    public LocalTime convertTo(LocalTime localTime, Type<LocalTime> type, MappingContext mappingContext) {
        return LocalTime.from(localTime);
    }

    @Override
    public LocalTime convertFrom(LocalTime localTime, Type<LocalTime> type, MappingContext mappingContext) {
        return LocalTime.from(localTime);
    }
}

