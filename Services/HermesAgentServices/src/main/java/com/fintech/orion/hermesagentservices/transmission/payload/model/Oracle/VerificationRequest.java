package com.fintech.orion.hermesagentservices.transmission.payload.model.Oracle;

import java.util.List;

/**
 * Created by sasitha on 12/19/16.
 *
 */
public class VerificationRequest {

    private List<VerificationProcess> verificationProcessList;

    public List<VerificationProcess> getVerificationProcessList() {
        return verificationProcessList;
    }

    public void setVerificationProcessList(List<VerificationProcess> verificationProcessList) {
        this.verificationProcessList = verificationProcessList;
    }
}
