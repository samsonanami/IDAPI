package com.fintech.orion.hermesagentservices.transmission.request.submit;

import com.fintech.orion.common.exceptions.request.FailedRequestException;
import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.request.BaseRequest;

/**
 * Created by TharinduMP on 10/21/2016.
 * RequestSubmitterInterface
 */
@FunctionalInterface
public interface RequestSubmitterInterface {
    HttpResponse<String> submitRequest(BaseRequest baseRequest) throws FailedRequestException;
}
