package com.fintech.orion.mapping.process;

import com.fintech.orion.dataabstraction.entities.orion.Process;
import com.fintech.orion.dto.process.ProcessDTO;
import com.fintech.orion.mapping.processingrequest.ProcessingRequestMapper;
import com.fintech.orion.mapping.processingstatus.ProcessingStatusMapper;
import com.fintech.orion.mapping.processtype.ProcessTypeMapper;
import com.fintech.orion.mapping.response.ResponseMapper;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

import java.util.List;

/**
 * Process entity mappings
 */
@Mapper(uses = {ProcessSetToListMapper.class,
        ProcessTypeMapper.class,
        ProcessingRequestMapper.class,
        ProcessingStatusMapper.class,
        ResponseMapper.class}, componentModel = "spring")
public interface ProcessMapper {

    @Mappings({
            @Mapping(target = "processTypeDTO", source = "processType"),
            @Mapping(target = "processingRequestDTO", source = "processingRequest"),
            @Mapping(target = "processingStatusDTO", source = "processingStatus"),
            @Mapping(target = "responseDTO", source = "response")
    })
    ProcessDTO processToProcessDTO(Process process);

    @Mappings({
            @Mapping(target = "processType", source = "processTypeDTO"),
            @Mapping(target = "processingRequest", source = "processingRequestDTO"),
            @Mapping(target = "processingStatus", source = "processingStatusDTO"),
            @Mapping(target = "response", source = "responseDTO")
    })
    Process processDTOToProcess(ProcessDTO processDTO);

    List<ProcessDTO> processesToProcessDTOs(List<Process> processes);
}