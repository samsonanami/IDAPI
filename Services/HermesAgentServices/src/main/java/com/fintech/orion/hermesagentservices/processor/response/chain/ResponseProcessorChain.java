package com.fintech.orion.hermesagentservices.processor.response.chain;

import com.fintech.orion.dto.response.api.VerificationProcessDetailedResponse;
import com.fintech.orion.hermesagentservices.processor.VerificationResult;

import java.util.List;

/**
 * Created by sasitha on 2/17/17.
 */
public abstract class ResponseProcessorChain {

    private ResponseProcessorChain next;

    public void setNext(ResponseProcessorChain chain){
        this.next = chain;
    }

    public final void start(VerificationProcessDetailedResponse response,
                            List<VerificationResult> verificationResults,
                            String processingRequestId){
        execute(response, verificationResults, processingRequestId);
        if (next != null){
            next.start(response, verificationResults, processingRequestId);
        }
    }

    protected abstract void execute(VerificationProcessDetailedResponse response,
                                    List<VerificationResult> verificationResults,
                                    String processingRequestId);
}
