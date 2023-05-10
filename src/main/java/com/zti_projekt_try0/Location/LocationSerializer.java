package com.zti_projekt_try0.Location;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.module.SimpleModule;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;

import java.io.IOException;

public class LocationSerializer extends StdSerializer<Location> {

    public LocationSerializer() {
        super(Location.class);
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
        generator.writeStringField("countryCode", location.getCountryCode().toString());
        generator.writeStringField("activity", location.getActivity().toString());
        generator.writeEndObject();
    }

    public static void registerModule(ObjectMapper mapper) {
        SimpleModule module = new SimpleModule();
        module.addSerializer(Location.class, new LocationSerializer());
        mapper.registerModule(module);
    }

}