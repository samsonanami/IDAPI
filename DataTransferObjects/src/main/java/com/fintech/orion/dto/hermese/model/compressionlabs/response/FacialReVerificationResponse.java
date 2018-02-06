package com.fintech.orion.dto.hermese.model.compressionlabs.response;

public class FacialReVerificationResponse {

    private String livenessStatus;
    private String faceMatchStatus;

    public String getLivenessStatus() {
        return livenessStatus;
    }

    public void setLivenessStatus(String livenessStatus) {
        this.livenessStatus = livenessStatus;
    }

    public String getFaceMatchStatus() {
        return faceMatchStatus;
    }

    public void setFaceMatchStatus(String faceMatchStatus) {
        this.faceMatchStatus = faceMatchStatus;
    }
}
