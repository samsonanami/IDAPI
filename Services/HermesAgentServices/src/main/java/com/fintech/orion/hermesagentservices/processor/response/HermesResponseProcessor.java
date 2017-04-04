package com.fintech.orion.hermesagentservices.processor.response;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fintech.orion.common.exceptions.transformer.RequestTransformerException;
import com.fintech.orion.dto.hermese.ResponseProcessorResult;
import com.fintech.orion.dto.response.api.ValidationData;
import com.fintech.orion.dto.response.api.VerificationProcessDetailedResponse;
import com.fintech.orion.hermesagentservices.processor.VerificationResult;
import com.fintech.orion.hermesagentservices.processor.request.HermeseRequestProcessor;
import com.fintech.orion.hermesagentservices.processor.response.chain.ResponseProcessorChainHead;
import com.fintech.orion.hermesagentservices.transformer.ResponseTransformer;
import com.fintech.orion.hermesagentservices.transformer.factory.ResponseTransformerFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;


public class HermesResponseProcessor implements HermesResponseProcessorInterface {
    private static final Logger LOGGER = LoggerFactory.getLogger(HermeseRequestProcessor.class);
    private static final String STATUS_FAILED = "verification_failed";
    private static final String STATUS_PASSED = "verification_successful";

    @Autowired
    private ResponseProcessorChainHead hermesResponseProcessorChainHead;

    @Autowired
    private ResponseTransformerFactory responseTransformerFactory;

    @Override
    public ResponseProcessorResult processVerificationResults(List<VerificationResult> verificationResults,
                                                              String processingRequestId) {
        ResponseProcessorResult result = new ResponseProcessorResult();
        VerificationProcessDetailedResponse verificationProcessDetailedResponse =
                new VerificationProcessDetailedResponse();

        hermesResponseProcessorChainHead.start(verificationProcessDetailedResponse,
                verificationResults, processingRequestId);

        setFinalProcessingStatus(verificationProcessDetailedResponse);

        try {
            ResponseTransformer responseTransformer =
                    responseTransformerFactory.getResponseTransformer("defaultResponseTransformer");
            String transformedJsonString = responseTransformer.transform(verificationProcessDetailedResponse);
            result.setProcessedString(transformedJsonString);
        } catch (RequestTransformerException e) {
            LOGGER.error("Error parsing verification process detailed response object in to a string. " +
                    "Actual object is {} ", verificationProcessDetailedResponse, e);
        }
        if (STATUS_FAILED.equalsIgnoreCase(verificationProcessDetailedResponse.getStatus())) {
            result.setFinalProcessingStatus(false);
        }

        return result;
    }


    private void setFinalProcessingStatus(VerificationProcessDetailedResponse detailedResponse) {
        detailedResponse.setStatus(STATUS_PASSED);
        for (ValidationData validation : detailedResponse.getIdDocFullValidations()) {
            if ("critical_error_set".equalsIgnoreCase(validation.getId()) && !validation.getRemarks().isEmpty()) {
                detailedResponse.setStatus(STATUS_FAILED);
            }
        }
        for (ValidationData validation : detailedResponse.getAddressDocFullValidations()) {
            if ("critical_error_set".equalsIgnoreCase(validation.getId()) && !validation.getRemarks().isEmpty()) {
                detailedResponse.setStatus(STATUS_FAILED);
            }
        }
        if (detailedResponse.getFacialMatch() != null &&
                !"passed".equalsIgnoreCase(detailedResponse.getFacialMatch())) {
            detailedResponse.setStatus(STATUS_FAILED);
        }
        if (detailedResponse.getLivenessTest() != null &&
                !"passed".equalsIgnoreCase(detailedResponse.getLivenessTest())) {
            detailedResponse.setStatus(STATUS_FAILED);
        }
    }
}
