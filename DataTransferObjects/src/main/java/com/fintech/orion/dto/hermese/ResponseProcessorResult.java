package com.fintech.orion.dto.hermese;

/**
 * Created by sasitha on 12/26/16.
 */
public class ResponseProcessorResult {
    private String processedString;
    private boolean finalProcessingStatus;
    private String clientName;


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

    public String getClientName() {
        return clientName;
    }

    public void setClientName(String clientName) {
        this.clientName = clientName;
    }
    
}
