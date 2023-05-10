package com.zti_projekt_try0.CountryCode;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonNode;

import java.io.IOException;

public class CountryCodeDeserializer extends JsonDeserializer<CountryCode> {

    @Override
    public CountryCode deserialize(JsonParser parser, DeserializationContext context) throws IOException {
        JsonNode node = parser.getCodec().readTree(parser);

        int countryCodeId = node.get("countryCodeId").asInt();
        boolean isActive = node.get("isActive").asBoolean();
        String countryCode = node.get("countryCode").asText();

        return new CountryCode(countryCodeId, isActive, countryCode);
    }
}

