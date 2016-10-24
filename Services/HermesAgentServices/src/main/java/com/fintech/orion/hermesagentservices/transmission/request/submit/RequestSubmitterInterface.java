package com.fintech.orion.hermesagentservices.transmission.request.submit;

import com.fintech.orion.common.exceptions.FailedRequestException;
import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.JsonNode;
import com.mashape.unirest.request.BaseRequest;

/**
 * Created by TharinduMP on 10/21/2016.
 * RequestSubmitterInterface
 */
@FunctionalInterface
public interface RequestSubmitterInterface {
    HttpResponse<JsonNode> submitRequest(BaseRequest baseRequest) throws FailedRequestException;
}
