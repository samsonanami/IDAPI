package com.fintech.orion.hermesagentservices.transmission.payload.model.Oracle;

import java.util.List;

/**
 * Created by sasitha on 12/19/16.
 *
 */
public class VerificationRequest {

    private List<VerificationProcess> verificationProcesses;

    public List<VerificationProcess> getVerificationProcesses() {
        return verificationProcesses;
    }

    public void setVerificationProcesses(List<VerificationProcess> verificationProcesses) {
        this.verificationProcesses = verificationProcesses;
    }
}
