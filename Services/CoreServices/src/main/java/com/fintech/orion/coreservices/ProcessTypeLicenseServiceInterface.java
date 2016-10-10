package com.fintech.orion.coreservices;

import com.fintech.orion.dataabstraction.entities.orion.ProcessTypeLicense;
import com.fintech.orion.dataabstraction.exceptions.ItemNotFoundException;

import java.util.List;

public interface ProcessTypeLicenseServiceInterface {
    List<ProcessTypeLicense> getProcessTypeLicenseList();

    ProcessTypeLicense getProcessTypeLicenseById(int id) throws ItemNotFoundException;

    void saveOrUpdateProcessTypeLicense(ProcessTypeLicense processTypeLicense);

    boolean deleteProcessTypeLicenseById(int id) throws ItemNotFoundException;

    void deleteProcessTypeLicense(ProcessTypeLicense processTypeLicense);
}
