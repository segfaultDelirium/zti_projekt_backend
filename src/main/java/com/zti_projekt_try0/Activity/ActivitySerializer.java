package com.zti_projekt_try0.Activity;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class ActivitySerializer {

    private static final ObjectMapper mapper = new ObjectMapper();

    public static String serialize(Activity activity) throws JsonProcessingException {
        return mapper.writeValueAsString(activity);
    }
}
