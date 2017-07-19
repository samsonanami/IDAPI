package com.fintech.orion.mapping.process;

import com.fintech.orion.dataabstraction.entities.orion.Process;
import com.fintech.orion.dto.process.ProcessDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Created by TharinduMP on 10/18/2016.
 */
@Component
public class ProcessSetToListMapper {

    @Autowired
    private ProcessMapper processMapper;

    public List<ProcessDTO> map(Set<Process> processes) {
        List<ProcessDTO> processDTOs = new ArrayList<>();
        for(Process p : processes){
            ProcessDTO processDTO = processMapper.processToProcessDTO(p);
            processDTOs.add(processDTO);
        }
        return processDTOs;
    }

    public Set<Process> map(List<ProcessDTO> processes) {
        Set<Process> processSet = new HashSet<>();
        for(ProcessDTO p : processes){
            Process process = processMapper.processDTOToProcess(p);
            processSet.add(process);
        }
        return processSet;
    }
}
