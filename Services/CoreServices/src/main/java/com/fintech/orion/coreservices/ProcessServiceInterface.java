package com.fintech.orion.coreservices;

import com.fintech.orion.dataabstraction.entities.orion.Process;
import com.fintech.orion.dataabstraction.exceptions.ItemNotFoundException;

import java.util.List;

public interface ProcessServiceInterface {
    List<Process> getProcessList();

    Process getProcessById(int id) throws ItemNotFoundException;

    void saveProcess(Process process);

    void updateProcess(Process process);

    boolean deleteProcessById(int id) throws ItemNotFoundException;

    void deleteProcess(Process process);
}
