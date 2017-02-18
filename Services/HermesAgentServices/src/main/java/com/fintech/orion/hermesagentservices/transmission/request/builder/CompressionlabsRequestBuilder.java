package com.fintech.orion.hermesagentservices.transmission.request.builder;

import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.request.BaseRequest;

import java.util.Map;

/**
 * Created by sasitha on 2/18/17.
 *
 */
public class CompressionlabsRequestBuilder extends RequestBuilder{

    @Override
    public BaseRequest buildPostRequest(Map<String, String> configurations, Map content) {
        String body = (String)content.get("body");

        return Unirest.post(configurations.get("url"))
                .header("Content-Type", "multipart/form-data; boundary=----WebKitFormBoundary7MA4YWxkTrZu0gW")
                .header("cache-control", "no-cache")
                .body(body);
    }
}
