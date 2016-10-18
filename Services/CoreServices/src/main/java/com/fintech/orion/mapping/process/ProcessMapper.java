package com.fintech.orion.mapping.process;

import com.fintech.orion.dataabstraction.entities.orion.Process;
import com.fintech.orion.dto.process.ProcessDTO;
import org.mapstruct.Mapper;

import java.util.List;

/**
 * Process entity mappings
 */
@Mapper(componentModel = "spring")
public interface ProcessMapper {

    ProcessDTO processToProcessDTO(Process process);

    Process processDTOToProcess(ProcessDTO processDTO);

    List<ProcessDTO> processesToProcessDTOs(List<Process> processes);
}
