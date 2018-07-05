package com.github.dqqzj.common.util.convert.orika;

import ma.glasnost.orika.MappingContext;
import ma.glasnost.orika.converter.BidirectionalConverter;
import ma.glasnost.orika.metadata.Type;

import java.time.ZonedDateTime;

/**
 * @author qinzhongjian
 * @date created in 2018/6/25 23:34
 * @since 1.0.0
 */
public class ZonedDateTimeConverter extends BidirectionalConverter<ZonedDateTime, ZonedDateTime> {
    @Override
    public ZonedDateTime convertTo(ZonedDateTime odt, Type<ZonedDateTime> type, MappingContext mappingContext) {
        return ZonedDateTime.from(odt);
    }

    @Override
    public ZonedDateTime convertFrom(ZonedDateTime odt, Type<ZonedDateTime> type, MappingContext mappingContext) {
        return ZonedDateTime.from(odt);
    }
}

