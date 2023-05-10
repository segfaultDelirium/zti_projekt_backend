package com.zti_projekt_try0.Activity;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

import java.io.IOException;

public class ActivitySerializer extends JsonSerializer<Activity> {

    @Override
    public void serialize(Activity activity, JsonGenerator generator, SerializerProvider provider) throws IOException {
        generator.writeStartObject();
        generator.writeNumberField("activityId", activity.getActivityId());
        generator.writeBooleanField("isActive", activity.isActive());
        generator.writeStringField("activityName", activity.getActivityName());
        generator.writeEndObject();
    }
}