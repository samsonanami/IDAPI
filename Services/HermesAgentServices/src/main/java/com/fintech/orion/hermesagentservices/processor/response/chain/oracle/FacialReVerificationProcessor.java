package com.fintech.orion.hermesagentservices.processor.response.chain.oracle;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fintech.orion.common.Processor;
import com.fintech.orion.dto.hermese.model.compressionlabs.response.FacialReVerificationResponse;
import com.fintech.orion.dto.response.api.VerificationProcessDetailedResponse;
import com.fintech.orion.hermesagentservices.processor.VerificationResult;
import com.fintech.orion.hermesagentservices.processor.response.chain.ResponseProcessorChain;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.List;

public class FacialReVerificationProcessor extends ResponseProcessorChain {
    private static final Logger LOGGER = LoggerFactory.getLogger(FacialReVerificationProcessor.class);

    @Override
    protected void execute(VerificationProcessDetailedResponse response, List<VerificationResult> verificationResults,
                           String processingRequestId) {
        String rawString;
        ObjectMapper objectMapper = new ObjectMapper();
        for (VerificationResult verificationResult : verificationResults){
            if (verificationResult.getProcessor().equals(Processor.COMPRESSION_LABS)){
                rawString = verificationResult.getResultString();
                try {
                    FacialReVerificationResponse reVerificationResponse =
                            objectMapper.readValue(rawString, FacialReVerificationResponse.class);
                    response.setFacialMatch(getFaceMatchStatus(reVerificationResponse.getFaceMatchStatus()));
                    response.setLivenessTest(getLivenessStatus(reVerificationResponse.getLivenessStatus()));

                } catch (IOException e) {
                    LOGGER.error("Error in re verification facial values {} ", rawString, e);
                }
            }
        }
    }

    private String getLivenessStatus(String liveness){
        String status = "failed";
        if(liveness.equalsIgnoreCase("passed") ||
                liveness.equalsIgnoreCase("ManuallyVerifiedPassed")){
            status = "passed";
        }
        return status;
    }

    private String getFaceMatchStatus(String faceMatch){
        String status = "failed";
        if(faceMatch.equalsIgnoreCase("passed") ||
                faceMatch.equalsIgnoreCase("ManuallyVerifiedPassed")){
            status = "passed";
        }
        return status;
    }
}
