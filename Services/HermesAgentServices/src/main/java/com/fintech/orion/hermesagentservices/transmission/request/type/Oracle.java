package com.fintech.orion.hermesagentservices.transmission.request.type;

import com.fintech.orion.common.exceptions.request.FailedRequestException;
import com.fintech.orion.common.exceptions.request.RequestException;
import com.fintech.orion.dto.request.GenericRequest;
import com.fintech.orion.hermesagentservices.transmission.request.builder.BuilderType;
import com.fintech.orion.hermesagentservices.transmission.request.builder.RequestBuilder;
import com.fintech.orion.hermesagentservices.transmission.request.builder.RequestBuilderFactory;
import com.fintech.orion.hermesagentservices.transmission.request.submit.RequestSubmitterInterface;
import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.request.BaseRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by sasitha on 12/19/16.
 *
 */

public class Oracle extends AbstractRequest implements RequestInterface{

    private static final Logger LOGGER = LoggerFactory.getLogger(Oracle.class);


    @Autowired
    private RequestSubmitterInterface requestSubmitter;

    @Override
    public void process(GenericRequest genericRequest) throws RequestException {
        super.process(genericRequest);

        Map<String, Object> content = new HashMap<>();
        Map<String, String> processConfigurationMap = new HashMap<>();

        processConfigurationMap.put("url", "http://10.101.15.212:8080/oracle/v1/verification");

        content.put("body", "{}");

        RequestBuilderFactory requestBuilderFactory = new RequestBuilderFactory();
        RequestBuilder builder = requestBuilderFactory.getRequestBuilder(BuilderType.ORACLE);

        BaseRequest request = builder.buildPostRequest(processConfigurationMap, content);

        try {
            HttpResponse<String> response = requestSubmitter.submitRequest(request);
            LOGGER.debug("Received response {} ", response);

        } catch (FailedRequestException e) {
            e.printStackTrace();
        }
    }
}
