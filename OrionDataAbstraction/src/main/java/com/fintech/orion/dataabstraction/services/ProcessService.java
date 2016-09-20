package com.fintech.orion.dataabstraction.services;

import com.fintech.orion.dataabstraction.entities.orion.Process;
import com.fintech.orion.dataabstraction.exceptions.ItemNotFoundException;
import com.fintech.orion.dataabstraction.repositories.ProcessRepositoryInterface;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * Created by ChathurangaRW on 9/14/2016.
 */
public class ProcessService implements ProcessServiceInterface {

    @Autowired
    private ProcessRepositoryInterface repositoryInterface;

    @Override
    public List<Process> getProcessList() {
        return repositoryInterface.getAll();
    }

    @Override
    public Process getProcessById(int id) throws ItemNotFoundException {
        Process process = repositoryInterface.findById(id);
        if (process != null) {
            return process;
        } else { throw new ItemNotFoundException("Item Not Found"); }
    }

    @Override
    public void saveProcess(Process process) {
        repositoryInterface.saveOrUpdate(process);
    }

    @Override
    public void updateProcess(Process process) {
        repositoryInterface.saveOrUpdate(process);
    }

    @Override
    public boolean deleteProcessById(int id) throws ItemNotFoundException {
        Process process = repositoryInterface.findById(id);
        if(process != null){
            repositoryInterface.delete(process);
            return true;
        }
        return false;
    }

    @Override
    public void deleteProcess(Process process) {
        repositoryInterface.delete(process);
    }
}
