package com.fintech.orion.hermesagentservices.processor.response;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fintech.orion.dto.hermese.ResponseProcessorResult;
import com.fintech.orion.dto.response.api.ValidationData;
import com.fintech.orion.dto.response.api.VerificationProcessDetailedResponse;
import com.fintech.orion.hermesagentservices.processor.VerificationResult;
import com.fintech.orion.hermesagentservices.processor.response.chain.oracle.OracleResponseProcessor;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * Created by sasitha on 2/17/17.
 *
 */
public class HermesResponseProcessor implements HermesResponseProcessorInterface {
    private static final String STATUS_FAILED = "processing_failed";

    @Autowired
    private OracleResponseProcessor oracleResponseProcessor;

    @Override
    public ResponseProcessorResult processVerificationResults(List<VerificationResult> verificationResults,
                                                              String processingRequestId) {
        ObjectMapper objectMapper = new ObjectMapper();
        ResponseProcessorResult result = new ResponseProcessorResult();
        VerificationProcessDetailedResponse verificationProcessDetailedResponse =
                new VerificationProcessDetailedResponse();

        oracleResponseProcessor.start(verificationProcessDetailedResponse,
                verificationResults, processingRequestId);

        setFinalProcessingStatus(verificationProcessDetailedResponse);

        try {
            result.setProcessedString(objectMapper.writeValueAsString(verificationProcessDetailedResponse));
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        if (STATUS_FAILED.equalsIgnoreCase(verificationProcessDetailedResponse.getStatus())){
            result.setFinalProcessingStatus(false);
        }

        return result;
    }


    private void setFinalProcessingStatus(VerificationProcessDetailedResponse detailedResponse){
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
    }
}
