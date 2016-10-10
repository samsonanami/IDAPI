package com.fintech.orion.coreservices;

import com.fintech.orion.dataabstraction.entities.orion.ProcessingStatus;
import com.fintech.orion.dataabstraction.exceptions.ItemNotFoundException;

import java.util.List;

public interface ProcessingStatusServiceInterface {

    List<ProcessingStatus> getProcessingStatusList();

    ProcessingStatus getProcessingStatusById(int id) throws ItemNotFoundException;

    void saveOrUpdateProcessingStatus(ProcessingStatus processingStatus);

    boolean deleteProcessingStatusById(int id) throws ItemNotFoundException;

    void deleteProcessingStatus(ProcessingStatus processingStatus);

}
