package com.fintech.orion.hermesagentservices.transmission.request.type;

import com.fintech.orion.common.exceptions.BodyServiceException;
import com.fintech.orion.common.exceptions.RequestException;
import com.fintech.orion.common.exceptions.RequestSubmitterException;
import com.fintech.orion.dto.request.GenericRequest;
import com.fintech.orion.hermesagentservices.transmission.request.basetype.RequestCreatorInterface;
import com.fintech.orion.hermesagentservices.transmission.request.body.BodyServiceInterface;
import com.fintech.orion.hermesagentservices.transmission.request.submit.RequestSubmitterInterface;
import com.fintech.orion.hermesagentservices.transmission.response.handler.ResponseHandlerInterface;
import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.JsonNode;
import com.mashape.unirest.request.BaseRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by TharinduMP on 10/13/2016.
 * JenID API Request Implementation
 */
@Component
@Scope("prototype")
public class JenID extends AbstractRequest implements RequestInterface {

    @Autowired
    private BodyServiceInterface jenIdBody;

    @Autowired
    private RequestCreatorInterface jenIdPostSyncRequest;

    @Autowired
    private RequestSubmitterInterface requestSubmitter;

    @Autowired
    private ResponseHandlerInterface jenIdResponseHandler;

    @Override
    public void process(GenericRequest genericRequest) throws RequestException {

        // initialize the resources and configurations
        super.process(genericRequest);

        Map<String,Object> extras = new HashMap<>();

        try {
            // create jen id body
            extras.put("body",jenIdBody.createJSONBody(processConfigurationMap, resourceList, null));

            // create request
            BaseRequest request = jenIdPostSyncRequest.createRequest(processConfigurationMap,resourceList,extras);

            // make jen id call
            HttpResponse<JsonNode> response = requestSubmitter.submitRequest(request);

            // handle response
            jenIdResponseHandler.handleResponse(response);

        } catch (RequestSubmitterException | BodyServiceException e) {
            throw new RequestException(e);
        }

        // status update : call response handler

        // license update : will be done by the response handler


    }
}
