package com.zti_projekt_try0.CountryCode;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

import java.io.IOException;

public class CountryCodeSerializer extends JsonSerializer<CountryCode> {

    @Override
    public void serialize(CountryCode countryCode, JsonGenerator generator, SerializerProvider provider) throws IOException {
        generator.writeStartObject();
        generator.writeNumberField("countryCodeId", countryCode.getCountryCodeId());
        generator.writeBooleanField("isActive", countryCode.isActive());
        generator.writeStringField("countryCode", countryCode.getCountryCode());
        generator.writeEndObject();
    }
}

