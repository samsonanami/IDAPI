package com.fintech.orion.mapping.process;

import com.fintech.orion.dataabstraction.entities.orion.Process;
import com.fintech.orion.dto.process.ProcessDTO;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Set;

/**
 * Created by TharinduMP on 10/18/2016.
 */
@Component
public class ProcessSetToListMapper {

    public List<ProcessDTO> map(Set<Process> processes) {
        return null;
    }

    public Set<Process> map(List<ProcessDTO> processes) {
        return null;
    }
}
