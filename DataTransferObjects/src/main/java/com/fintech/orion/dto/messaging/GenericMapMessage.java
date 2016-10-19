package com.fintech.orion.dto.messaging;

/**
 * Created by TharinduMP on 10/10/2016.
 * Generic Message sent through the messaging queue
 */
public class GenericMapMessage {

    private String identificationCode;
    private Integer clientId;

    public String getIdentificationCode() {
        return identificationCode;
    }

    public void setIdentificationCode(String identificationCode) {
        this.identificationCode = identificationCode;
    }

    public Integer getClientId() {
        return clientId;
    }

    public void setClientId(Integer clientId) {
        this.clientId = clientId;
    }
}
