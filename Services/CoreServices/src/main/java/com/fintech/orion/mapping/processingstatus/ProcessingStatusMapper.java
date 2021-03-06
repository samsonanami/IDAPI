package com.fintech.orion.mapping.processingstatus;

import com.fintech.orion.dataabstraction.entities.orion.ProcessingStatus;
import com.fintech.orion.dto.processingstatus.ProcessingStatusDTO;
import org.mapstruct.Mapper;

/**
 * ProcessingStatus entity mappings
 */
@Mapper(componentModel = "spring")
public interface ProcessingStatusMapper {

    ProcessingStatusDTO processingStatusToProcessingStatusDTO(ProcessingStatus processingStatus);

    ProcessingStatus processingStatusDTOToProcessingStatus(ProcessingStatusDTO processingStatusDTO);

}
