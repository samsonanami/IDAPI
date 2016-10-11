package com.fintech.orion.dto.messaging;

/**
 * Created by TharinduMP on 10/10/2016.
 * Generic Message sent through the messaging queue
 */
public class GenericMapMessage {

    private Integer processingRequestId;

    private Integer clientId;

    public Integer getProcessingRequestId() {
        return processingRequestId;
    }

    public void setProcessingRequestId(Integer processingRequestId) {
        this.processingRequestId = processingRequestId;
    }

    public Integer getClientId() {
        return clientId;
    }

    public void setClientId(Integer clientId) {
        this.clientId = clientId;
    }
}
