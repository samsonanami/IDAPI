package com.fintech.orion.hermesagentservices.transmission.request.builder;

import com.mashape.unirest.request.BaseRequest;

import java.util.Map;

/**
 * Created by sasitha on 12/19/16.
 *
 */
public interface RequestBuilder {

    BaseRequest buildRequest(Map<String,String> configurations, Map content);
}
