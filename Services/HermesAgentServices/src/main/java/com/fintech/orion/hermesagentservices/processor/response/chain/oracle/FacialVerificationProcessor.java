package com.fintech.orion.hermesagentservices.processor.response.chain.oracle;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fintech.orion.common.Processor;
import com.fintech.orion.documentverification.factory.DocumentVerification;
import com.fintech.orion.documentverification.factory.DocumentVerificationFactory;
import com.fintech.orion.documentverification.factory.DocumentVerificationType;
import com.fintech.orion.dto.configuration.VerificationConfiguration;
import com.fintech.orion.dto.hermese.model.compressionlabs.response.FacialVerificationResponse;
import com.fintech.orion.dto.response.api.VerificationProcessDetailedResponse;
import com.fintech.orion.hermesagentservices.processor.VerificationResult;
import com.fintech.orion.hermesagentservices.processor.response.chain.RequestProcessorChain;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import javax.annotation.Resource;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by sasitha on 2/18/17.
 *
 */
public class FacialVerificationProcessor extends RequestProcessorChain{
    private static final Logger LOGGER = LoggerFactory.getLogger(FacialVerificationProcessor.class);


    @Override
    protected void execute(VerificationProcessDetailedResponse response, List<VerificationResult> verificationResults, String processingRequestId) {
        String rawString = "";
        for (VerificationResult verificationResult : verificationResults){
            if (verificationResult.getProcessor().equals(Processor.COMPRESSION_LABS)){
                rawString = verificationResult.getResultString();
            }
        }

        if (rawString != null && !"null".equalsIgnoreCase(rawString) && !rawString.isEmpty()) {
            try {
                processRawString(response, rawString, processingRequestId);
            } catch (IOException e) {
                LOGGER.error("Error processing raw response {} for processing request id {} ",
                        rawString, processingRequestId, e);
            }
        }
    }

    private void processRawString(VerificationProcessDetailedResponse response, String rawString, String processingRequestId) throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        FacialVerificationResponse facialVerificationResponse = objectMapper.readValue(rawString, FacialVerificationResponse.class);

        facialVerification(response, facialVerificationResponse);

    }

    private void facialVerification(VerificationProcessDetailedResponse response, FacialVerificationResponse facialVerificationResponse) {

        if (facialVerificationResponse.getConfidence() == 0.02){
            response.setLivenessTest("failed");
        }else {
            response.setLivenessTest("passed");
        }

        if (facialVerificationResponse.getConfidence() >= 0.5){
            response.setFacialMatch("Good match");
        }else if (facialVerificationResponse.getConfidence() < 0.5 && facialVerificationResponse.getConfidence() >= 0.2){
            response.setFacialMatch("Manual verification required");
        }else if (facialVerificationResponse.getConfidence() < 0.2){
            response.setFacialMatch("Failed");
        }

    }


}
