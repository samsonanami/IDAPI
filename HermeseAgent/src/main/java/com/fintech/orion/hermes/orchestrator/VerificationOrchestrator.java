package com.fintech.orion.hermes.orchestrator;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fintech.orion.common.exceptions.HermeseResponseprocessorException;
import com.fintech.orion.common.exceptions.license.LicenseHandlerException;
import com.fintech.orion.common.exceptions.request.RequestProcessorException;
import com.fintech.orion.common.service.VerificationRequestDetailService;
import com.fintech.orion.dataabstraction.entities.orion.Process;
import com.fintech.orion.dataabstraction.entities.orion.ProcessingRequest;
import com.fintech.orion.dataabstraction.exceptions.ItemNotFoundException;
import com.fintech.orion.dto.hermese.ResponseProcessorResult;
import com.fintech.orion.dto.messaging.ProcessingMessage;
import com.fintech.orion.hermesagentservices.license.LicenseHandlerInterface;
import com.fintech.orion.hermesagentservices.processor.OracleRequestProcessor;
import com.fintech.orion.hermesagentservices.response.processor.HermeseResponseProcessor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.concurrent.*;


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
    private LicenseHandlerInterface licenseHandler;


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

        if (oracleResults != null) {
            try {
                LOGGER.debug("received results {}", oracleResults.get());
                LOGGER.debug("Elapsed time to complete the processing : " + (System.currentTimeMillis() - start));
                String rawString = objectMapper.writeValueAsString(oracleResults.get());
                processResponse(processingMessage, rawString);

            } catch (InterruptedException e) {
                LOGGER.error("Verification process orchestration interrupted ", e);
            } catch (ExecutionException e) {
                LOGGER.error("Verification process execution occurred ", e);
            } catch (JsonProcessingException e) {
                LOGGER.error("Error occurred processing one or more json ", e);
            }
        } else {
            LOGGER.error("Error occurred while trying to process the request. oracle request processor async result " +
                    "received as null");
            processResponse(processingMessage, "");
        }
    }

    @Transactional
    private void processResponse(ProcessingMessage processingMessage, String rawString){
        try {
            ProcessingRequest processingRequest = verificationRequestDetailService.getProcessingRequest(processingMessage.getVerificationRequestCode());
            ResponseProcessorResult result = hermeseResponseProcessor.processAndUpdateRawResponse(rawString, processingRequest);
            licenseHandler.updateLicense(processingMessage.getClientLicense(), rawString);
            List<Process> processList = verificationRequestDetailService
                    .getProcessListBelongsToProcessingRequest(processingMessage.getVerificationRequestCode());
            for (Process process : processList){
                saveProcessResponse(rawString, result.getProcessedString(), process);
            }
        }catch (ItemNotFoundException e) {
            LOGGER.error("Failed to find required data to complete the processing ", e);
        }catch (HermeseResponseprocessorException e) {
            LOGGER.error("Error while processing the response ", e);
        } catch (LicenseHandlerException e) {
            LOGGER.error("Unable to update the license with license key {}", processingMessage.getClientLicense(), e);
        }

    }

    @Transactional
    private void saveProcessResponse(String rawResponse, String processedString, Process process){
        verificationRequestDetailService.saveResponse(rawResponse, processedString, process);
    }

}
