package com.fintech.orion.hermes.orchestrator;

import com.fintech.orion.common.service.VerificationRequestDetailServiceInterface;
import com.fintech.orion.dataabstraction.entities.orion.ProcessingRequest;
import com.fintech.orion.dataabstraction.exceptions.ItemNotFoundException;
import com.fintech.orion.dto.messaging.ProcessingMessage;
import com.fintech.orion.hermesagentservices.processor.OracleRequestProcessor;
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
    private VerificationRequestDetailServiceInterface verificationRequestDetailService;


    public void orchestrate(Object message){
        long start = System.currentTimeMillis();
        LOGGER.debug("Start orchestrating message {} ", message);
        ProcessingMessage processingMessage = (ProcessingMessage)message;
        try {
            ProcessingRequest processingRequest = verificationRequestDetailService.getProcessingRequest(processingMessage.getVerificationRequestCode());

            Future<Object> oracleResults = oracleRequestProcessor.process(processingRequest);

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
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (ExecutionException e) {
                e.printStackTrace();
            }

        } catch (ItemNotFoundException e) {
            e.printStackTrace();
        }

    }

}
