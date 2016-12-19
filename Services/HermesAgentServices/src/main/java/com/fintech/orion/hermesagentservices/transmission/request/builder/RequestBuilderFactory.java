package com.fintech.orion.hermesagentservices.transmission.request.builder;

import org.springframework.stereotype.Component;
import org.springframework.util.Assert;

/**
 * Created by sasitha on 12/19/16.
 *
 */
@Component
public class RequestBuilderFactory {

    public RequestBuilder getRequestBuilder(BuilderType builderType){
        Assert.notNull(builderType, "Builder type cannot be null");
        RequestBuilder requestBuilder = null;
        switch (builderType){
            case ORACLE:
                requestBuilder = new OracleRequestBuilder();
                break;
        }
        return requestBuilder;
    }
}
