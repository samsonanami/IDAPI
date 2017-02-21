package com.fintech.orion.hermesagentservices.processor.request.processor;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fintech.orion.common.exceptions.request.FailedRequestException;
import com.fintech.orion.common.exceptions.request.RequestProcessorException;
import com.fintech.orion.common.service.VerificationRequestDetailServiceInterface;
import com.fintech.orion.dataabstraction.entities.orion.Process;
import com.fintech.orion.dataabstraction.entities.orion.ProcessingRequest;
import com.fintech.orion.dataabstraction.entities.orion.Resource;
import com.fintech.orion.dataabstraction.exceptions.ItemNotFoundException;
import com.fintech.orion.dto.hermese.model.compressionlabs.response.FacialVerificationResponse;
import com.fintech.orion.dto.messaging.ProcessingMessage;
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
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Future;

/**
 * Created by sasitha on 2/17/17.
 *
 */
public class CompressionLabsRequestProcessor implements RequestProcessor {
    private static final Logger LOGGER = LoggerFactory.getLogger(CompressionLabsRequestProcessor.class);

    @Autowired
    private String compressionLabsBaseUrl;

    @Autowired
    private String resourceBaseLocation;

    @Autowired
    private String commandsFileLocation;

    @Autowired
    private VerificationRequestDetailServiceInterface verificationRequestDetailService;

    @Autowired
    private RequestSubmitterInterface requestSubmitter;

    @Async
    @Override
    @Transactional
    public Future<Object> processRequest(Object object) throws RequestProcessorException {
        ProcessingRequest processingRequest;
        ProcessingMessage processingMessage = (ProcessingMessage)object;
        FacialVerificationResponse verificationResponse = null;
        List<String> processTypeList = new ArrayList<>();
        processTypeList.add("facialVerification");
        List<Process> processList = verificationRequestDetailService
                .getProcessListBelongsToProcessingRequest(processingMessage.getVerificationRequestCode(),
                        processTypeList);

        if (!processList.isEmpty()) {
            try {
                processingRequest = verificationRequestDetailService.getProcessingRequest(processingMessage.getVerificationRequestCode());
            } catch (ItemNotFoundException e) {
                throw new RequestProcessorException("Could not found processing request with verification request code : " +
                        "" + processingMessage.getVerificationRequestCode(), e);
            }

            BaseRequest postRequest = buildPostRequest(processingRequest);

            try {
                verificationResponse = sendPostRequest(postRequest);
            } catch (FailedRequestException e) {
                throw new RequestProcessorException("Unable to send the facial verification request  ", e);
            } catch (IOException e) {
                throw new RequestProcessorException("Unable to map the response received from the facial verification" +
                        " service ", e);
            }
        }

        return new AsyncResult<>(verificationResponse);
    }

    @Transactional
    private FacialVerificationResponse sendPostRequest(BaseRequest postRequest) throws FailedRequestException, IOException {
        LOGGER.debug("Submitting request with body {} ", postRequest.toString());
        HttpResponse<String> response = requestSubmitter.submitRequest(postRequest);
        ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper.readValue(response.getBody(), FacialVerificationResponse.class);
    }

    @Transactional
    private BaseRequest buildPostRequest(ProcessingRequest processingRequest) {
        Map<String, String> processConfigurationMap = new HashMap<>();
        processConfigurationMap.put("url", compressionLabsBaseUrl + "match");


        RequestBuilderFactory requestBuilderFactory = new RequestBuilderFactory();
        RequestBuilder builder = requestBuilderFactory.getRequestBuilder(BuilderType.COMPRESSION);

        return builder.buildPostRequest(processConfigurationMap, getRequestBodyContent(processingRequest));
    }

    @Transactional
    private Map<String, Object> getRequestBodyContent(ProcessingRequest processingRequest) {
        List<String> processTypeList = new ArrayList<>();
        processTypeList.add("facialVerification");
        Map<String, Object> requestBodyContent = new HashMap<>();
        List<Process> processList = verificationRequestDetailService
                .getProcessListBelongsToProcessingRequest(processingRequest.getProcessingRequestIdentificationCode(),
                        processTypeList);

        for (Process process : processList){
            for (Resource resource :
                    process.getResources()) {
                requestBodyContent.put(getShortRequestName(resource.getResourceName().getName()), resourceBaseLocation
                        + "/" + resource.getLocation());
            }
        }

        requestBodyContent.put("f3", commandsFileLocation);
        requestBodyContent.put("f1-contentType", "video/mp4");
        requestBodyContent.put("f2-contentType", "image/jpeg");
        requestBodyContent.put("f3-contentType", "application/json");
        return requestBodyContent;
    }

    @Transactional
    private String getShortRequestName(String resourceName){
        String shortName = "f1";
        if ("facialVideo".equalsIgnoreCase(resourceName)){
            shortName = "f1";
        }else if ("faceImage".equalsIgnoreCase(resourceName)){
            shortName = "f2";
        }
        return shortName;
    }
}
