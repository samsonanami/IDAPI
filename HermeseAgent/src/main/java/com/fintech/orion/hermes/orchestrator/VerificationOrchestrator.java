package com.fintech.orion.hermes.orchestrator;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fintech.orion.common.Processor;
import com.fintech.orion.common.exceptions.license.LicenseHandlerException;
import com.fintech.orion.common.exceptions.request.RequestProcessorException;
import com.fintech.orion.common.service.VerificationRequestDetailService;
import com.fintech.orion.dataabstraction.entities.orion.Process;
import com.fintech.orion.dto.hermese.ResponseProcessorResult;
import com.fintech.orion.dto.hermese.model.oracle.response.OcrResponse;
import com.fintech.orion.dto.messaging.ProcessingMessage;
import com.fintech.orion.hermesagentservices.license.LicenseHandlerInterface;
import com.fintech.orion.hermesagentservices.processor.VerificationResult;
import com.fintech.orion.hermesagentservices.processor.request.HermeseRequestProcessor;
import com.fintech.orion.hermesagentservices.processor.response.HermesResponseProcessor;
import com.fintech.orion.hermesagentservices.transformer.custom.ReVerificationOCRDataTransformer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;


/**
 * Created by sasitha on 12/19/16.
 *
 */
public class VerificationOrchestrator {
    private static final Logger LOGGER = LoggerFactory.getLogger(VerificationOrchestrator.class);


    @Autowired
    private VerificationRequestDetailService verificationRequestDetailService;

    @Autowired
    private HermesResponseProcessor hermesResponseProcessor;

    @Autowired
    private LicenseHandlerInterface licenseHandler;

    @Autowired
    private HermeseRequestProcessor hermeseRequestProcessor;


    public void orchestrate(Object message){
        long start = System.currentTimeMillis();
        LOGGER.debug("Start orchestrating message {} ", message);
        ProcessingMessage processingMessage = (ProcessingMessage)message;

        List<VerificationResult> verificationResultList  =
                null;
        if(!processingMessage.isReVerification()){
            try {
                verificationResultList = hermeseRequestProcessor.processVerificationRequest(processingMessage);
            } catch (RequestProcessorException e) {
                LOGGER.error("Unable process verification request {} ", processingMessage, e);
            }
        }else {
            verificationResultList = verificationResultsForReVerification(processingMessage);
        }

        ResponseProcessorResult responseProcessorResult =
                hermesResponseProcessor.processVerificationResults(verificationResultList,
                        processingMessage.getVerificationRequestCode());

        saveProcessResponse(responseProcessorResult, processingMessage.getVerificationRequestCode());

        LOGGER.info("Total time elapse to complete the full processing {} ",  System.currentTimeMillis() - start);

        try {
            licenseHandler.updateLicense(processingMessage.getClientLicense(), processingMessage.getVerificationRequestCode());
        } catch (LicenseHandlerException e) {
            LOGGER.error("Unable to update license for license key {} ", processingMessage.getClientLicense(), e);
        }

    }

    private List<VerificationResult> verificationResultsForReVerification(ProcessingMessage processingMessage){

        List<VerificationResult> results = new ArrayList<>();
        ObjectMapper objectMapper = new ObjectMapper();
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
        return results;
    }

    @Transactional
    private void saveProcessResponse(ResponseProcessorResult responseProcessorResult, String verificationCode){
        verificationRequestDetailService.saveFinalVerificationResponse(responseProcessorResult.getProcessedString(),
                verificationCode, responseProcessorResult.getFinalProcessingStatus(), responseProcessorResult.getClientName());
    }

}
