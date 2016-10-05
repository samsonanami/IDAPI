package com.fintech.orion.coreservices;

import com.fintech.orion.dataabstraction.entities.orion.ProcessResource;
import com.fintech.orion.dataabstraction.exceptions.ItemNotFoundException;
import com.fintech.orion.dataabstraction.repositories.ProcessResourceRepositoryInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class ProcessResourceService implements ProcessResourceServiceInterface {

    @Autowired
    private ProcessResourceRepositoryInterface processResourceRepositoryInterface;

    @Transactional
    @Override
    public List<ProcessResource> getProcessResourceList() {
        return processResourceRepositoryInterface.getAll();
    }

    @Transactional
    @Override
    public ProcessResource getProcessResourceById(int id) throws ItemNotFoundException {
        ProcessResource processResource = processResourceRepositoryInterface.findById(id);
        if (processResource != null) {
            return processResource;
        } else { throw new ItemNotFoundException("Item Not Found"); }
    }

    @Transactional
    @Override
    public void saveProcessResource(ProcessResource processResource) {
        processResourceRepositoryInterface.saveOrUpdate(processResource);
    }

    @Transactional
    @Override
    public boolean deleteProcessResourceById(int id) throws ItemNotFoundException {
        ProcessResource processResource = processResourceRepositoryInterface.findById(id);
        if(processResource != null){
            processResourceRepositoryInterface.delete(processResource);
            return true;
        }
        return false;
    }

    @Transactional
    @Override
    public void updateProcessResource(ProcessResource processResource) {
        processResourceRepositoryInterface.saveOrUpdate(processResource);
    }

    @Transactional
    @Override
    public void deleteProcessResource(ProcessResource processResource) {
        processResourceRepositoryInterface.delete(processResource);
    }
}
