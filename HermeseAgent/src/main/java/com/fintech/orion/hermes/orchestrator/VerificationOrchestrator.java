package com.fintech.orion.hermes.orchestrator;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fintech.orion.common.exceptions.request.RequestProcessorException;
import com.fintech.orion.common.service.VerificationRequestDetailService;
import com.fintech.orion.common.service.VerificationRequestDetailServiceInterface;
import com.fintech.orion.dataabstraction.entities.orion.Process;
import com.fintech.orion.dataabstraction.entities.orion.ProcessingRequest;
import com.fintech.orion.dataabstraction.exceptions.ItemNotFoundException;
import com.fintech.orion.dto.messaging.ProcessingMessage;
import com.fintech.orion.hermesagentservices.processor.OracleRequestProcessor;
import com.fintech.orion.hermesagentservices.transmission.payload.model.Oracle.response.OcrResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

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
            e.printStackTrace();
        }

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
            LOGGER.debug("Elapsed time : " + (System.currentTimeMillis() - start));
            ProcessingRequest processingRequest = verificationRequestDetailService.getProcessingRequest(processingMessage.getVerificationRequestCode());
            for (Process process : processingRequest.getProcesses()){
                switch (process.getProcessType().getType()){
                    case "idVerification":
                        saveRawResponseOfProcess(objectMapper.writeValueAsString(oracleResults.get()), process);
                        break;
                    case "addressVerification" :
                        saveRawResponseOfProcess(objectMapper.writeValueAsString(oracleResults.get()), process);
                        break;
                }
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (ItemNotFoundException e) {
            e.printStackTrace();
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }

    }

    @Transactional
    private void saveRawResponseOfProcess(String rawResponse, Process process){
        verificationRequestDetailService.saveRawResponse(rawResponse, process);
    }

}
