package com.liziczh.erp.dto;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

import java.io.IOException;
import java.text.DecimalFormat;

public class PriceDoubleSerializer extends JsonSerializer<Double> {
    private DecimalFormat df = new DecimalFormat("0.00");
    @Override
    public void serialize(Double aDouble, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
        if(aDouble != null) {
            jsonGenerator.writeString(df.format(aDouble));
        }
    }
}
