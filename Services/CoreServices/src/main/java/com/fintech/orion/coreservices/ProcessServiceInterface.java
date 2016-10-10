package com.fintech.orion.coreservices;

import com.fintech.orion.dataabstraction.entities.orion.Process;
import com.fintech.orion.dataabstraction.entities.orion.ProcessType;
import com.fintech.orion.dataabstraction.entities.orion.ProcessingRequest;
import com.fintech.orion.dataabstraction.entities.orion.ProcessingStatus;
import com.fintech.orion.dataabstraction.exceptions.ItemNotFoundException;

import java.util.List;

public interface ProcessServiceInterface {
    List<Process> getProcessList();

    Process getProcessById(int id) throws ItemNotFoundException;

    void saveOrUpdateProcess(Process process);

    boolean deleteProcessById(int id) throws ItemNotFoundException;

    void deleteProcess(Process process);

    Process saveProcess(ProcessType processType, ProcessingRequest processingRequest, ProcessingStatus processingStatus);

}
