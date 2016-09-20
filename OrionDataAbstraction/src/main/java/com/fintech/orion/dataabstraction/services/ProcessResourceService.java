package com.fintech.orion.dataabstraction.services;

import com.fintech.orion.dataabstraction.entities.orion.ProcessResource;
import com.fintech.orion.dataabstraction.exceptions.ItemNotFoundException;
import com.fintech.orion.dataabstraction.repositories.ProcessResourceRepositoryInterface;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * Created by ChathurangaRW on 9/19/2016.
 */
public class ProcessResourceService implements ProcessResourceServiceInterface {

    @Autowired
    private ProcessResourceRepositoryInterface repositoryInterface;

    @Override
    public List<ProcessResource> getProcessResourceList() {
        return repositoryInterface.getAll();
    }

    @Override
    public ProcessResource getProcessResourceById(int id) throws ItemNotFoundException {
        ProcessResource processResource = repositoryInterface.findById(id);
        if (processResource != null) {
            return processResource;
        } else { throw new ItemNotFoundException("Item Not Found"); }
    }

    @Override
    public void saveProcessResource(ProcessResource processResource) {
        repositoryInterface.saveOrUpdate(processResource);
    }

    @Override
    public boolean deleteProcessResourceById(int id) throws ItemNotFoundException {
        ProcessResource processResource = repositoryInterface.findById(id);
        if(processResource != null){
            repositoryInterface.delete(processResource);
            return true;
        }
        return false;
    }

    @Override
    public void updateProcessResource(ProcessResource processResource) {
        repositoryInterface.saveOrUpdate(processResource);
    }

    @Override
    public void deleteProcessResource(ProcessResource processResource) {
        repositoryInterface.delete(processResource);
    }
}
