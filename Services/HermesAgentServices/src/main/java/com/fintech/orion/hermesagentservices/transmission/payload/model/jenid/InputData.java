package com.fintech.orion.hermesagentservices.transmission.payload.model.jenid;

import java.util.List;

/**
 * Created by TharinduMP on 10/19/2016.
 * InputData Model
 */
public class InputData {

    private String description;
    private String clientID;
    private List<InputImage> inputImages;

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getClientID() {
        return clientID;
    }

    public void setClientID(String clientID) {
        this.clientID = clientID;
    }

    public List<InputImage> getInputImages() {
        return inputImages;
    }

    public void setInputImages(List<InputImage> inputImages) {
        this.inputImages = inputImages;
    }
}
