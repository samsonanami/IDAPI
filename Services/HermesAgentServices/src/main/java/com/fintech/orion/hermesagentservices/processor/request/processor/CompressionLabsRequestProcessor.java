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
import java.util.*;
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
            System.out.println(postRequest);

            try {
                updateProcessStarte(processList, new Date());
                verificationResponse = sendPostRequest(postRequest);
                System.out.println(verificationResponse);
                updateProcessCompletedTime(processList, new Date());

                LOGGER.debug("Received response from compression labs {} ", verificationResponse);

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
    public void updateProcessStarte(List<Process> processList, Date date){
        for (Process process : processList){
            process.setRequestSentOn(date);
        }
        verificationRequestDetailService.updateProcessDetails(processList);
    }

    @Transactional
    public void updateProcessCompletedTime(List<Process> processList, Date date){
        for (Process process : processList){
            process.setResponseReceivedOn(date);
        }
        verificationRequestDetailService.updateProcessDetails(processList);
    }

    @Transactional
    public FacialVerificationResponse sendPostRequest(BaseRequest postRequest) throws FailedRequestException, IOException {
        LOGGER.debug("Submitting request with body {} ", postRequest.toString());
        HttpResponse<String> response = requestSubmitter.submitRequest(postRequest);
        System.out.println(response);
        ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper.readValue(response.getBody(), FacialVerificationResponse.class);
    }

    @Transactional
    public BaseRequest buildPostRequest(ProcessingRequest processingRequest) {

        Map<String, String> processConfigurationMap = new HashMap<>();
        processConfigurationMap.put("url", compressionLabsBaseUrl + "match/");


        RequestBuilderFactory requestBuilderFactory = new RequestBuilderFactory();
        RequestBuilder builder = requestBuilderFactory.getRequestBuilder(BuilderType.COMPRESSION);

        System.out.println(builder.buildPostRequest(processConfigurationMap, getRequestBodyContent(processingRequest)));

        return builder.buildPostRequest(processConfigurationMap, getRequestBodyContent(processingRequest));
    }


    String commandFollowed = commandsFileLocation;

    @Transactional
    public Map<String, Object> getRequestBodyContent(ProcessingRequest processingRequest) {
        List<String> processTypeList = new ArrayList<>();
        processTypeList.add("facialVerification");
        Map<String, Object> requestBodyContent = new HashMap<>();
        List<Process> processList = verificationRequestDetailService
                .getProcessListBelongsToProcessingRequest(processingRequest.getProcessingRequestIdentificationCode(),
                        processTypeList);



        for (Process process : processList){
            for (Resource resource :
                    process.getResources()) {
                System.out.println(resource.getResourceName().getName()+"       resource.getResourceName().getName()");
                System.out.println(resource.getLocation()+"         resource.getLocation()");
                requestBodyContent.put(getShortRequestName(resource.getResourceName().getName()), resourceBaseLocation
                        + "/" + resource.getLocation());


            }
        }

/** Setting f1 resourse type and commandFollowed boolean **/

        Support support = new Support();
        support.set(requestBodyContent);
        String f1Type = support.f1Type;
        boolean set_CommandFollowed = support.set_CommandFollowed;

/** --------------------------------- **/

        System.out.println(f1Type);
        if(set_CommandFollowed == true) {
            requestBodyContent.put("commands_file", commandsFileLocation);
        }

        System.out.println(requestBodyContent);
        System.out.println(set_CommandFollowed);
        return requestBodyContent;
    }

    @Transactional
    public String getShortRequestName(String resourceName){    //id_image  commands_file
        String shortName = "selfie_video";
        if ("facialVideo".equalsIgnoreCase(resourceName)){
            shortName = "selfie_video";
        }else if ("faceImage".equalsIgnoreCase(resourceName)){
            shortName = "id_image";
        }else if ("faceMatchImage".equalsIgnoreCase(resourceName)){
            shortName = "selfie_image";
        }
        else if("commandFollowed".equalsIgnoreCase(resourceName)){
            shortName = "commands_file";
        }
        return shortName;
    }
}
