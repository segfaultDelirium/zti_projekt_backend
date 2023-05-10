package com.zti_projekt_try0.Location;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonNode;
import com.zti_projekt_try0.Activity.Activity;
import com.zti_projekt_try0.CountryCode.CountryCode;

import java.io.IOException;

public class LocationDeserializer extends JsonDeserializer<Location> {

    @Override
    public Location deserialize(JsonParser parser, DeserializationContext context) throws IOException {
        JsonNode node = parser.getCodec().readTree(parser);

        int locationId = node.get("locationId").asInt();
        boolean isActive = node.get("isActive").asBoolean();
        String streetAddress = node.get("streetAddress").asText();
        String city = node.get("city").asText();
        String zipcode = node.get("zipcode").asText();
        String state = node.get("state").asText();
        String companyName = node.get("companyName").asText();
        CountryCode countryCode = new CountryCode(node.get("countryCode").asInt(), node.get("countryCode").asBoolean(), node.get("countryCode").asText());
        Activity activity = new Activity(node.get("activity").get("activityId").asInt(), node.get("activity").get("isActive").asBoolean(), node.get("activity").get("activityName").asText());

        return new Location(locationId, isActive, streetAddress, city, zipcode, state, companyName, countryCode, activity);
    }
}
