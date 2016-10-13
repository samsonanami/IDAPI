package com.fintech.orion.hermes.provider;

import com.fintech.orion.common.exceptions.ProcessProviderException;
import com.fintech.orion.coreservices.ProcessingRequestServiceInterface;
import com.fintech.orion.dataabstraction.entities.orion.Process;
import com.fintech.orion.dataabstraction.entities.orion.ProcessingRequest;
import com.fintech.orion.dataabstraction.exceptions.ItemNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by TharinduMP on 10/12/2016.
 * Provides necessary Processes
 */
public class ProcessProvider implements ProcessProviderInterface {

    @Autowired
    private ProcessingRequestServiceInterface processingRequestService;

    @Override
    public List<Process> getProcesses(int processingRequestId) throws ProcessProviderException {
        try {
            ProcessingRequest processingRequest = processingRequestService.getProcessingRequestById(processingRequestId);
            return new ArrayList<>(processingRequest.getProcesses());
        } catch (ItemNotFoundException e) {
            throw new ProcessProviderException(e);
        }
    }

}
