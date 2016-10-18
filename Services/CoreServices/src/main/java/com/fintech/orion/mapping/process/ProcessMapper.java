package com.fintech.orion.mapping.process;

import com.fintech.orion.dataabstraction.entities.orion.Process;
import com.fintech.orion.dto.process.ProcessDTO;
import org.mapstruct.Mapper;

/**
 * Created by TharinduMP on 10/14/2016.
 */
@Mapper(componentModel = "spring")
public interface ProcessMapper {

    ProcessDTO processToProcessDTO(Process process);

    Process processDTOToProcess(ProcessDTO processDTO);
}
