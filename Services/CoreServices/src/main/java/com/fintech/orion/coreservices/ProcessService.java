package com.fintech.orion.coreservices;

import com.fintech.orion.dataabstraction.entities.orion.Process;
import com.fintech.orion.dataabstraction.entities.orion.ProcessType;
import com.fintech.orion.dataabstraction.entities.orion.ProcessingRequest;
import com.fintech.orion.dataabstraction.exceptions.ItemNotFoundException;
import com.fintech.orion.dataabstraction.helper.GenerateTimestamp;
import com.fintech.orion.dataabstraction.helper.GenerateUUID;
import com.fintech.orion.dataabstraction.repositories.ProcessRepositoryInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class ProcessService implements ProcessServiceInterface {

    @Autowired
    private ProcessRepositoryInterface processRepositoryInterface;

    @Transactional
    @Override
    public List<Process> getProcessList() {
        return processRepositoryInterface.getAll();
    }

    @Transactional
    @Override
    public Process getProcessById(int id) throws ItemNotFoundException {
        Process process = processRepositoryInterface.findById(id);
        if (process != null) {
            return process;
        } else { throw new ItemNotFoundException("Item Not Found"); }
    }

    @Transactional
    @Override
    public void saveProcess(Process process) {
        processRepositoryInterface.saveOrUpdate(process);
    }

    @Transactional
    @Override
    public void updateProcess(Process process) {
        processRepositoryInterface.saveOrUpdate(process);
    }

    @Transactional
    @Override
    public boolean deleteProcessById(int id) throws ItemNotFoundException {
        Process process = processRepositoryInterface.findById(id);
        if(process != null){
            processRepositoryInterface.delete(process);
            return true;
        }
        return false;
    }

    @Transactional
    @Override
    public void deleteProcess(Process process) {
        processRepositoryInterface.delete(process);
    }

    @Transactional
    @Override
    public Process saveProcess(ProcessType processType, ProcessingRequest processingRequest) {
        Process process = new Process();
        process.setRequestSentOn(GenerateTimestamp.timestamp());
        process.setProcessingRequest(processingRequest);
        process.setProcessType(processType);
        process.setProcessIdentificationCode(GenerateUUID.uuidNumber());
        processRepositoryInterface.saveOrUpdate(process);
        return process;
    }
}
