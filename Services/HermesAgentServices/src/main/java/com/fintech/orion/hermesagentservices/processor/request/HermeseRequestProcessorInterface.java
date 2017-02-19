package com.fintech.orion.hermesagentservices.processor.request;

import com.fintech.orion.common.exceptions.request.RequestProcessorException;
import com.fintech.orion.dto.messaging.ProcessingMessage;
import com.fintech.orion.hermesagentservices.processor.VerificationResult;

import java.util.List;

/**
 * Created by sasitha on 2/16/17.
 *
 */
public interface HermeseRequestProcessorInterface {

    List<VerificationResult> processVerificationRequest(ProcessingMessage processingMessage) throws RequestProcessorException;
}
