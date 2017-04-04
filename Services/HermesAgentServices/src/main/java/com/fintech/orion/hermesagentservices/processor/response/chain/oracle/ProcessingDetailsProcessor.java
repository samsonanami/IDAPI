package com.fintech.orion.hermesagentservices.processor.response.chain.oracle;

import com.fintech.orion.common.service.VerificationRequestDetailServiceInterface;
import com.fintech.orion.dataabstraction.entities.orion.Process;
import com.fintech.orion.dto.response.api.VerificationProcessDetail;
import com.fintech.orion.dto.response.api.VerificationProcessDetailedResponse;
import com.fintech.orion.hermesagentservices.processor.VerificationResult;
import com.fintech.orion.hermesagentservices.processor.response.chain.ResponseProcessorChain;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;

public class ProcessingDetailsProcessor extends ResponseProcessorChain {

    @Autowired
    private VerificationRequestDetailServiceInterface verificationRequestDetailService;

    @Override
    protected void execute(VerificationProcessDetailedResponse response, List<VerificationResult> verificationResults,
                           String processingRequestId) {

        List<VerificationProcessDetail> processList = verificationRequestDetailService.getVerificationProcessDetails(processingRequestId);

        response.setVerificationProcessDetails(processList);
    }
}
