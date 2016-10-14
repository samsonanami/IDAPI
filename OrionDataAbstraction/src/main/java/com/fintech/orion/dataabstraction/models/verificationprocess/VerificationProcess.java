package com.fintech.orion.dataabstraction.models.verificationprocess;

import java.util.List;

public class VerificationProcess {
    private String verificationProcessType;
    private List<Resource> resources;

    public String getVerificationProcessType() {
        return verificationProcessType;
    }

    public void setVerificationProcessType(String verificationProcessType) {
        this.verificationProcessType = verificationProcessType;
    }

    public List<Resource> getResources() {
        return resources;
    }

    public void setResources(List<Resource> resources) {
        this.resources = resources;
    }
}
