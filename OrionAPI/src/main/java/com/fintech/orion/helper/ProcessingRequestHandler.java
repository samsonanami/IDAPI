package com.fintech.orion.helper;

import com.fintech.orion.coreservices.*;
import com.fintech.orion.dataabstraction.entities.orion.*;
import com.fintech.orion.dataabstraction.entities.orion.Process;
import com.fintech.orion.dataabstraction.entities.orion.ProcessingRequest;
import com.fintech.orion.dataabstraction.entities.orion.Resource;
import com.fintech.orion.dataabstraction.exceptions.ItemNotFoundException;
import com.fintech.orion.dataabstraction.models.*;
import org.springframework.beans.factory.annotation.Autowired;

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

    @Override
    public String saveData(String accessToken, List<VerificationProcess> verificationProcessList) throws ItemNotFoundException {
        Client client = clientServiceInterface.getClientByAuthToken(accessToken);

        ProcessingRequest processingRequest = processingRequestServiceInterface.saveProcessingRequest(client);
        processingRequest = processingRequestServiceInterface.getProcessingRequestById(processingRequest.getId());

        for(VerificationProcess v : verificationProcessList) {
            ProcessType processType = processTypeServiceInterface.getProcessTypeByName(v.getVerificationProcessType());
            Process process = processServiceInterface.saveProcess(processType, processingRequest);
            for(com.fintech.orion.dataabstraction.models.Resource r : v.getResources()) {
                Resource resource = resourceServiceInterface.getResourceByIdentificationCode(r.getResourceId());
                processResourceServiceInterface.saveProcessResource(process, resource, r.getResourceName());
            }
        }
        return processingRequest.getProcessingRequestIdentificationCode();
    }
}
