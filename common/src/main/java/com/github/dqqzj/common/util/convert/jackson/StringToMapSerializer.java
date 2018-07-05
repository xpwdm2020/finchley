package com.github.dqqzj.common.util.convert.jackson;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.github.dqqzj.common.util.Jacksons;

import java.io.IOException;
import java.util.Map;

/**
 * @author qinzhongjian
 * @date created in 2018/6/25 23:19
 * @since 1.0.0
 */
public class StringToMapSerializer extends JsonSerializer<String> {

    @Override
    public void serialize(String value, JsonGenerator gen, SerializerProvider serializers) throws IOException {
        String val = value;
        if (value == null || value.isEmpty()) {
            val = "{}";
        }
        gen.writeObject(Jacksons.getMapper().readValue(val, Map.class));
    }

}
