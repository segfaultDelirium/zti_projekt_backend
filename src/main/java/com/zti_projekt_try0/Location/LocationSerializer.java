package com.zti_projekt_try0.Location;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.zti_projekt_try0.Activity.Activity;
import com.zti_projekt_try0.CountryCode.CountryCode;

import java.io.IOException;

public class LocationSerializer extends JsonSerializer<Location> {

    private final JsonSerializer<Activity> activitySerializer;
    private final JsonSerializer<CountryCode> countryCodeJsonSerializer;

    public LocationSerializer(JsonSerializer<Activity> activitySerializer, JsonSerializer<CountryCode> countryCodeJsonSerializer) {
        this.activitySerializer = activitySerializer;
        this.countryCodeJsonSerializer = countryCodeJsonSerializer;
    }

    @Override
    public void serialize(Location location, JsonGenerator generator, SerializerProvider provider) throws IOException {
        generator.writeStartObject();
        generator.writeNumberField("locationId", location.getLocationId());
        generator.writeBooleanField("isActive", location.isActive());
        generator.writeStringField("streetAddress", location.getStreetAddress());
        generator.writeStringField("city", location.getCity());
        generator.writeStringField("zipcode", location.getZipcode());
        generator.writeStringField("state", location.getState());
        generator.writeStringField("companyName", location.getCompanyName());
        generator.writeFieldName("activity");
        activitySerializer.serialize(location.getActivity(), generator, provider);
        generator.writeFieldName("countryCode");
        countryCodeJsonSerializer.serialize(location.getCountryCode(), generator, provider);
        generator.writeEndObject();
    }
}