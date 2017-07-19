package com.fintech.orion.hermesagentservices.transmission.request.body.builder;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fintech.orion.dto.hermese.model.oracle.VerificationResource;
import com.fintech.orion.dto.hermese.model.oracle.VerificationProcess;
import com.fintech.orion.dto.hermese.model.oracle.VerificationRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 * Created by sasitha on 12/19/16.
 *
 */
public class OracleRequestBodyBuilder implements RequestBodyBuilder{
    private static final Logger LOGGER = LoggerFactory.getLogger(OracleRequestBodyBuilder.class);
    @Override
    public String getRequestBody(Map<String, Object> bodyContent) {
        VerificationRequest verificationRequest = new VerificationRequest();

        List<VerificationProcess> verificationProcessList = new ArrayList<>();
        Iterator iterator = bodyContent.entrySet().iterator();
        while (iterator.hasNext()){
            Map.Entry pair = (Map.Entry)iterator.next();
            VerificationProcess verificationProcess = new VerificationProcess();
            verificationProcess.setVerificationProcessType(pair.getKey().toString());
            verificationProcess.setResources((List<VerificationResource>) pair.getValue());
            verificationProcessList.add(verificationProcess);
        }
        verificationRequest.setVerificationProcesses(verificationProcessList);
        String bodyString = "{}";
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            bodyString = objectMapper.writeValueAsString(verificationRequest);
        } catch (JsonProcessingException e) {
            LOGGER.error("Unable to parse the json " ,e);
        }
        return bodyString;
    }
}
