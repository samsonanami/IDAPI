package com.fintech.orion.hermesagentservices.transmission.request.body.builder;

import com.fintech.orion.hermesagentservices.transmission.request.builder.RequestBuilder;

import java.util.Map;

/**
 * Created by sasitha on 12/19/16.
 *
 */
public class OracleRequestBodyBuilder implements RequestBodyBuilder{

    @Override
    public String getRequestBody(Map<String, Object> bodyContent) {

        return "{}";
    }
}
