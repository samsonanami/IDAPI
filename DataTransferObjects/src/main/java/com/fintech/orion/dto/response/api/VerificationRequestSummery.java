package com.fintech.orion.dto.response.api;

import java.util.Date;

/**
 * Created by sasitha on 3/1/17.
 *
 */
public class VerificationRequestSummery {

    private Date requestedDate;
    private String processedString;
    private String finalVerificationStatus;
    private String requestIdentificationCode;
    private Date processingCompletedOn;
    private String clientName;

    public Date getRequestedDate() {
        return requestedDate;
    }

    public void setRequestedDate(Date requestedDate) {
        this.requestedDate = requestedDate;
    }

    public String getProcessedString() {
        return processedString;
    }

    public void setProcessedString(String processedString) {
        this.processedString = processedString;
    }

    public String getFinalVerificationStatus() {
        return finalVerificationStatus;
    }

    public void setFinalVerificationStatus(String finalVerificationStatus) {
        this.finalVerificationStatus = finalVerificationStatus;
    }

    public String getRequestIdentificationCode() {
        return requestIdentificationCode;
    }

    public void setRequestIdentificationCode(String requestIdentificationCode) {
        this.requestIdentificationCode = requestIdentificationCode;
    }

    public Date getProcessingCompletedOn() {
        return processingCompletedOn;
    }

    public void setProcessingCompletedOn(Date processingCompletedOn) {
        this.processingCompletedOn = processingCompletedOn;
    }

    public String getClientName() {
        return clientName;
    }

    public void setClientName(String clientName) {
        this.clientName = clientName;
    }

}
