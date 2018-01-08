package com.fintech.orion.hermesagentservices.processor.response;

import com.fintech.orion.dto.hermese.ResponseProcessorResult;
import com.fintech.orion.hermesagentservices.processor.VerificationResult;

import java.util.List;

/**
 * Created by sasitha on 2/17/17.
 *
 */
public interface HermesResponseProcessorInterface {

    ResponseProcessorResult processVerificationResults(List<VerificationResult> verificationResults,
                                                       String processingRequestId, boolean isReVerification,
                                                       String reVerificationStatus);
}
