package com.fintech.orion.coreservices;

import com.fintech.orion.dataabstraction.entities.orion.ProcessTypeLicense;
import com.fintech.orion.dataabstraction.exceptions.ItemNotFoundException;
import com.fintech.orion.dataabstraction.repositories.ProcessTypeLicenseRepositoryInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class ProcessTypeLicenseService implements ProcessTypeLicenseServiceInterface {

    @Autowired
    private ProcessTypeLicenseRepositoryInterface processTypeLicenseRepositoryInterface;

    @Transactional
    @Override
    public List<ProcessTypeLicense> getProcessTypeLicenseList() {
        return processTypeLicenseRepositoryInterface.getAll();
    }

    @Transactional
    @Override
    public ProcessTypeLicense getProcessTypeLicenseById(int id) throws ItemNotFoundException {
        ProcessTypeLicense processTypeLicense = processTypeLicenseRepositoryInterface.findById(id);
        if (processTypeLicense != null) {
            return processTypeLicense;
        } else { throw new ItemNotFoundException("Item Not Found"); }
    }

    @Transactional
    @Override
    public void saveProcessTypeLicense(ProcessTypeLicense processTypeLicense) {
        processTypeLicenseRepositoryInterface.saveOrUpdate(processTypeLicense);
    }

    @Transactional
    @Override
    public void updateProcessTypeLicense(ProcessTypeLicense processTypeLicense) {
        processTypeLicenseRepositoryInterface.saveOrUpdate(processTypeLicense);
    }

    @Transactional
    @Override
    public boolean deleteProcessTypeLicenseById(int id) throws ItemNotFoundException {
        ProcessTypeLicense processTypeLicense = processTypeLicenseRepositoryInterface.findById(id);
        if(processTypeLicense != null){
            processTypeLicenseRepositoryInterface.delete(processTypeLicense);
            return true;
        }
        return false;
    }

    @Transactional
    @Override
    public void deleteProcessTypeLicense(ProcessTypeLicense processTypeLicense) {
        processTypeLicenseRepositoryInterface.delete(processTypeLicense);
    }
}
