package com.fintech.orion.mapping.processingrequest;

import com.fintech.orion.dataabstraction.entities.orion.ProcessingRequest;
import com.fintech.orion.dto.processingrequest.ProcessingRequestDTO;
import com.fintech.orion.mapping.process.ProcessSetToListMapper;
import org.mapstruct.Mapper;

/**
 * ProcessingRequest entity mappings
 */
@Mapper(uses = ProcessSetToListMapper.class, componentModel = "spring")
public interface ProcessingRequestMapper {

    ProcessingRequestDTO processingRequestToProcessingRequestDTO(ProcessingRequest processingRequest);

    ProcessingRequest processingRequestDTOToProcessingRequest(ProcessingRequestDTO processingRequestDTO);

}
