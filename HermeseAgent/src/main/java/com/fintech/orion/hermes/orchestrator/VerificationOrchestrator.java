package com.fintech.orion.hermes.orchestrator;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fintech.orion.common.exceptions.HermeseResponseprocessorException;
import com.fintech.orion.common.exceptions.request.RequestProcessorException;
import com.fintech.orion.common.service.VerificationRequestDetailService;
import com.fintech.orion.dataabstraction.entities.orion.License;
import com.fintech.orion.dataabstraction.entities.orion.Process;
import com.fintech.orion.dataabstraction.entities.orion.ProcessingRequest;
import com.fintech.orion.dataabstraction.exceptions.ItemNotFoundException;
import com.fintech.orion.dataabstraction.repositories.LicenseRepositoryInterface;
import com.fintech.orion.dto.hermese.ResponseProcessorResult;
import com.fintech.orion.dto.hermese.model.Oracle.response.OcrResponse;
import com.fintech.orion.dto.messaging.ProcessingMessage;
import com.fintech.orion.hermesagentservices.processor.OracleRequestProcessor;
import com.fintech.orion.hermesagentservices.response.processor.HermeseResponseProcessor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

/**
 * Created by sasitha on 12/19/16.
 *
 */
public class VerificationOrchestrator {
    private static final Logger LOGGER = LoggerFactory.getLogger(VerificationOrchestrator.class);

    @Autowired
    private OracleRequestProcessor oracleRequestProcessor;

    @Autowired
    private VerificationRequestDetailService verificationRequestDetailService;

    @Autowired
    private HermeseResponseProcessor hermeseResponseProcessor;

    @Autowired
    private LicenseRepositoryInterface licenseRepositoryInterface;

    @Transactional
    public void orchestrate(Object message){
        long start = System.currentTimeMillis();
        LOGGER.debug("Start orchestrating message {} ", message);
        ProcessingMessage processingMessage = (ProcessingMessage)message;
        ObjectMapper objectMapper = new ObjectMapper();
        Future<Object> oracleResults = null;
        try {
            oracleResults = oracleRequestProcessor.process(message);
        } catch (RequestProcessorException e) {
            LOGGER.error("Unable to process oracle verification process ", e);
        }

        if (oracleResults != null){
            while (!oracleResults.isDone()){
                LOGGER.debug("Still processing and waiting");
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

            try {
                LOGGER.debug("received results {}", oracleResults.get());
                LOGGER.debug("Elapsed time to complete the processing : " + (System.currentTimeMillis() - start));
                ProcessingRequest processingRequest = verificationRequestDetailService.getProcessingRequest(processingMessage.getVerificationRequestCode());
                String rawString = objectMapper.writeValueAsString(oracleResults.get());
                ResponseProcessorResult result = hermeseResponseProcessor.processAndUpdateRawResponse(rawString, processingRequest);
                updateLicenseStatus(rawString, processingMessage.getClientLicense());
                for (Process process : processingRequest.getProcesses()){
                    saveProcessResponse(rawString, result.getProcessedString(), process);
                }

            } catch (InterruptedException e) {
                LOGGER.error("Verification process orchestration interrupted ", e);
            } catch (ExecutionException e) {
                LOGGER.error("Verification process execution occurred ", e);
            } catch (ItemNotFoundException e) {
                LOGGER.error("Failed to find required data to complete the processing ", e);
            } catch (JsonProcessingException e) {
                LOGGER.error("Error occurred processing one or more json ", e);
            } catch (HermeseResponseprocessorException e) {
                LOGGER.error("Error while processing the response ", e);
            }
        }


    }

    @Transactional
    private void updateLicenseStatus(String rawString, String licenseKey) throws ItemNotFoundException {
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            OcrResponse response = objectMapper.readValue(rawString, OcrResponse.class);
            if (response.getStatus().equalsIgnoreCase("processing_successful")){
                License license = licenseRepositoryInterface.findLicenseByLicenseKey(licenseKey);
                int currentLicenseCount = license.getCurrentRequestCount();
                currentLicenseCount = currentLicenseCount + 1;
                license.setCurrentRequestCount(currentLicenseCount);
                licenseRepositoryInterface.save(license);
            }
        } catch (IOException e) {
            LOGGER.error("Error while trying to update license key", e);
        }
    }
    @Transactional
    private void saveProcessResponse(String rawResponse, String processedString, Process process){
        verificationRequestDetailService.saveResponse(rawResponse, processedString, process);
    }

}
