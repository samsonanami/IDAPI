package com.fintech.orion.hermesagentservices.transmission.request.body.builder;

import org.springframework.util.Assert;

/**
 * Created by sasitha on 12/19/16.
 *
 */
public class RequestBodyBuilderFactory {

    public RequestBodyBuilder getRequestBodyBuilder(RequestBodyType type){
        Assert.notNull(type, "Request body type cannot be null");
        RequestBodyBuilder builder = null;
        switch (type){
            case ORACLE:
                builder = new OracleRequestBodyBuilder();
                break;
        }
        return builder;
    }
}
