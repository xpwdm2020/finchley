package com.github.dqqzj.common.util.convert.jackson;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

import java.io.IOException;
import java.time.OffsetDateTime;
import java.time.format.DateTimeFormatter;

/**
 * @author qinzhongjian
 * @date created in 2018/6/25 22:37
 * @since 1.0.0
 */
public class OffsetDateTimeToIso8601Serializer extends JsonSerializer<OffsetDateTime> {

    @Override
    public void serialize(OffsetDateTime dateTime, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
        jsonGenerator.writeString(dateTime.format(DateTimeFormatter.ISO_OFFSET_DATE_TIME));
    }

}
