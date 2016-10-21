package com.fintech.orion.hermesagentservices.transmission.response.mapper;

import java.io.IOException;

/**
 * Created by TharinduMP on 10/20/2016.
 * GenericMapperInterface
 */
@FunctionalInterface
public interface GenericMapperInterface {
    <T> T createMappedJsonObject(String textToMap, Class<T> mapperClass) throws IOException;
}
