package com.github.dqqzj.common.util.convert.orika;

import ma.glasnost.orika.MappingContext;
import ma.glasnost.orika.converter.BidirectionalConverter;
import ma.glasnost.orika.metadata.Type;

import java.time.Instant;

/**
 * @author qinzhongjian
 * @date created in 2018/6/25 23:21
 * @since 1.0.0
 */
public class InstantConverter extends BidirectionalConverter<Instant, Instant> {
    @Override
    public Instant convertTo(Instant instant, Type<Instant> type, MappingContext mappingContext) {
        return Instant.from(instant);
    }

    @Override
    public Instant convertFrom(Instant instant, Type<Instant> type, MappingContext mappingContext) {
        return Instant.from(instant);
    }
}
