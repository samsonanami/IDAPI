package com.fintech.orion.hermesagentservices.transmission.request.builder;

import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.request.BaseRequest;
import org.springframework.util.Assert;

import java.util.Map;

/**
 * Created by sasitha on 12/19/16.
 *
 */
public class OracleRequestBuilder implements RequestBuilder {

    @Override
    public BaseRequest buildPostRequest(Map<String, String> configurations, Map content) {
        Assert.notNull(configurations, "Configurations cannot be null");
        String body = (String)content.get("body");

        return Unirest.post(configurations.get("url"))
                .header("Content-Type", configurations.get("header.contentType"))
                .body(body);
    }

    @Override
    public BaseRequest buildGetRequest(Map<String, String> configurations, Map content) {
        Assert.notNull(configurations, "Configurations cannot be null");
        return Unirest.get(configurations.get("url"));
    }
}
