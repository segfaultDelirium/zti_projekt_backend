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
    public Location deserialize(JsonParser jsonParser, DeserializationContext deserializationContext)
            throws IOException {
        JsonNode node = jsonParser.getCodec().readTree(jsonParser);
        Location location = new Location();

        location.setLocationId(node.get("locationId").asInt());

        if (node.hasNonNull("isActive")){
            location.setActive(node.get("isActive").asBoolean());
        }else{
            location.setActive(null);
        }

        if (node.hasNonNull("streetAddress")){
            location.setStreetAddress(node.get("streetAddress").asText());
        }

        if (node.hasNonNull("city")){
            location.setCity(node.get("city").asText());
        }

        if (node.hasNonNull("zipcode")){
            location.setZipcode(node.get("zipcode").asText());
        }

        if (node.hasNonNull("state")){
            location.setState(node.get("state").asText());
        }

        if (node.hasNonNull("companyName")){
            location.setCompanyName(node.get("companyName").asText());
        }

        if(node.hasNonNull("countryCode")){
            CountryCode countryCode = new CountryCode();
            JsonNode countryCodeNode = node.get("countryCode");
            if(countryCodeNode.hasNonNull("code")){
                countryCode.setCountryCode(countryCodeNode.get("code").asText());
            }
            if(countryCodeNode.hasNonNull("isActive")){
                countryCode.setIsActive(countryCodeNode.get("isActive").asBoolean());
            }
            if(countryCodeNode.hasNonNull("countryCodeId")){
                countryCode.setCountryCodeId(countryCodeNode.get("countryCodeId").asInt());
            }
            location.setCountryCode(countryCode);
        }

        if(node.hasNonNull("activity")){
            Activity activity = new Activity();
            JsonNode activityNode = node.get("activity");
            if(activityNode.hasNonNull("activityName")){
                activity.setActivityName(activityNode.get("activityName").asText());
            }
            if(activityNode.hasNonNull("isActive")){
                activity.setActive(activityNode.get("isActive").asBoolean());
            }
            if(activityNode.hasNonNull("activityId")){
                activity.setActivityId(activityNode.get("activityId").asInt());
            }
            location.setActivity(activity);
        }

        return location;
    }
}
