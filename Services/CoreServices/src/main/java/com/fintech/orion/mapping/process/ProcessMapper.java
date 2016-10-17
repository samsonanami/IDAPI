package com.fintech.orion.mapping.process;

import com.fintech.orion.dataabstraction.entities.orion.Process;
import com.fintech.orion.dto.process.ProcessDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

/**
 * Created by TharinduMP on 10/14/2016.
 */
@Mapper
public interface ProcessMapper {


    @Mappings({
            @Mapping(source = "id", target = "id"),
            @Mapping(source = "processType", target = "processType"),
            @Mapping(source = "processingRequest", target = "processingRequest"),
            @Mapping(source = "id", target = "id"),
            @Mapping(source = "id", target = "id"),
            @Mapping(source = "id", target = "id"),
            @Mapping(source = "id", target = "id")
    })
    ProcessDTO processToProcessDTO(Process process);
}
