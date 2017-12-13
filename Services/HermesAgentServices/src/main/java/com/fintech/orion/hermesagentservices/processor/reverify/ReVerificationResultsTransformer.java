package com.fintech.orion.hermesagentservices.processor.reverify;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fintech.orion.common.Processor;
import com.fintech.orion.dto.hermese.model.compressionlabs.response.FacialVerificationResponse;
import com.fintech.orion.dto.hermese.model.oracle.response.OcrResponse;
import com.fintech.orion.dto.messaging.ProcessingMessage;
import com.fintech.orion.dto.response.external.Verification;
import com.fintech.orion.hermesagentservices.processor.VerificationResult;
import com.fintech.orion.hermesagentservices.transformer.custom.ReVerificationOCRDataTransformer;
import com.fintech.orion.hermesagentservices.transformer.custom.ReverificationLinessTestDataTransformer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;

public class ReVerificationResultsTransformer {
    private static final Logger LOGGER = LoggerFactory.getLogger(ReVerificationResultsTransformer.class);
    private String idVerificationName;
    private String addressVerificationName;
    private String facialVerificationName;

    public List<VerificationResult> verificationResultsFromManualPortalInput(ProcessingMessage processingMessage){
        List<VerificationResult> results = new ArrayList<>();
        ObjectMapper objectMapper = new ObjectMapper();

        if(isVerificationIsRequested(idVerificationName, processingMessage.getVerificationResponse().getVerificationDetails()) ||
                isVerificationIsRequested(addressVerificationName, processingMessage.getVerificationResponse().getVerificationDetails())){
            VerificationResult oracleResults = new VerificationResult();
            oracleResults.setProcessor(Processor.ORACLE);
            try {
                ReVerificationOCRDataTransformer transformer = new ReVerificationOCRDataTransformer();
                OcrResponse ocrResponse = transformer.transform(processingMessage.getVerificationResponse());
                oracleResults.setResultString(objectMapper.writeValueAsString(ocrResponse));
            } catch (JsonProcessingException e) {
                LOGGER.error("Unable to parse json {} ", e);
            }
            results.add(oracleResults);
        }

        if(isVerificationIsRequested(facialVerificationName, processingMessage.getVerificationResponse().getVerificationDetails())){
            VerificationResult compressionLabsResult = new VerificationResult();
            compressionLabsResult.setProcessor(Processor.COMPRESSION_LABS);

            ReverificationLinessTestDataTransformer livenessDataTransformer = new ReverificationLinessTestDataTransformer();
            FacialVerificationResponse facialResponse = livenessDataTransformer.transform(processingMessage.getVerificationResponse());

            try {
                compressionLabsResult.setResultString(objectMapper.writeValueAsString(facialResponse));
            } catch (JsonProcessingException e) {
                LOGGER.error("Unable to parse json {} ", e);
            }

            results.add(compressionLabsResult);
        }

        return results;
    }


    public void setIdVerificationName(String idVerificationName) {
        this.idVerificationName = idVerificationName;
    }

    public void setAddressVerificationName(String addressVerificationName) {
        this.addressVerificationName = addressVerificationName;
    }

    public void setFacialVerificationName(String facialVerificationName) {
        this.facialVerificationName = facialVerificationName;
    }

    private boolean isVerificationIsRequested(String verificationRequestType,
                                              List<Verification> verificationProcessDetails){
        boolean isVerificationFound = false;
        for (Verification verificationProcessDetail : verificationProcessDetails){
            if (verificationProcessDetail.getVerificationProcessName().equalsIgnoreCase(verificationRequestType)){
                isVerificationFound = true;
                break;
            }
        }

        return isVerificationFound;
    }
}
