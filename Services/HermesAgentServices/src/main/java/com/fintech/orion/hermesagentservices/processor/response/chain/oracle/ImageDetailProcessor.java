package com.fintech.orion.hermesagentservices.processor.response.chain.oracle;

import com.fintech.orion.common.service.VerificationRequestDetailServiceInterface;
import com.fintech.orion.dto.response.api.ImageDetail;
import com.fintech.orion.dto.response.api.VerificationProcessDetailedResponse;
import com.fintech.orion.hermesagentservices.processor.VerificationResult;
import com.fintech.orion.hermesagentservices.processor.response.chain.ResponseProcessorChain;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Component
public class ImageDetailProcessor extends ResponseProcessorChain {

    private static final Logger LOGGER = LoggerFactory.getLogger(ImageDetailProcessor.class);

    @Autowired
    private VerificationRequestDetailServiceInterface verificationRequestDetailService;

    @Transactional
    @Override
    protected void execute(VerificationProcessDetailedResponse response, List<VerificationResult> verificationResults,
                           String processingRequestId) {

        List<ImageDetail> imageDetails = verificationRequestDetailService.getResourceOfProcessingRequest(processingRequestId);

        response.setImageDetails(imageDetails);
    }
}
