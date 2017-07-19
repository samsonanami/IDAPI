package com.fintech.orion.hermesagentservices.transmission.request.builder;

import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.request.BaseRequest;

import java.io.File;
import java.util.Map;

/**
 * Created by sasitha on 2/18/17.
 *
 */
public class CompressionlabsRequestBuilder extends RequestBuilder{

    @Override
    public BaseRequest buildPostRequest(Map<String, String> configurations, Map content) {


        return Unirest.post(configurations.get("url"))
                .field("f1", new File((String) content.get("f1")))
                .field("f2", new File((String) content.get("f2")))
                .field("f3", new File((String) content.get("f3")));

    }
}
