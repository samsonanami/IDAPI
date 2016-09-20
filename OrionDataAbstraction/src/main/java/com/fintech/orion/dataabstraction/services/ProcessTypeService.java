package com.fintech.orion.dataabstraction.services;

import com.fintech.orion.dataabstraction.entities.orion.ProcessType;
import com.fintech.orion.dataabstraction.exceptions.ItemNotFoundException;
import com.fintech.orion.dataabstraction.repositories.ProcessTypeRepositoryInterface;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * Created by ChathurangaRW on 9/19/2016.
 */
public class ProcessTypeService implements ProcessTypeServiceInterface {

    @Autowired
    private ProcessTypeRepositoryInterface repositoryInterface;

    @Override
    public List<ProcessType> getProcessTypeList() {
        return repositoryInterface.getAll();
    }

    @Override
    public ProcessType getProcessTypeById(int id) throws ItemNotFoundException {
        ProcessType processType = repositoryInterface.findById(id);
        if (processType != null) {
            return processType;
        } else { throw new ItemNotFoundException("Item Not Found"); }
    }

    @Override
    public void saveProcessType(ProcessType processType) {
        repositoryInterface.saveOrUpdate(processType);
    }

    @Override
    public void updateProcessType(ProcessType processType) {
        repositoryInterface.saveOrUpdate(processType);
    }

    @Override
    public boolean deleteProcessTypeById(int id) throws ItemNotFoundException {
        ProcessType processType = repositoryInterface.findById(id);
        if(processType != null){
            repositoryInterface.delete(processType);
            return true;
        }
        return false;
    }

    @Override
    public void deleteProcessType(ProcessType processType) {
        repositoryInterface.delete(processType);
    }
}
