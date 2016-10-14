package com.fintech.orion.dataabstraction.models.verificationresult;

import java.util.List;

public class VerificationRequest {

    private String verificationRequestId;
    private List<VerificationProcess> verificationProcesses;

    public String getVerificationRequestId() {
        return verificationRequestId;
    }

    public void setVerificationRequestId(String verificationRequestId) {
        this.verificationRequestId = verificationRequestId;
    }

    public List<VerificationProcess> getVerificationProcesses() {
        return verificationProcesses;
    }

    public void setVerificationProcesses(List<VerificationProcess> verificationProcesses) {
        this.verificationProcesses = verificationProcesses;
    }
}
