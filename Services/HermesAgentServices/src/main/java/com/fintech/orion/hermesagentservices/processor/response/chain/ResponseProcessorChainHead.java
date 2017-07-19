package com.fintech.orion.hermesagentservices.processor.response.chain;

import com.fintech.orion.dto.response.api.VerificationProcessDetailedResponse;
import com.fintech.orion.hermesagentservices.processor.VerificationResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

public class ResponseProcessorChainHead extends ResponseProcessorChain{

    private static final Logger LOGGER = LoggerFactory.getLogger(ResponseProcessorChainHead.class);
    @Override
    protected void execute(VerificationProcessDetailedResponse response, List<VerificationResult> verificationResults, String processingRequestId) {
        LOGGER.debug("Response processor chain start processing response for {} " , processingRequestId);
    }
}
