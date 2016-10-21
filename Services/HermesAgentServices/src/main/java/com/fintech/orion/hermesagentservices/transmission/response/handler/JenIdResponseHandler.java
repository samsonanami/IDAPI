package com.fintech.orion.hermesagentservices.transmission.response.handler;


import com.fintech.orion.hermesagentservices.transmission.payload.model.jenid.JenID;
import com.fintech.orion.hermesagentservices.transmission.payload.model.jenidresultstring.JenIDResultString;
import com.fintech.orion.hermesagentservices.transmission.response.mapper.GenericMapperInterface;
import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.JsonNode;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.IOException;


/**
 * Created by TharinduMP on 10/19/2016.
 * JenIdResponseHandler
 * This is the final point of a worker.
 * this needs to handle the response and the final outcome for the process request.
 */
public class JenIdResponseHandler implements ResponseHandlerInterface {

    static final Logger LOGGER = org.slf4j.LoggerFactory.getLogger(JenIdResponseHandler.class);

    @Autowired
    private GenericMapperInterface genericMapper;

    @Override
    public void handleResponse(HttpResponse<JsonNode> response) {

        try {

            if(response.getStatus() == 200) {

                //get response mapped to jen id model
                JenID jenID = genericMapper.createMappedJsonObject(response.getBody().toString(), JenID.class);
                JenIDResultString jenIDResultString = genericMapper.createMappedJsonObject(jenID.getOutputData().getResultString(), JenIDResultString.class);

                //convert to saving format


                //save response

            } else {
                // save the error into the process response?


                // log it on logger
                LOGGER.error("Response Code was not 200.");
                LOGGER.error("Response Code : {} . Response : {}", response.getStatus(), response.getBody());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
