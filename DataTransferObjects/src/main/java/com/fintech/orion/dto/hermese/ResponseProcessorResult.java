package com.fintech.orion.dto.hermese;

/**
 * Created by sasitha on 12/26/16.
 */
public class ResponseProcessorResult {
    private String processedString;
    private String finalProcessingStatus;
    private String clientName;


    public String getProcessedString() {
        return processedString;
    }

    public void setProcessedString(String processedString) {
        this.processedString = processedString;
    }

    public String getFinalProcessingStatus() {
        return finalProcessingStatus;
    }

    public void setFinalProcessingStatus(String finalProcessingStatus) {
        this.finalProcessingStatus = finalProcessingStatus;
    }

    public String getClientName() {
        return clientName;
    }

    public void setClientName(String clientName) {
        this.clientName = clientName;
    }
    
}
