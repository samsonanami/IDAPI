package com.fintech.orion.dto.hermese.model.compressionlabs.response;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Created by sasitha on 2/18/17.
 *
 */
public class FacialVerificationResponse {

    @JsonProperty("result")
    private String result;

    @JsonProperty("selfie_frame")
    private String selfie_frame;

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public String getSelfie_frame() {
        return selfie_frame;
    }

    public void setSelfie_frame(String selfie_frame) {
        this.selfie_frame = selfie_frame;
    }

    @Override
    public String toString() {
        return "FacialVerificationResponse{" +
                "result=" + result +
                ", selfie_frame=" + selfie_frame +
                '}';
    }
}
