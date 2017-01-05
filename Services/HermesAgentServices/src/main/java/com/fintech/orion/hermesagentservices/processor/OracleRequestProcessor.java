package com.fintech.orion.hermesagentservices.processor;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fintech.orion.common.exceptions.request.FailedRequestException;
import com.fintech.orion.common.exceptions.request.RequestProcessorException;
import com.fintech.orion.common.service.VerificationRequestDetailServiceInterface;
import com.fintech.orion.dataabstraction.entities.orion.Process;
import com.fintech.orion.dataabstraction.entities.orion.ProcessingRequest;
import com.fintech.orion.dataabstraction.entities.orion.Resource;
import com.fintech.orion.dataabstraction.exceptions.ItemNotFoundException;
import com.fintech.orion.dto.messaging.ProcessingMessage;
import com.fintech.orion.dto.hermese.model.Oracle.VerificationProcessResponse;
import com.fintech.orion.dto.hermese.model.Oracle.VerificationResource;
import com.fintech.orion.dto.hermese.model.Oracle.response.OcrResponse;
import com.fintech.orion.hermesagentservices.transmission.request.body.builder.RequestBodyBuilder;
import com.fintech.orion.hermesagentservices.transmission.request.body.builder.RequestBodyBuilderFactory;
import com.fintech.orion.hermesagentservices.transmission.request.body.builder.RequestBodyType;
import com.fintech.orion.hermesagentservices.transmission.request.builder.BuilderType;
import com.fintech.orion.hermesagentservices.transmission.request.builder.RequestBuilder;
import com.fintech.orion.hermesagentservices.transmission.request.builder.RequestBuilderFactory;
import com.fintech.orion.hermesagentservices.transmission.request.submit.RequestSubmitterInterface;
import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.request.BaseRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.AsyncResult;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
import java.util.*;
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

    @Autowired
    private VerificationRequestDetailServiceInterface verificationRequestDetailService;

    @Autowired
    private String oracleAPIBaseUrl;

    @Autowired
    private String maximumWaitingTimeInSeconds;

    @Async
    @Override
    @Transactional
    public Future<Object> process(Object object) throws RequestProcessorException {

        ProcessingRequest processingRequest;
        ProcessingMessage processingMessage = (ProcessingMessage)object;
        OcrResponse response = new OcrResponse();
        try {
            processingRequest = verificationRequestDetailService.getProcessingRequest(processingMessage.getVerificationRequestCode());
        } catch (ItemNotFoundException e) {
            throw new RequestProcessorException("Could not found processing request with verification request code : " + processingMessage.getVerificationRequestCode());
        }

        BaseRequest postRequest = buildPostRequest(processingRequest);

        try {
            VerificationProcessResponse verificationResponse = sendPostRequest(postRequest);
            response = waitForProcessingComplete(verificationResponse);
        } catch (FailedRequestException e) {
            throw new RequestProcessorException("Could not send request to oracle api", e);
        } catch (IOException e) {
            throw new RequestProcessorException("Could not map response from oracle api to the post request : " + postRequest.toString());
        } catch (InterruptedException e) {
            throw new RequestProcessorException("Interruption occurred while waiting to get the processed data from the oracle api ", e);
        }

        return new AsyncResult<>(response);
    }

    @Transactional
    private BaseRequest buildPostRequest(ProcessingRequest processingRequest){
        Map<String, String> processConfigurationMap = new HashMap<>();
        Map<String, Object> content = new HashMap<>();
        processConfigurationMap.put("url", oracleAPIBaseUrl + "verification");
        processConfigurationMap.put("header.contentType", "application/json");

        RequestBodyBuilderFactory requestBodyBuilderFactory = new RequestBodyBuilderFactory();
        RequestBodyBuilder requestBodyBuilder = requestBodyBuilderFactory.getRequestBodyBuilder(RequestBodyType.ORACLE);

        String body = requestBodyBuilder.getRequestBody(getRequestBodyContent(processingRequest));
        content.put("body", body);

        RequestBuilderFactory requestBuilderFactory = new RequestBuilderFactory();
        RequestBuilder builder = requestBuilderFactory.getRequestBuilder(BuilderType.ORACLE);

        return builder.buildPostRequest(processConfigurationMap, content);
    }

    @Transactional
    private VerificationProcessResponse sendPostRequest(BaseRequest baseRequest) throws FailedRequestException, IOException {
        LOGGER.debug("Submitting request with body {} ", baseRequest.toString());
        HttpResponse<String> response = requestSubmitter.submitRequest(baseRequest);
        LOGGER.debug("Received response {} ", response);
        ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper.readValue(response.getBody(), VerificationProcessResponse.class);
    }

    private OcrResponse waitForProcessingComplete(VerificationProcessResponse verificationProcessResponse) throws FailedRequestException, InterruptedException, IOException {
        String verificationProcessId = verificationProcessResponse.getVerificationProcessCode();
        String processDetailsCheckUrl = oracleAPIBaseUrl + "verification/" + verificationProcessId;
        Map<String, String> processConfigurationMap = new HashMap<>();
        processConfigurationMap.put("url", processDetailsCheckUrl);

        RequestBuilderFactory requestBuilderFactory = new RequestBuilderFactory();
        RequestBuilder builder = requestBuilderFactory.getRequestBuilder(BuilderType.ORACLE);
        BaseRequest getRequest = builder.buildGetRequest(processConfigurationMap, null);
        HttpResponse<String> response = null;
        OcrResponse ocrResponse = new OcrResponse();
        boolean continueCheck = true;
        int maximumTimeToWait = (Integer.valueOf(maximumWaitingTimeInSeconds)/10);
        long startTime = System.currentTimeMillis();
        while (continueCheck && maximumTimeToWait >0){
            maximumTimeToWait--;
            Thread.sleep(10000);
            response = requestSubmitter.submitRequest(getRequest);
            if (response.getStatus() == 200){
                ObjectMapper objectMapper = new ObjectMapper();
                ocrResponse = objectMapper.readValue(response.getBody(), OcrResponse.class);
                if (ocrResponse.getStatus().equals("processing_successful") ||
                        ocrResponse.getStatus().equals("processing_failed")){
                    continueCheck = false;
                }
            }else {
                continueCheck = false;
            }
        }
        LOGGER.debug("Received final response from oracle api : {}  elapsed time to receive response from ocr api" +
                " {} ", response, System.currentTimeMillis() - startTime);
        return  ocrResponse;
    }

    @Transactional
    private Map<String, Object> getRequestBodyContent(ProcessingRequest processingRequest){
        Map<String, Object> requestBodyContent = new HashMap<>();
        for (Process p : processingRequest.getProcesses()){
            List<VerificationResource> resourceList = new ArrayList<>();
            for (Resource r : p.getResources()){
                resourceList.add(getPayloadResource(r));
            }

            requestBodyContent.put(p.getProcessType().getType(), resourceList);
        }
        return  requestBodyContent;
    }

    private VerificationResource getPayloadResource(Resource resource){
        VerificationResource payloadResource = new VerificationResource();
        payloadResource.setResourceId(resource.getResourceIdentificationCode());
        payloadResource.setResourceName(resource.getResourceName().getName());
        return payloadResource;
    }

}