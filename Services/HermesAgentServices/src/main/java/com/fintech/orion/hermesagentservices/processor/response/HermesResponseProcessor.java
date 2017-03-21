package com.fintech.orion.hermesagentservices.processor.response;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fintech.orion.dto.hermese.ResponseProcessorResult;
import com.fintech.orion.dto.response.api.ValidationData;
import com.fintech.orion.dto.response.api.VerificationProcessDetailedResponse;
import com.fintech.orion.hermesagentservices.processor.VerificationResult;
import com.fintech.orion.hermesagentservices.processor.request.HermeseRequestProcessor;
import com.fintech.orion.hermesagentservices.processor.response.chain.oracle.FacialVerificationProcessor;
import com.fintech.orion.hermesagentservices.processor.response.chain.oracle.OracleResponseProcessor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * Created by sasitha on 2/17/17.
 *
 */
public class HermesResponseProcessor implements HermesResponseProcessorInterface {
    private static final Logger LOGGER = LoggerFactory.getLogger(HermeseRequestProcessor.class);
    private static final String STATUS_FAILED = "processing_failed";
    private static final String STATUS_PASSED = "processing_successful";

    @Autowired
    private OracleResponseProcessor oracleResponseProcessor;

    @Autowired
    private FacialVerificationProcessor facialVerificationProcessor;

    @Override
    public ResponseProcessorResult processVerificationResults(List<VerificationResult> verificationResults,
                                                              String processingRequestId) {
        ObjectMapper objectMapper = new ObjectMapper();
        ResponseProcessorResult result = new ResponseProcessorResult();
        VerificationProcessDetailedResponse verificationProcessDetailedResponse =
                new VerificationProcessDetailedResponse();

        oracleResponseProcessor.setNext(facialVerificationProcessor);
        oracleResponseProcessor.start(verificationProcessDetailedResponse,
                verificationResults, processingRequestId);

        setFinalProcessingStatus(verificationProcessDetailedResponse);

        try {
            result.setProcessedString(objectMapper.writeValueAsString(verificationProcessDetailedResponse));
        } catch (JsonProcessingException e) {
            LOGGER.error("Error parsing verification process detailed response object in to a string. " +
                    "Actual object is {} ", verificationProcessDetailedResponse, e);
        }
        if (STATUS_FAILED.equalsIgnoreCase(verificationProcessDetailedResponse.getStatus())){
            result.setFinalProcessingStatus(false);
        }

        return result;
    }


    private void setFinalProcessingStatus(VerificationProcessDetailedResponse detailedResponse){
        detailedResponse.setStatus(STATUS_PASSED);
        for (ValidationData validation : detailedResponse.getIdDocFullValidations()){
            if("critical_error_set".equalsIgnoreCase(validation.getId()) && !validation.getRemarks().isEmpty()){
                detailedResponse.setStatus(STATUS_FAILED);
            }
        }
        for (ValidationData validation : detailedResponse.getAddressDocFullValidations()){
            if("critical_error_set".equalsIgnoreCase(validation.getId())&& !validation.getRemarks().isEmpty()){
                detailedResponse.setStatus(STATUS_FAILED);
            }
        }
        if (!"passed".equalsIgnoreCase(detailedResponse.getFacialMatch())){
            detailedResponse.setStatus(STATUS_FAILED);
        }
        if (!"passed".equalsIgnoreCase(detailedResponse.getLivenessTest())){
            detailedResponse.setStatus(STATUS_FAILED);
        }
    }
}
