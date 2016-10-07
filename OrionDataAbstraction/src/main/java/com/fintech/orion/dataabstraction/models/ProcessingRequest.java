package com.fintech.orion.dataabstraction.models;

import java.util.List;

public class ProcessingRequest {
    private List<VerificationProcess> verificationProcesses;

    public List<VerificationProcess> getVerificationProcesses() {
        return verificationProcesses;
    }

    public void setVerificationProcesses(List<VerificationProcess> verificationProcesses) {
        this.verificationProcesses = verificationProcesses;
    }
}
