package com.fintech.orion.hermesagentservices.processor;

import com.fintech.orion.common.exceptions.request.FailedRequestException;
import com.fintech.orion.common.service.VerificationRequestDetailServiceInterface;
import com.fintech.orion.dataabstraction.entities.orion.Process;
import com.fintech.orion.dataabstraction.entities.orion.ProcessingRequest;
import com.fintech.orion.dataabstraction.exceptions.ItemNotFoundException;
import com.fintech.orion.dto.messaging.ProcessingMessage;
import com.fintech.orion.dto.request.GenericRequest;
import com.fintech.orion.hermesagentservices.transmission.request.body.builder.RequestBodyBuilder;
import com.fintech.orion.hermesagentservices.transmission.request.body.builder.RequestBodyBuilderFactory;
import com.fintech.orion.hermesagentservices.transmission.request.body.builder.RequestBodyType;
import com.fintech.orion.hermesagentservices.transmission.request.builder.BuilderType;
import com.fintech.orion.hermesagentservices.transmission.request.builder.RequestBuilder;
import com.fintech.orion.hermesagentservices.transmission.request.builder.RequestBuilderFactory;
import com.fintech.orion.hermesagentservices.transmission.request.submit.RequestSubmitterInterface;
import com.fintech.orion.hermesagentservices.transmission.request.type.Oracle;
import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.request.BaseRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.AsyncResult;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.Future;

/**
 * Created by sasitha on 12/19/16.
 *
 */
@Component
public class OracleRequestProcessor implements VerificationProcessor {

    private static final Logger LOGGER = LoggerFactory.getLogger(OracleRequestProcessor.class);

    @Autowired
    private RequestSubmitterInterface requestSubmitter;



    @Async
    @Override
    public Future<Object> process(Object object) {
        Object results = new Object();
        Map<String, Object> content = new HashMap<>();
        Map<String, String> processConfigurationMap = new HashMap<>();
        Map<String, Object> requestBodyContent = new HashMap<>();


        processConfigurationMap.put("url", "http://10.101.15.212:8080/oracle/v1/verification");
        processConfigurationMap.put("header.contentType", "application/json");

        RequestBodyBuilderFactory requestBodyBuilderFactory = new RequestBodyBuilderFactory();
        RequestBodyBuilder requestBodyBuilder = requestBodyBuilderFactory.getRequestBodyBuilder(RequestBodyType.ORACLE);

        String body = requestBodyBuilder.getRequestBody(requestBodyContent);

        content.put("body", body);

        RequestBuilderFactory requestBuilderFactory = new RequestBuilderFactory();
        RequestBuilder builder = requestBuilderFactory.getRequestBuilder(BuilderType.ORACLE);

        BaseRequest request = builder.buildRequest(processConfigurationMap, content);

        try {
            HttpResponse<String> response = requestSubmitter.submitRequest(request);
            LOGGER.debug("Received response {} ", response.getStatus());

        } catch (FailedRequestException e) {
            e.printStackTrace();
        }
        return new AsyncResult<>(results);
    }
}
