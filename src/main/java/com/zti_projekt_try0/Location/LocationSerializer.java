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

        if (location.getLocationId() != null) {
            generator.writeNumberField("locationId", location.getLocationId());
        }else{
            generator.writeNullField("locationId");
        }

        if (location.isActive() != null) {
            generator.writeBooleanField("isActive", location.isActive());
        } else {
            generator.writeNullField("isActive");
        }

        if (location.getStreetAddress() != null) {
            generator.writeStringField("streetAddress", location.getStreetAddress());
        } else {
            generator.writeNullField("streetAddress");
        }

        if (location.getCity() != null) {
            generator.writeStringField("city", location.getCity());
        } else {
            generator.writeNullField("city");
        }

        if (location.getZipcode() != null) {
            generator.writeStringField("zipcode", location.getZipcode());
        } else {
            generator.writeNullField("zipcode");
        }

        if (location.getState() != null) {
            generator.writeStringField("state", location.getState());
        } else {
            generator.writeNullField("state");
        }

        if (location.getCompanyName() != null) {
            generator.writeStringField("companyName", location.getCompanyName());
        } else {
            generator.writeNullField("companyName");
        }

        generator.writeFieldName("activity");
        activitySerializer.serialize(location.getActivity(), generator, provider);

        generator.writeFieldName("countryCode");
        countryCodeJsonSerializer.serialize(location.getCountryCode(), generator, provider);

        if (location.getTimestamp() != null) {
            generator.writeStringField("timestamp", location.getTimestamp().toString());
        } else {
            generator.writeNullField("timestamp");
        }

        generator.writeEndObject();
    }
}