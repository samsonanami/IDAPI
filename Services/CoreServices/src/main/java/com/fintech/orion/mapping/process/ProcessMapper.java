package com.fintech.orion.mapping.process;

import com.fintech.orion.dataabstraction.entities.orion.Process;
import com.fintech.orion.dto.process.ProcessDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

/**
 * Created by TharinduMP on 10/14/2016.
 */
@Mapper
public interface ProcessMapper {


    @Mapping(source = "id", target = "id")
    ProcessDTO processToProcessDTO(Process process);
}
