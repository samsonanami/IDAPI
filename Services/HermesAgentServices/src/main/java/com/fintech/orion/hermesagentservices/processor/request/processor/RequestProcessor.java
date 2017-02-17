package com.fintech.orion.hermesagentservices.processor.request.processor;

import com.fintech.orion.common.exceptions.request.RequestProcessorException;
import org.springframework.scheduling.annotation.Async;

import java.util.concurrent.Future;

/**
 * Created by sasitha on 2/16/17.
 *
 */
public interface RequestProcessor {

    @Async
    Future<Object> processRequest(Object object) throws RequestProcessorException;
}
