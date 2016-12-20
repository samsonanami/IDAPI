package com.fintech.orion.common.service;

import com.fintech.orion.dataabstraction.entities.orion.Client;
import com.fintech.orion.dataabstraction.entities.orion.Process;
import com.fintech.orion.dataabstraction.entities.orion.ProcessingRequest;
import com.fintech.orion.dataabstraction.entities.orion.Response;
import com.fintech.orion.dataabstraction.exceptions.ItemNotFoundException;
import com.fintech.orion.dataabstraction.repositories.ClientRepositoryInterface;
import com.fintech.orion.dataabstraction.repositories.ProcessRepositoryInterface;
import com.fintech.orion.dataabstraction.repositories.ProcessingRequestRepositoryInterface;
import com.fintech.orion.dataabstraction.repositories.ResponseRepositoryInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by sasitha on 12/19/16.
 *
 */
@Service
public class VerificationRequestDetailService implements VerificationRequestDetailServiceInterface{

    @Autowired
    private ProcessingRequestRepositoryInterface processingRequestRepositoryInterface;

    @Autowired
    private ProcessRepositoryInterface processRepositoryInterface;

    @Autowired
    private ClientRepositoryInterface clientRepositoryInterface;

    @Autowired
    private ResponseRepositoryInterface responseRepositoryInterface;

    @Override
    @Transactional
    public ProcessingRequest getProcessingRequest(String processingRequestId) throws ItemNotFoundException {
        ProcessingRequest p = processingRequestRepositoryInterface.findProcessingRequestByProcessingRequestIdentificationCode(processingRequestId);
        if (p == null){
            throw new ItemNotFoundException("No processing request found with processing request identification code : " + processingRequestId);
        }
        return p;
    }

    @Override
    @Transactional
    public void saveRawResponse(String rawResponse, Process process) {
        Response response = new Response();
        response.setProcess(process);
        response.setRawJson(rawResponse);

        responseRepositoryInterface.save(response);
    }
}
