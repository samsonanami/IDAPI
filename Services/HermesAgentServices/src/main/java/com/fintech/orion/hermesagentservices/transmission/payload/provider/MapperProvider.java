package com.fintech.orion.hermesagentservices.transmission.payload.provider;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;

/**
 * Created by TharinduMP on 10/26/2016.
 * Pvode new instances of ObjectMapper to create different configurations on threads' safely.
 */
public class MapperProvider implements MapperProviderInterface {

    @Autowired
    private ApplicationContext context;

    @Override
    public ObjectMapper providerNewMapperInstance() {
        return (ObjectMapper) context.getBean("mapper");
    }

}
