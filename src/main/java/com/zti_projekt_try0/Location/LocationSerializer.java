package com.zti_projekt_try0.Location;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;

import java.io.IOException;

@JsonSerialize(using = LocationSerializer.class)
public class LocationSerializer extends StdSerializer<Location> {

    public LocationSerializer() {
        this(null);
    }

    public LocationSerializer(Class<Location> t) {
        super(t);
    }

    @Override
    public void serialize(Location location, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
        jsonGenerator.writeStartObject();
        jsonGenerator.writeNumberField("location_id", location.getLocationId());
        jsonGenerator.writeBooleanField("is_active", location.getIsActive());
        jsonGenerator.writeStringField("street_address", location.getStreetAddress());
        jsonGenerator.writeStringField("city", location.getCity());
        jsonGenerator.writeStringField("zipcode", location.getZipcode());
        jsonGenerator.writeStringField("state", location.getState());
        jsonGenerator.writeStringField("country_code", location.getCountryCode());
        jsonGenerator.writeStringField("activity_name", location.getActivityName());
        jsonGenerator.writeStringField("company_name", location.getCompanyName());
        jsonGenerator.writeEndObject();
    }
}