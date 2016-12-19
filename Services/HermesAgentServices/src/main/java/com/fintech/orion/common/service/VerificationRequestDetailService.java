package com.fintech.orion.common.service;

import com.fintech.orion.dataabstraction.entities.orion.Client;
import com.fintech.orion.dataabstraction.entities.orion.ProcessingRequest;
import com.fintech.orion.dataabstraction.exceptions.ItemNotFoundException;
import com.fintech.orion.dataabstraction.repositories.ClientRepositoryInterface;
import com.fintech.orion.dataabstraction.repositories.ProcessingRequestRepositoryInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by sasitha on 12/19/16.
 *
 */
@Service
public class VerificationRequestDetailService implements VerificationRequestDetailServiceInterface{

    @Autowired
    private ProcessingRequestRepositoryInterface processingRequestRepositoryInterface;

    @Autowired
    private ClientRepositoryInterface clientRepositoryInterface;

    @Override
    public ProcessingRequest getProcessingRequest(String processingRequestId) throws ItemNotFoundException {
        ProcessingRequest p = processingRequestRepositoryInterface.findOne(10);
        Client client = clientRepositoryInterface.findClientByUserName("zone-admin");
        return p;
    }
}
