package com.fintech.orion.hermesagentservices.transmission.request.submit;

import com.fintech.orion.common.exceptions.RequestException;
import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.JsonNode;
import com.mashape.unirest.request.BaseRequest;

/**
 * Created by TharinduMP on 10/21/2016.
 * RequestSubmitterInterface
 */
public interface RequestSubmitterInterface {
    HttpResponse<JsonNode> submitRequest(BaseRequest baseRequest) throws RequestException;
}
