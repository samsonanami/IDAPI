package com.fintech.orion.coreservices;

import com.fintech.orion.common.AbstractService;
import com.fintech.orion.dataabstraction.entities.orion.Client;
import com.fintech.orion.dataabstraction.entities.orion.ProcessingRequest;
import com.fintech.orion.dataabstraction.exceptions.ItemNotFoundException;
import com.fintech.orion.dataabstraction.helper.GenerateTimestamp;
import com.fintech.orion.dataabstraction.helper.GenerateUUID;
import com.fintech.orion.dataabstraction.repositories.ProcessingRequestRepositoryInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * ProcessingRequest entity service class
 */
@Service
public class ProcessingRequestService extends AbstractService<ProcessingRequest, Integer> implements ProcessingRequestServiceInterface {

    @Autowired
    private ProcessingRequestRepositoryInterface processingRequestRepositoryInterface;

    @Transactional
    @Override
    public ProcessingRequest save(Client client) {
        ProcessingRequest processingRequest = new ProcessingRequest();
        processingRequest.setReceivedOn(GenerateTimestamp.timestamp());
        processingRequest.setClient(client);
        processingRequest.setProcessingRequestIdentificationCode(GenerateUUID.uuidNumber());
        processingRequestRepositoryInterface.saveOrUpdate(processingRequest);
        return processingRequest;
    }

    @Transactional
    @Override
    public ProcessingRequest findByIdIdentificationCode(String identificationCode) throws ItemNotFoundException {
        return processingRequestRepositoryInterface.findByIdIdentificationCode(identificationCode);
    }
}
