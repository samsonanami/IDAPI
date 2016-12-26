package com.fintech.orion.dto.hermese;

/**
 * Created by sasitha on 12/26/16.
 */
public class ResponseProcessorResult {
    private String processedString;
    private boolean finalProcessingStatus;


    public String getProcessedString() {
        return processedString;
    }

    public void setProcessedString(String processedString) {
        this.processedString = processedString;
    }

    public boolean isFinalProcessingStatus() {
        return finalProcessingStatus;
    }

    public void setFinalProcessingStatus(boolean finalProcessingStatus) {
        this.finalProcessingStatus = finalProcessingStatus;
    }
}
