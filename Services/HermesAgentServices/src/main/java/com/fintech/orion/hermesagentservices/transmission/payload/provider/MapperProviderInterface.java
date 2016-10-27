package com.fintech.orion.hermesagentservices.transmission.payload.provider;

import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * Created by TharinduMP on 10/26/2016.
 */
public interface MapperProviderInterface {
    ObjectMapper providerNewMapperInstance();
}
