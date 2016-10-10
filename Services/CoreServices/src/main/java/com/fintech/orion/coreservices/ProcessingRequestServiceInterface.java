package com.fintech.orion.coreservices;

import com.fintech.orion.dataabstraction.entities.orion.Client;
import com.fintech.orion.dataabstraction.entities.orion.ProcessingRequest;
import com.fintech.orion.dataabstraction.exceptions.ItemNotFoundException;

import java.util.List;

public interface ProcessingRequestServiceInterface {
    List<ProcessingRequest> getProcessingRequestList();

    ProcessingRequest getProcessingRequestById(int id) throws ItemNotFoundException;

    void saveOrUpdateProcessingRequest(ProcessingRequest processingRequest);

    ProcessingRequest saveProcessingRequest(Client client);

    boolean deleteProcessingRequestById(int id) throws ItemNotFoundException;

    void deleteProcessingRequest(ProcessingRequest processingRequest);

    ProcessingRequest getProcessingRequestByIdentificationCode(String identificationCode) throws ItemNotFoundException;
}
