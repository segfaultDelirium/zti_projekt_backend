package com.zti_projekt_try0.CountryCode;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

import java.io.IOException;

public class CountryCodeSerializer extends JsonSerializer<CountryCode> {

    @Override
    public void serialize(CountryCode countryCode, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
        jsonGenerator.writeStartObject();
        jsonGenerator.writeNumberField("countryCodeId", countryCode.getCountryCodeId());
        jsonGenerator.writeBooleanField("isActive", countryCode.isActive());
        jsonGenerator.writeStringField("countryCode", countryCode.getCountryCode());
        jsonGenerator.writeEndObject();
    }
}
