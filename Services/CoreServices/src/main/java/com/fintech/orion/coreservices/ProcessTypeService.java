package com.fintech.orion.coreservices;

import com.fintech.orion.dataabstraction.entities.orion.ProcessType;
import com.fintech.orion.dataabstraction.exceptions.ItemNotFoundException;
import com.fintech.orion.dataabstraction.repositories.ProcessTypeRepositoryInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class ProcessTypeService implements ProcessTypeServiceInterface {

    @Autowired
    private ProcessTypeRepositoryInterface processTypeRepositoryInterface;

    @Transactional
    @Override
    public List<ProcessType> getProcessTypeList() {
        return processTypeRepositoryInterface.getAll();
    }

    @Transactional
    @Override
    public ProcessType getProcessTypeById(int id) throws ItemNotFoundException {
        ProcessType processType = processTypeRepositoryInterface.findById(id);
        if (processType != null) {
            return processType;
        } else { throw new ItemNotFoundException("Item Not Found"); }
    }

    @Transactional
    @Override
    public void saveProcessType(ProcessType processType) {
        processTypeRepositoryInterface.saveOrUpdate(processType);
    }

    @Transactional
    @Override
    public void updateProcessType(ProcessType processType) {
        processTypeRepositoryInterface.saveOrUpdate(processType);
    }

    @Transactional
    @Override
    public boolean deleteProcessTypeById(int id) throws ItemNotFoundException {
        ProcessType processType = processTypeRepositoryInterface.findById(id);
        if(processType != null){
            processTypeRepositoryInterface.delete(processType);
            return true;
        }
        return false;
    }

    @Transactional
    @Override
    public void deleteProcessType(ProcessType processType) {
        processTypeRepositoryInterface.delete(processType);
    }
}
