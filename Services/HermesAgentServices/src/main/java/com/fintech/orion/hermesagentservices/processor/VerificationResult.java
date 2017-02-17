package com.fintech.orion.hermesagentservices.processor;

import com.fintech.orion.common.Processor;

/**
 * Created by sasitha on 2/16/17.
 *
 */
public class VerificationResult {
    private Processor processor;
    private String resultString;

    public Processor getProcessor() {
        return processor;
    }

    public void setProcessor(Processor processor) {
        this.processor = processor;
    }

    public String getResultString() {
        return resultString;
    }

    public void setResultString(String resultString) {
        this.resultString = resultString;
    }
}
