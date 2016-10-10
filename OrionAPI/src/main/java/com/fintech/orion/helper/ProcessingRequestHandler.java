package com.fintech.orion.helper;

import com.fintech.orion.coreservices.*;
import com.fintech.orion.dataabstraction.entities.orion.*;
import com.fintech.orion.dataabstraction.entities.orion.Process;
import com.fintech.orion.dataabstraction.entities.orion.ProcessingRequest;
import com.fintech.orion.dataabstraction.entities.orion.Resource;
import com.fintech.orion.dataabstraction.exceptions.ItemNotFoundException;
import com.fintech.orion.dataabstraction.models.verificationprocess.VerificationProcess;
import com.fintech.orion.dataabstraction.models.verificationresult.VerificationRequest;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;

public class ProcessingRequestHandler implements ProcessingRequestHandlerInterface {

    @Autowired
    private ClientServiceInterface clientServiceInterface;

    @Autowired
    private ProcessingRequestServiceInterface processingRequestServiceInterface;

    @Autowired
    private ProcessServiceInterface processServiceInterface;

    @Autowired
    private ProcessTypeServiceInterface processTypeServiceInterface;

    @Autowired
    private ProcessResourceServiceInterface processResourceServiceInterface;

    @Autowired
    private ResourceServiceInterface resourceServiceInterface;

    @Autowired
    private ProcessingStatusServiceInterface processingStatusServiceInterface;

    @Override
    public String saveData(String accessToken, List<VerificationProcess> verificationProcessList) throws ItemNotFoundException {
        Client client = clientServiceInterface.getClientByAuthToken(accessToken);

        ProcessingRequest processingRequest = processingRequestServiceInterface.saveProcessingRequest(client);
        // TODO set correct processing status
        ProcessingStatus processingStatus = processingStatusServiceInterface.getProcessingStatusById(1);

        for(VerificationProcess v : verificationProcessList) {
            ProcessType processType = processTypeServiceInterface.getProcessTypeByName(v.getVerificationProcessType());
            Process process = processServiceInterface.saveProcess(processType, processingRequest, processingStatus);
            for(com.fintech.orion.dataabstraction.models.verificationprocess.Resource r : v.getResources()) {
                Resource resource = resourceServiceInterface.getResourceByIdentificationCode(r.getResourceId());
                processResourceServiceInterface.saveProcessResource(process, resource, r.getResourceName());
            }
        }
        return processingRequest.getProcessingRequestIdentificationCode();
    }

    @Override
    public VerificationRequest getData(String verificationRequestId) throws ItemNotFoundException {
        ProcessingRequest processingRequest = processingRequestServiceInterface.getProcessingRequestByIdentificationCode(verificationRequestId);

        VerificationRequest verificationRequest = new VerificationRequest();
        List<com.fintech.orion.dataabstraction.models.verificationresult.VerificationProcess> verificationProcessList = new ArrayList<>();
        verificationRequest.setVerificationRequestId(processingRequest.getProcessingRequestIdentificationCode());
        for(Process p : processingRequest.getProcesses()) {
            com.fintech.orion.dataabstraction.models.verificationresult.VerificationProcess verificationProcess =
                    new com.fintech.orion.dataabstraction.models.verificationresult.VerificationProcess();
            verificationProcess.setVerificationProcessId(p.getProcessIdentificationCode());
            verificationProcess.setStatus(p.getProcessingStatus().getStatus());
            // TODO handle response
            //verificationProcess.setData(p.getResponse().getRawJson());
            verificationProcessList.add(verificationProcess);
        }
        verificationRequest.setVerificationProcesses(verificationProcessList);
        return verificationRequest;
    }
}
