package com.fintech.orion.mapping.process;

import com.fintech.orion.dataabstraction.entities.orion.Process;
import com.fintech.orion.dto.process.ProcessDTO;
import com.fintech.orion.mapping.processingstatus.ProcessingStatusMapper;
import com.fintech.orion.mapping.processtype.ProcessTypeMapper;
import com.fintech.orion.mapping.response.ResponseMapper;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.Mappings;

/**
 * Process entity mappings
 */
@Mapper(uses = {ProcessTypeMapper.class,
        ProcessingStatusMapper.class, ResponseMapper.class}, componentModel = "spring")
public interface ProcessMapper {

    @Mappings({
            @Mapping(target = "processTypeDTO", source = "processType"),
            @Mapping(target = "processingStatusDTO", source = "processingStatus"),
            @Mapping(target = "responseDTO", source = "response")
    })
    ProcessDTO processToProcessDTO(Process process);

    @Mappings({
            @Mapping(target = "processType", source = "processTypeDTO"),
            @Mapping(target = "processingStatus", source = "processingStatusDTO"),
            @Mapping(target = "response", source = "responseDTO")
    })
    Process processDTOToProcess(ProcessDTO processDTO);

    @Mappings({
            @Mapping(target = "processingStatus", source = "processingStatusDTO"),
            @Mapping(target = "processIdentificationCode", ignore = true),
            @Mapping(target = "response", source = "responseDTO")
    })
    void updateProcess(ProcessDTO processDTO, @MappingTarget Process process);
}
