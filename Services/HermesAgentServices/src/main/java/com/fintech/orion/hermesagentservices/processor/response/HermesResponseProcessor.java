package com.fintech.orion.hermesagentservices.processor.response;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fintech.orion.common.exceptions.transformer.RequestTransformerException;
import com.fintech.orion.dto.hermese.ResponseProcessorResult;
import com.fintech.orion.dto.response.api.VerificationProcessDetailedResponse;
import com.fintech.orion.dto.response.external.VerificationResponse;
import com.fintech.orion.hermesagentservices.processor.VerificationResult;
import com.fintech.orion.hermesagentservices.processor.request.HermeseRequestProcessor;
import com.fintech.orion.hermesagentservices.processor.response.chain.ResponseProcessorChainHead;
import com.fintech.orion.hermesagentservices.transformer.ResponseTransformer;
import com.fintech.orion.hermesagentservices.transformer.custom.GenericResponseTransformer;
import com.fintech.orion.hermesagentservices.transformer.factory.ResponseTransformerFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;


public class HermesResponseProcessor implements HermesResponseProcessorInterface {
    private static final Logger LOGGER = LoggerFactory.getLogger(HermeseRequestProcessor.class);
    private static final String STATUS_FAILED = "failed";

    @Autowired
    private ResponseProcessorChainHead hermesResponseProcessorChainHead;

    @Autowired
    private ResponseTransformerFactory responseTransformerFactory;

    @Override
    public ResponseProcessorResult processVerificationResults(List<VerificationResult> verificationResults,
                                                              String processingRequestId) {
        ResponseProcessorResult result = new ResponseProcessorResult();
        ObjectMapper objectMapper = new ObjectMapper();
        VerificationProcessDetailedResponse verificationProcessDetailedResponse =
                new VerificationProcessDetailedResponse();
        VerificationResponse verificationResponse = new VerificationResponse();
        hermesResponseProcessorChainHead.start(verificationProcessDetailedResponse,
                verificationResults, processingRequestId);

        try {
            ResponseTransformer responseTransformer =
                    responseTransformerFactory.getResponseTransformer("defaultResponseTransformer");
            verificationResponse = (VerificationResponse)responseTransformer.transform(verificationProcessDetailedResponse);
            result.setProcessedString(objectMapper.writeValueAsString(verificationResponse));
        } catch (JsonProcessingException e) {
            LOGGER.error("Error parsing verification process detailed response object in to a string. " +
                    "Actual object is {} ", verificationProcessDetailedResponse, e);
        } catch (RequestTransformerException e) {
            LOGGER.error("Unable to transform internal state object {} to a verification response",
                    verificationProcessDetailedResponse, e);
        }
        if (STATUS_FAILED.equalsIgnoreCase(verificationResponse.getStatus())) {
            result.setFinalProcessingStatus(false);
        }else {
            result.setFinalProcessingStatus(true);
        }
        return result;
    }
}
