package com.fintech.orion.api.service.request;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fintech.orion.api.service.exceptions.DataNotFoundException;
import com.fintech.orion.api.service.exceptions.ResourceAccessPolicyViolationException;
import com.fintech.orion.api.service.exceptions.ResourceNotFoundException;
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
import org.springframework.util.Assert;

import java.io.IOException;
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

    @Transactional(rollbackFor = {ItemNotFoundException.class, DataNotFoundException.class})
    @Override
    public String saveVerificationProcessData(String clientName, List<VerificationProcess> verificationProcessList) throws DataNotFoundException {
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

            updateResources(v.getResources(), process);

        }
        return processingRequest.getProcessingRequestIdentificationCode();
    }

    @Transactional
    private void updateResources(List<Resource> resourceList, Process process) throws DataNotFoundException {
        for (Resource r: resourceList){
            com.fintech.orion.dataabstraction.entities.orion.Resource resourceEntity = resourceRepositoryInterface.findResourceByResourceIdentificationCode(r.getResourceId());
            if (resourceEntity == null){
                throw new DataNotFoundException("No resource found with resource identification code :" +
                        r.getResourceId() + " and could not be associated with resource name  : " +
                        r.getResourceName() + " to complete the processing.");
            }
            ResourceName resourceName = resourceNameRepositoryInterface.findResourceNameByName(r.getResourceName());
            resourceEntity.setProcess(process);
            resourceEntity.setResourceName(resourceName);
            resourceRepositoryInterface.save(resourceEntity);
        }
    }

    @Override
    @Transactional
    public VerificationProcessDetailedResponse getDetailedResponse(String clientName, String verificationRequestId) throws IOException, ResourceAccessPolicyViolationException, ResourceNotFoundException {

        Client client = clientRepositoryInterface.findClientByUserName(clientName);
        ProcessingRequest processingRequest = processingRequestRepositoryInterface.findProcessingRequestByProcessingRequestIdentificationCode(verificationRequestId);
        if (processingRequest == null){
            throw new ResourceNotFoundException("No processing request found with identification code : " + verificationRequestId);
        }
        if(!processingRequest.getClient().getUserName().equals(client.getUserName())){
            throw new ResourceAccessPolicyViolationException("Accessing content not belong to the requested user is denied by resource access policy.");
        }
        ObjectMapper objectMapper = new ObjectMapper();
        VerificationProcessDetailedResponse response = new VerificationProcessDetailedResponse();
        Process process = processingRequest.getProcesses().iterator().next();
        if(process.getResponse() != null){
            String processedJson = process.getResponse().getExtractedJson();
            response = objectMapper.readValue(processedJson, VerificationProcessDetailedResponse.class);
        }else {
            response.setVerificationRequestId(verificationRequestId);
            response.setStatus("Pending");
        }
        return response;
    }
}
