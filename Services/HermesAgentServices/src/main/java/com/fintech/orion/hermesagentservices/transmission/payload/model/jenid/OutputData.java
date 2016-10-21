package com.fintech.orion.hermesagentservices.transmission.payload.model.jenid;

/**
 * Created by TharinduMP on 10/21/2016.
 */
public class OutputData {

    private String id;
    private Number processingSeconds;
    private String resultString;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Number getProcessingSeconds() {
        return processingSeconds;
    }

    public void setProcessingSeconds(Number processingSeconds) {
        this.processingSeconds = processingSeconds;
    }

    public String getResultString() {
        return resultString;
    }

    public void setResultString(String resultString) {
        this.resultString = resultString;
    }
}
