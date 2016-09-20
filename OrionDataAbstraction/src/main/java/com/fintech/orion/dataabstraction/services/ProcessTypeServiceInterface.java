package com.fintech.orion.dataabstraction.services;

import com.fintech.orion.dataabstraction.entities.orion.ProcessType;
import com.fintech.orion.dataabstraction.exceptions.ItemNotFoundException;

import java.util.List;

/**
 * Created by ChathurangaRW on 9/19/2016.
 */
public interface ProcessTypeServiceInterface {
    List<ProcessType> getProcessTypeList();

    ProcessType getProcessTypeById(int id) throws ItemNotFoundException;

    void saveProcessType(ProcessType processType);

    void updateProcessType(ProcessType processType);

    boolean deleteProcessTypeById(int id) throws ItemNotFoundException;

    void deleteProcessType(ProcessType processType);
}
