package com.fintech.orion.hermesagentservices.processor.request;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fintech.orion.common.Processor;
import com.fintech.orion.common.exceptions.request.RequestProcessorException;
import com.fintech.orion.common.service.VerificationRequestDetailServiceInterface;
import com.fintech.orion.dataabstraction.entities.orion.Process;
import com.fintech.orion.dto.messaging.ProcessingMessage;
import com.fintech.orion.hermesagentservices.processor.VerificationResult;
import com.fintech.orion.hermesagentservices.processor.request.processor.RequestProcessorFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

/**
 * Created by sasitha on 2/16/17.
 *
 */
@Component
public class HermeseRequestProcessor implements HermeseRequestProcessorInterface {
    private static final Logger LOGGER = LoggerFactory.getLogger(HermeseRequestProcessor.class);

    @Autowired
    private RequestProcessorFactory requestProcessorFactory;

    @Autowired
    private VerificationRequestDetailServiceInterface verificationRequestDetailService;

    @Override
    public List<VerificationResult> processVerificationRequest(ProcessingMessage processingMessage) throws RequestProcessorException {
        List<VerificationResult> verificationResults = new ArrayList<>();
        ObjectMapper objectMapper = new ObjectMapper();
        Future<Object> oracleResults = requestProcessorFactory.getRequestProcessor(Processor.ORACLE)
                .processRequest(processingMessage);

        Future<Object> compressionLabsResults = requestProcessorFactory.getRequestProcessor(Processor.COMPRESSION_LABS)
                .processRequest(processingMessage);

        while (!(oracleResults.isDone() && compressionLabsResults.isDone())) {
            try {
                LOGGER.debug("Waiting for all the async task to be completed.");
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                throw new RequestProcessorException("Interpreted", e);
            }
        }

        try {
            VerificationResult oracleVerificationResult = null;
            if (oracleResults != null) {
                String oracleRawString = objectMapper.writeValueAsString(oracleResults.get());
                oracleVerificationResult = new VerificationResult();
                oracleVerificationResult.setProcessor(Processor.ORACLE);
                oracleVerificationResult.setResultString(oracleRawString);
                oracleVerificationResult.setLicense(processingMessage.getClientLicense());
                verificationResults.add(oracleVerificationResult);
            }

            VerificationResult facialVerificationResults = null;
            if (compressionLabsResults != null) {
                String facialVerificationRawResponse = objectMapper.writeValueAsString(compressionLabsResults.get());
                facialVerificationResults = new VerificationResult();
                facialVerificationResults.setProcessor(Processor.COMPRESSION_LABS);
                facialVerificationResults.setResultString(facialVerificationRawResponse);
                facialVerificationResults.setLicense(processingMessage.getClientLicense());
                verificationResults.add(facialVerificationResults);
            }

        } catch (JsonProcessingException e) {
            LOGGER.error("Error parsing the json from result {} ", oracleResults, e);
        } catch (InterruptedException e) {
            LOGGER.error("Async processing interrupted ", e);
        } catch (ExecutionException e) {
            LOGGER.error("Async process execution exception occurred ", e);
        }


        return verificationResults;
    }
}