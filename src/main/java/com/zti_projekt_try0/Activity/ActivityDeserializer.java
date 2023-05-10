package com.zti_projekt_try0.Activity;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonNode;

import java.io.IOException;

public class ActivityDeserializer extends JsonDeserializer<Activity> {

    @Override
    public Activity deserialize(JsonParser parser, DeserializationContext context) throws IOException {
        JsonNode node = parser.getCodec().readTree(parser);

        int activityId = node.get("activityId").asInt();
        boolean isActive = node.get("isActive").asBoolean();
        String activityName = node.get("activityName").asText();

        return new Activity(activityId, isActive, activityName);
    }
}
