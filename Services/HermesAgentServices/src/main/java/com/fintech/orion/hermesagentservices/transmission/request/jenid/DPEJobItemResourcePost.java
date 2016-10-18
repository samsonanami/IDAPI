package com.fintech.orion.hermesagentservices.transmission.request.jenid;

import com.fintech.orion.common.exceptions.RequestSubmitterException;
import com.fintech.orion.hermesagentservices.transmission.request.basetype.RequestSubmitterInterface;
import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.JsonNode;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;
import org.apache.http.annotation.ThreadSafe;

/**
 * Created by TharinduMP on 10/17/2016.
 */
@ThreadSafe
public class DPEJobItemResourcePost implements RequestSubmitterInterface {

    public void submitRequest() throws RequestSubmitterException {
        try {
            HttpResponse<JsonNode> jsonNodeHttpResponse = Unirest.post("")
                    .header("Content-Type", "application/json")
                    .header("Authorization", "Basic dXNlcjpwYXNzd29yZA==")
                    .queryString("synchronous", "false")
                    .asJson();
        } catch (UnirestException e) {
            throw new RequestSubmitterException(e);
        }
    }
}
