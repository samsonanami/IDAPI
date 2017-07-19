package com.fintech.orion.dto.hermese.model.compressionlabs.response;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Created by sasitha on 2/18/17.
 *
 */
public class FacialVerificationResponse {

    private double confidence;
    @JsonProperty("commands_followed")
    private int commandsFollowed;

    public double getConfidence() {
        return confidence;
    }

    public void setConfidence(double confidence) {
        this.confidence = confidence;
    }

    public int getCommandsFollowed() {
        return commandsFollowed;
    }

    public void setCommandsFollowed(int commandsFollowed) {
        this.commandsFollowed = commandsFollowed;
    }

    @Override
    public String toString() {
        return "FacialVerificationResponse{" +
                "confidence=" + confidence +
                ", commandsFollowed=" + commandsFollowed +
                '}';
    }
}
