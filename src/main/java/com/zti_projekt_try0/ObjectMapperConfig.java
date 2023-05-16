package com.zti_projekt_try0;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.module.SimpleModule;
import com.zti_projekt_try0.Activity.Activity;
import com.zti_projekt_try0.Activity.ActivitySerializer;
import com.zti_projekt_try0.CountryCode.CountryCode;
import com.zti_projekt_try0.CountryCode.CountryCodeSerializer;
import com.zti_projekt_try0.Location.Location;
import com.zti_projekt_try0.Location.LocationSerializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

@Configuration
@EnableAspectJAutoProxy
public class ObjectMapperConfig {

    @Bean
    public ObjectMapper objectMapper() {
        ObjectMapper mapper = new ObjectMapper();
        SimpleModule module = new SimpleModule();
        module.addSerializer(Location.class, new LocationSerializer(new ActivitySerializer(), new CountryCodeSerializer()));
        module.addSerializer(CountryCode.class, new CountryCodeSerializer());
        module.addSerializer(Activity.class, new ActivitySerializer());
        mapper.registerModule(module);
        return mapper;
    }
}
