package com.fintech.orion.hermesagentservices.transmission.response.mapper;

import com.fasterxml.jackson.core.JsonProcessingException;

import java.io.IOException;

/**
 * Created by TharinduMP on 10/20/2016.
 * GenericMapperInterface
 */
public interface GenericMapperInterface {
    <T> T createMappedJsonObject(String textToMap, Class<T> mapperClass) throws IOException;

    String createJSONStringFromObject(Object o) throws JsonProcessingException;
}
