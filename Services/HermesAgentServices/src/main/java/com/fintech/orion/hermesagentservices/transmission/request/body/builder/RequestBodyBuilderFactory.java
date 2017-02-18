package com.fintech.orion.hermesagentservices.transmission.request.body.builder;

import org.springframework.util.Assert;

/**
 * Created by sasitha on 12/19/16.
 *
 */
public class RequestBodyBuilderFactory {

    public RequestBodyBuilder getRequestBodyBuilder(RequestBodyType type){
        Assert.notNull(type, "Request body type cannot be null");
        RequestBodyBuilder builder = new OracleRequestBodyBuilder();
        if (type.equals(RequestBodyType.ORACLE)){
            builder = new OracleRequestBodyBuilder();
        }else if (type.equals(RequestBodyType.COMPRESSION)){
            builder = new CompressionLabsRequestBodyBuilder();
        }
        return builder;
    }
}
