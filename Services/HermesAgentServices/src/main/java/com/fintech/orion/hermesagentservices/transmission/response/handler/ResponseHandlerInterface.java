package com.fintech.orion.hermesagentservices.transmission.response.handler;

import com.fintech.orion.common.exceptions.response.ResponseHandlerException;
import com.fintech.orion.dto.process.ProcessDTO;
import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.JsonNode;

/**
 * Created by TharinduMP on 10/21/2016.
 * ResponseHandlerInterface
 */
@FunctionalInterface
public interface ResponseHandlerInterface {
    ProcessDTO handleResponse(HttpResponse<String> response, ProcessDTO processDTO) throws ResponseHandlerException;
}
