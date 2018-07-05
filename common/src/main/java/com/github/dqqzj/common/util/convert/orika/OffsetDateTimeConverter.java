package com.github.dqqzj.common.util.convert.orika;

import ma.glasnost.orika.MappingContext;
import ma.glasnost.orika.converter.BidirectionalConverter;
import ma.glasnost.orika.metadata.Type;

import java.time.OffsetDateTime;

/**
 * @author qinzhongjian
 * @date created in 2018/6/25 23:33
 * @since 1.0.0
 */
public class OffsetDateTimeConverter extends BidirectionalConverter<OffsetDateTime, OffsetDateTime> {
    @Override
    public OffsetDateTime convertTo(OffsetDateTime zdt, Type<OffsetDateTime> type, MappingContext mappingContext) {
        return OffsetDateTime.from(zdt);
    }

    @Override
    public OffsetDateTime convertFrom(OffsetDateTime zdt, Type<OffsetDateTime> type, MappingContext mappingContext) {
        return OffsetDateTime.from(zdt);
    }
}

