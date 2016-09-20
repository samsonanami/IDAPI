package com.fintech.orion.dataabstraction.services;

import com.fintech.orion.dataabstraction.entities.orion.ProcessTypeLicense;
import com.fintech.orion.dataabstraction.exceptions.ItemNotFoundException;

import java.util.List;

/**
 * Created by ChathurangaRW on 9/19/2016.
 */
public interface ProcessTypeLicenseServiceInterface {
    List<ProcessTypeLicense> getProcessTypeLicenseList();

    ProcessTypeLicense getProcessTypeLicenseById(int id) throws ItemNotFoundException;

    void saveProcessTypeLicense(ProcessTypeLicense processTypeLicense);

    void updateProcessTypeLicense(ProcessTypeLicense processTypeLicense);

    boolean deleteProcessTypeLicenseById(int id) throws ItemNotFoundException;

    void deleteProcessTypeLicense(ProcessTypeLicense processTypeLicense);
}
