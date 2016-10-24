package com.fintech.orion.hermesagentservices.transmission.response.mapper;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.http.annotation.ThreadSafe;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.IOException;

/**
 * Created by TharinduMP on 10/20/2016.
 * Class Responsible for converting string jSON to provided model objects.
 * ThreadSafe reference :http://wiki.fasterxml.com/JacksonFAQThreadSafety
 */
@ThreadSafe
public class GenericMapper implements GenericMapperInterface {

    @Autowired
    ObjectMapper mapper;

    @Override
    public <T> T createMappedJsonObject(String textToMap, Class<T> mapperClass) throws IOException {
        return mapper.readValue(textToMap,mapperClass);
    }

    @Override
    public String createJSONStringFromObject(Object o) throws JsonProcessingException {
        return mapper.writeValueAsString(o);
    }
}
