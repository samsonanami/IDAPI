package com.fintech.orion.dataabstraction.services;

import com.fintech.orion.dataabstraction.entities.orion.ProcessTypeLicense;
import com.fintech.orion.dataabstraction.exceptions.ItemNotFoundException;
import com.fintech.orion.dataabstraction.repositories.ProcessTypeLicenseRepositoryInterface;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * Created by ChathurangaRW on 9/19/2016.
 */
public class ProcessTypeLicenseService implements ProcessTypeLicenseServiceInterface {

    @Autowired
    private ProcessTypeLicenseRepositoryInterface repositoryInterface;

    @Override
    public List<ProcessTypeLicense> getProcessTypeLicenseList() {
        return repositoryInterface.getAll();
    }

    @Override
    public ProcessTypeLicense getProcessTypeLicenseById(int id) throws ItemNotFoundException {
        ProcessTypeLicense processTypeLicense = repositoryInterface.findById(id);
        if (processTypeLicense != null) {
            return processTypeLicense;
        } else { throw new ItemNotFoundException("Item Not Found"); }
    }

    @Override
    public void saveProcessTypeLicense(ProcessTypeLicense processTypeLicense) {
        repositoryInterface.saveOrUpdate(processTypeLicense);
    }

    @Override
    public void updateProcessTypeLicense(ProcessTypeLicense processTypeLicense) {
        repositoryInterface.saveOrUpdate(processTypeLicense);
    }

    @Override
    public boolean deleteProcessTypeLicenseById(int id) throws ItemNotFoundException {
        ProcessTypeLicense processTypeLicense = repositoryInterface.findById(id);
        if(processTypeLicense != null){
            repositoryInterface.delete(processTypeLicense);
            return true;
        }
        return false;
    }

    @Override
    public void deleteProcessTypeLicense(ProcessTypeLicense processTypeLicense) {
        repositoryInterface.delete(processTypeLicense);
    }
}
