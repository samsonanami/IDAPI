package com.fintech.orion.hermesagentservices.transmission.payload.model.Oracle;

import java.util.List;

/**
 * Created by sasitha on 12/19/16.
 *
 */
public class VerificationProcess {

    private String verificationProcessType;
    private List<Resource> resourceList;

    public String getVerificationProcessType() {
        return verificationProcessType;
    }

    public void setVerificationProcessType(String verificationProcessType) {
        this.verificationProcessType = verificationProcessType;
    }

    public List<Resource> getResourceList() {
        return resourceList;
    }

    public void setResourceList(List<Resource> resourceList) {
        this.resourceList = resourceList;
    }
}
