package com.fintech.orion.hermesagentservices.transmission.request.submit;

import com.fintech.orion.common.exceptions.request.FailedRequestException;
import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.JsonNode;
import com.mashape.unirest.http.exceptions.UnirestException;
import com.mashape.unirest.request.BaseRequest;
import org.slf4j.Logger;

/**
 * Created by TharinduMP on 10/19/2016.
 * Common Request Submitter.
 * Responsible for making the request.
 */
public class RequestSubmitter implements RequestSubmitterInterface {

    static final Logger LOGGER = org.slf4j.LoggerFactory.getLogger(RequestSubmitter.class);

    @Override
    public HttpResponse<JsonNode> submitRequest(BaseRequest baseRequest) throws FailedRequestException {
        try {
            return baseRequest.asJson();
        }
        catch (UnirestException e) {
            LOGGER.error("Failed Request : {}", baseRequest.getHttpRequest().toString());
            throw new FailedRequestException("Request Submission Failed.", e);
        }
    }
}
