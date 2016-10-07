package com.fintech.orion.coreservices;

import com.fintech.orion.dataabstraction.entities.orion.Client;
import com.fintech.orion.dataabstraction.entities.orion.ProcessingRequest;
import com.fintech.orion.dataabstraction.exceptions.ItemNotFoundException;
import com.fintech.orion.dataabstraction.helper.GenerateTimestamp;
import com.fintech.orion.dataabstraction.helper.GenerateUUID;
import com.fintech.orion.dataabstraction.models.VerificationProcess;
import com.fintech.orion.dataabstraction.repositories.ProcessingRequestRepositoryInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import sun.security.provider.VerificationProvider;

import java.util.List;

@Service
public class ProcessingRequestService implements ProcessingRequestServiceInterface {

    @Autowired
    private ProcessingRequestRepositoryInterface processingRequestRepositoryInterface;

    @Autowired
    private ClientServiceInterface clientServiceInterface;

    @Transactional
    @Override
    public List<ProcessingRequest> getProcessingRequestList() {
        return processingRequestRepositoryInterface.getAll();
    }

    @Transactional
    @Override
    public ProcessingRequest getProcessingRequestById(int id) throws ItemNotFoundException {
        ProcessingRequest processingRequest = processingRequestRepositoryInterface.findById(id);
        if (processingRequest != null) {
            return processingRequest;
        } else { throw new ItemNotFoundException("Item Not Found"); }
    }

    @Transactional
    @Override
    public void saveProcessingRequest(ProcessingRequest processingRequest) {
        processingRequestRepositoryInterface.saveOrUpdate(processingRequest);
    }

    @Transactional
    @Override
    public ProcessingRequest saveProcessingRequest(Client client) {
        ProcessingRequest processingRequest = new ProcessingRequest();
        processingRequest.setReceivedOn(GenerateTimestamp.timestamp());
        processingRequest.setClient(client);
        processingRequest.setProcessingRequestIdentificationCode(GenerateUUID.uuidNumber());
        processingRequestRepositoryInterface.saveOrUpdate(processingRequest);
        return processingRequest;
    }

    @Transactional
    @Override
    public void updateProcessingRequest(ProcessingRequest processingRequest) {
        processingRequestRepositoryInterface.saveOrUpdate(processingRequest);
    }

    @Transactional
    @Override
    public boolean deleteProcessingRequestById(int id) throws ItemNotFoundException {
        ProcessingRequest processingRequest = processingRequestRepositoryInterface.findById(id);
        if(processingRequest != null){
            processingRequestRepositoryInterface.delete(processingRequest);
            return true;
        }
        return false;
    }

    @Transactional
    @Override
    public void deleteProcessingRequest(ProcessingRequest processingRequest) {
        processingRequestRepositoryInterface.delete(processingRequest);
    }
}
