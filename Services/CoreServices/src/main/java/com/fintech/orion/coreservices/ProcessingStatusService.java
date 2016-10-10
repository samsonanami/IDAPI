package com.fintech.orion.coreservices;

import com.fintech.orion.dataabstraction.entities.orion.ProcessingStatus;
import com.fintech.orion.dataabstraction.exceptions.ItemNotFoundException;
import com.fintech.orion.dataabstraction.repositories.ProcessingStatusRepositoryInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public class ProcessingStatusService implements ProcessingStatusServiceInterface {

    @Autowired
    private ProcessingStatusRepositoryInterface processingStatusRepositoryInterface;

    @Transactional
    @Override
    public List<ProcessingStatus> getProcessingStatusList() {
        return processingStatusRepositoryInterface.getAll();
    }

    @Transactional
    @Override
    public ProcessingStatus getProcessingStatusById(int id) throws ItemNotFoundException {
        ProcessingStatus processingStatus = processingStatusRepositoryInterface.findById(id);
        if (processingStatus != null) {
            return processingStatus;
        } else { throw new ItemNotFoundException("ProcessingStatus Item Not Found"); }
    }

    @Transactional
    @Override
    public void saveProcessingStatus(ProcessingStatus processingStatus) {
        processingStatusRepositoryInterface.saveOrUpdate(processingStatus);
    }

    @Transactional
    @Override
    public void updateProcessingStatus(ProcessingStatus processingStatus) {
        processingStatusRepositoryInterface.saveOrUpdate(processingStatus);
    }

    @Transactional
    @Override
    public boolean deleteProcessingStatusById(int id) throws ItemNotFoundException {
        ProcessingStatus processingStatus = processingStatusRepositoryInterface.findById(id);
        if(processingStatus != null){
            processingStatusRepositoryInterface.delete(processingStatus);
            return true;
        }
        return false;
    }

    @Transactional
    @Override
    public void deleteProcessingStatus(ProcessingStatus processingStatus) {
        processingStatusRepositoryInterface.delete(processingStatus);
    }
}
