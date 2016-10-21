package com.fintech.orion.hermesagentservices.transmission.response.handler;

import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.JsonNode;

/**
 * Created by TharinduMP on 10/21/2016.
 */
public interface ResponseHandlerInterface {
    void handleResponse(HttpResponse<JsonNode> response);
}
