package com.fintech.orion.dataabstraction.services;

import com.fintech.orion.dataabstraction.entities.orion.ProcessingRequest;
import com.fintech.orion.dataabstraction.exceptions.ItemNotFoundException;
import com.fintech.orion.dataabstraction.repositories.ProcessingRequestRepositoryInterface;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * Created by ChathurangaRW on 9/14/2016.
 */
public class ProcessingRequestService implements ProcessingRequestServiceInterface {

    @Autowired
    private ProcessingRequestRepositoryInterface repositoryInterface;

    @Override
    public List<ProcessingRequest> getProcessingRequestList() {
        return repositoryInterface.getAll();
    }

    @Override
    public ProcessingRequest getProcessingRequestById(int id) throws ItemNotFoundException {
        ProcessingRequest processingRequest = repositoryInterface.findById(id);
        if (processingRequest != null) {
            return processingRequest;
        } else { throw new ItemNotFoundException("Item Not Found"); }
    }

    @Override
    public void saveProcessingRequest(ProcessingRequest processingRequest) {
        repositoryInterface.saveOrUpdate(processingRequest);
    }

    @Override
    public void updateProcessingRequest(ProcessingRequest processingRequest) {
        repositoryInterface.saveOrUpdate(processingRequest);
    }

    @Override
    public boolean deleteProcessingRequestById(int id) throws ItemNotFoundException {
        ProcessingRequest processingRequest = repositoryInterface.findById(id);
        if(processingRequest != null){
            repositoryInterface.delete(processingRequest);
            return true;
        }
        return false;
    }

    @Override
    public void deleteProcessingRequest(ProcessingRequest processingRequest) {
        repositoryInterface.delete(processingRequest);
    }
}
