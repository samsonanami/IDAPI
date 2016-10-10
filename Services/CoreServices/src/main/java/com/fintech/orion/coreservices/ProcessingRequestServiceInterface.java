package com.fintech.orion.coreservices;

import com.fintech.orion.dataabstraction.entities.orion.Client;
import com.fintech.orion.dataabstraction.entities.orion.ProcessingRequest;
import com.fintech.orion.dataabstraction.exceptions.ItemNotFoundException;

import java.util.List;

public interface ProcessingRequestServiceInterface {
    List<ProcessingRequest> getProcessingRequestList();

    ProcessingRequest getProcessingRequestById(int id) throws ItemNotFoundException;

    void saveProcessingRequest(ProcessingRequest processingRequest);

    ProcessingRequest saveProcessingRequest(Client client);

    void updateProcessingRequest(ProcessingRequest processingRequest);

    boolean deleteProcessingRequestById(int id) throws ItemNotFoundException;

    void deleteProcessingRequest(ProcessingRequest processingRequest);

    ProcessingRequest getProcessingRequestByIdentificationCode(String identificationCode) throws ItemNotFoundException;
}
