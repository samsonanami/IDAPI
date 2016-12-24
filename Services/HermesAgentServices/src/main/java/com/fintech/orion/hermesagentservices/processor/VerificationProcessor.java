package com.fintech.orion.hermesagentservices.processor;


import com.fintech.orion.common.exceptions.request.RequestProcessorException;
import org.springframework.scheduling.annotation.Async;

import java.util.concurrent.Future;

/**
 * Created by sasitha on 12/19/16.
 *
 */
public interface VerificationProcessor {

    @Async
    Future<Object> process(Object object) throws RequestProcessorException;
}
