package com.fintech.orion.helper;

import com.fintech.orion.dataabstraction.models.verificationprocess.ProcessingRequest;
import com.fintech.orion.dataabstraction.models.verificationprocess.Resource;
import com.fintech.orion.dataabstraction.models.verificationprocess.VerificationProcess;
import com.fintech.orion.dataabstraction.models.verificationresult.VerificationRequest;

import java.util.ArrayList;
import java.util.List;

public class ObjectCreator {

    private ObjectCreator() {}

    public static ProcessingRequest aProcessingRequest() {
        ProcessingRequest processingRequest = new ProcessingRequest();

        List<VerificationProcess> verificationProcessList = new ArrayList<>();
        VerificationProcess verificationProcess = new VerificationProcess();
        verificationProcess.setVerificationProcessType("idVerification");

        List<Resource> resourceList = new ArrayList<>();
        Resource resource1 = new Resource();
        resource1.setResourceId("12345");
        resource1.setResourceName("id");
        Resource resource2 = new Resource();
        resource2.setResourceId("23456");
        resource2.setResourceName("id");
        resourceList.add(resource1);
        resourceList.add(resource2);

        verificationProcess.setResources(resourceList);

        processingRequest.setVerificationProcesses(verificationProcessList);

        return processingRequest;
    }

    public static VerificationRequest aVerificationRequest() {
        VerificationRequest verificationRequest = new VerificationRequest();
        return verificationRequest;
    }

}
