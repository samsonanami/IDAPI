package com.fintech.orion.common;

import com.fintech.orion.common.Processor;
import com.fintech.orion.hermesagentservices.processor.request.processor.RequestProcessor;

import java.util.List;

/**
 * Created by sasitha on 2/16/17.
 *
 *
 */
public class ProcessConfiguration {

    private Processor processor;
    private RequestProcessor requestProcessor;
    private List<String> verificationProcessList;

    public List<String> getVerificationProcessList() {
        return verificationProcessList;
    }

    public void setVerificationProcessList(List<String> verificationProcessList) {
        this.verificationProcessList = verificationProcessList;
    }

    public Processor getProcessor() {
        return processor;
    }

    public void setProcessor(Processor processor) {
        this.processor = processor;
    }

    public RequestProcessor getRequestProcessor() {
        return requestProcessor;
    }

    public void setRequestProcessor(RequestProcessor requestProcessor) {
        this.requestProcessor = requestProcessor;
    }
}
