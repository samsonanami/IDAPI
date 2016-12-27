package com.fintech.orion.hermesagentservices.response.processor;

import com.fintech.orion.common.exceptions.HermeseResponseprocessorException;
import com.fintech.orion.dataabstraction.entities.orion.ProcessingRequest;
import com.fintech.orion.dto.hermese.ResponseProcessorResult;

/**
 * Created by sasitha on 12/20/16.
 *
 */
public interface HermeseResponseProcessorInterface {

    ResponseProcessorResult processAndUpdateRawResponse(String rawResponse, ProcessingRequest processingRequest) throws HermeseResponseprocessorException;
}
