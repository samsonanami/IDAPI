package com.fintech.orion.hermesagentservices.processor.response.chain.oracle;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fintech.orion.common.Processor;
import com.fintech.orion.common.exceptions.ConfigurationProvidorException;
import com.fintech.orion.dto.hermese.model.compressionlabs.response.FacialVerificationResponse;
import com.fintech.orion.dto.response.api.VerificationProcessDetailedResponse;
import com.fintech.orion.hermesagentservices.processor.VerificationResult;
import com.fintech.orion.hermesagentservices.processor.response.chain.RequestProcessorChain;
import com.fintech.orion.hermesagentservices.provider.ConfigurationProvider;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.IOException;
import java.util.List;

/**
 * Created by sasitha on 2/18/17.
 *
 */
public class FacialVerificationProcessor extends RequestProcessorChain{
    private static final Logger LOGGER = LoggerFactory.getLogger(FacialVerificationProcessor.class);

    private static final String FACIAL_VERIFICATION = "facialVerification";


    @Autowired
    private ConfigurationProvider configurationProvider;

    @Override
    protected void execute(VerificationProcessDetailedResponse response, List<VerificationResult> verificationResults, String processingRequestId) {
        String rawString = "";
        String clientId = "";
        for (VerificationResult verificationResult : verificationResults){
            if (verificationResult.getProcessor().equals(Processor.COMPRESSION_LABS)){
                rawString = verificationResult.getResultString();
                clientId = verificationResult.getLicense();
            }
        }

        if (rawString != null && !"null".equalsIgnoreCase(rawString) && !rawString.isEmpty()) {
            try {
                processRawString(response, rawString, processingRequestId, clientId);
            } catch (IOException e) {
                LOGGER.error("Error processing raw response {} for processing request id {} ",
                        rawString, processingRequestId, e);
            }
        }
    }

    private void processRawString(VerificationProcessDetailedResponse response, String rawString,
                                  String processingRequestId, String clientId) throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        FacialVerificationResponse facialVerificationResponse = objectMapper.readValue(rawString, FacialVerificationResponse.class);

        facialVerification(response, facialVerificationResponse, clientId);

    }

    private void facialVerification(VerificationProcessDetailedResponse response,
                                    FacialVerificationResponse facialVerificationResponse, String clientId) {

        String livenessTestFaileValue = "0.02";
        String facialVerificationSuccessValue = "0.5";
        String facialVerificationFailedValue = "0.2";
        try {
            livenessTestFaileValue = configurationProvider.getConfigurationValue(clientId,
                    "livenes_test_faile_value", FACIAL_VERIFICATION);
            facialVerificationSuccessValue = configurationProvider.getConfigurationValue(clientId,
                    "facial_match_success_threashold", FACIAL_VERIFICATION);
            facialVerificationFailedValue = configurationProvider.getConfigurationValue(clientId,
                    "facial_match_manual_threashold", FACIAL_VERIFICATION);
        } catch (ConfigurationProvidorException e) {
            LOGGER.error("Unable to find configuration value ", e);
        }


        if (facialVerificationResponse.getConfidence() == Double.valueOf(livenessTestFaileValue)){
            response.setLivenessTest("failed");
        }else {
            response.setLivenessTest("passed");
        }

        if (facialVerificationResponse.getConfidence() >= Double.valueOf(facialVerificationSuccessValue)){
            response.setFacialMatch("passed");
        }else if (facialVerificationResponse.getConfidence() < Double.valueOf(facialVerificationSuccessValue) &&
                facialVerificationResponse.getConfidence() >= Double.valueOf(facialVerificationFailedValue)){
            response.setFacialMatch("manual");
        }else if (facialVerificationResponse.getConfidence() < Double.valueOf(facialVerificationFailedValue)){
            response.setFacialMatch("failed");
        }

    }


}
