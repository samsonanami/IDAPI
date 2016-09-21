package com.fintech.orion.coreservices;

import com.fintech.orion.dataabstraction.entities.orion.ProcessingRequest;
import com.fintech.orion.dataabstraction.exceptions.ItemNotFoundException;

import java.util.List;

/**
 * Created by ChathurangaRW on 9/14/2016.
 */
public interface ProcessingRequestServiceInterface {
    List<ProcessingRequest> getProcessingRequestList();

    ProcessingRequest getProcessingRequestById(int id) throws ItemNotFoundException;

    void saveProcessingRequest(ProcessingRequest processingRequest);

    void updateProcessingRequest(ProcessingRequest processingRequest);

    boolean deleteProcessingRequestById(int id) throws ItemNotFoundException;

    void deleteProcessingRequest(ProcessingRequest processingRequest);
}
