package com.fintech.orion.api.service.request;

import com.fintech.orion.dataabstraction.entities.orion.*;
import com.fintech.orion.dataabstraction.entities.orion.Process;
import com.fintech.orion.dataabstraction.exceptions.ItemNotFoundException;
import com.fintech.orion.dataabstraction.models.Status;
import com.fintech.orion.dataabstraction.repositories.*;
import com.fintech.orion.dto.request.api.Resource;
import com.fintech.orion.dto.request.api.VerificationProcess;
import com.fintech.orion.dto.response.api.VerificationProcessDetailedResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;
import java.util.UUID;

public class ProcessingRequestService implements ProcessingRequestServiceInterface {

    @Autowired
    private ClientRepositoryInterface clientRepositoryInterface;

    @Autowired
    private ProcessingRequestRepositoryInterface processingRequestRepositoryInterface;

    @Autowired
    private ProcessingStatusRepositoryInterface processingStatusRepositoryInterface;

    @Autowired
    private ProcessRepositoryInterface processRepositoryInterface;

    @Autowired
    private ResourceRepositoryInterface resourceRepositoryInterface;

    @Autowired
    private ResourceNameRepositoryInterface resourceNameRepositoryInterface;

    @Autowired
    private ProcessTypeRepositoryInterface processTypeRepositoryInterface;

    @Transactional(rollbackFor = ItemNotFoundException.class)
    @Override
    public String saveVerificationProcessData(String clientName, List<VerificationProcess> verificationProcessList) {
        Client client = clientRepositoryInterface.findClientByUserName(clientName);

        ProcessingRequest processingRequest = new ProcessingRequest();
        processingRequest.setClient(client);
        processingRequest.setReceivedOn(new Date());
        processingRequest.setProcessingRequestIdentificationCode(UUID.randomUUID().toString());
        processingRequestRepositoryInterface.save(processingRequest);

        ProcessingStatus initialStatus = processingStatusRepositoryInterface.findProcessingStatusByStatus(Status.PROCESSING_REQUESTED.getStatus());

        for (VerificationProcess v : verificationProcessList){
            Process process = new Process();
            process.setProcessIdentificationCode(UUID.randomUUID().toString());
            process.setProcessingRequest(processingRequest);
            process.setProcessingStatus(initialStatus);
            process.setProcessType(processTypeRepositoryInterface.findProcessTypeByType(v.getVerificationProcessType()));
            processRepositoryInterface.save(process);

            for (Resource r: v.getResources()){
                com.fintech.orion.dataabstraction.entities.orion.Resource resourceEntity = resourceRepositoryInterface.findResourceByResourceIdentificationCode(r.getResourceId());
                ResourceName resourceName = resourceNameRepositoryInterface.findResourceNameByName(r.getResourceName());
                resourceEntity.setProcess(process);
                resourceEntity.setResourceName(resourceName);
                resourceRepositoryInterface.save(resourceEntity);
            }

        }
        return processingRequest.getProcessingRequestIdentificationCode();
    }

    @Override
    public VerificationProcessDetailedResponse getDetailedResponse(String accessToken, String verificationRequestId) throws ItemNotFoundException {
        return null;
    }
}
