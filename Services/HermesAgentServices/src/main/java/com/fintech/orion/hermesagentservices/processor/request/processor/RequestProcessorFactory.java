package com.fintech.orion.hermesagentservices.processor.request.processor;

import com.fintech.orion.common.Processor;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Created by sasitha on 2/16/17.
 *
 */
public class RequestProcessorFactory {

    @Autowired
    private OracleRequestProcessor oracleRequestProcessor;

    public RequestProcessor getRequestProcessor(Processor processor) {
        RequestProcessor requestProcessor = null;
        if (processor.equals(Processor.ORACLE)){
            requestProcessor = oracleRequestProcessor;
        }
        return requestProcessor;
    }
}
