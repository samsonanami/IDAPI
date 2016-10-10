package com.fintech.orion.coreservices;

import com.fintech.orion.dataabstraction.entities.orion.ProcessType;
import com.fintech.orion.dataabstraction.exceptions.ItemNotFoundException;

import java.util.List;

public interface ProcessTypeServiceInterface {
    List<ProcessType> getProcessTypeList();

    ProcessType getProcessTypeById(int id) throws ItemNotFoundException;

    ProcessType getProcessTypeByName(String type) throws ItemNotFoundException;

    void saveOrUpdateProcessType(ProcessType processType);

    boolean deleteProcessTypeById(int id) throws ItemNotFoundException;

    void deleteProcessType(ProcessType processType);
}
