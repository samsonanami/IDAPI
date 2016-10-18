package com.fintech.orion.mapping.processrequest;

import com.fintech.orion.dataabstraction.entities.orion.ProcessingRequest;
import com.fintech.orion.dto.processingrequest.ProcessingRequestDTO;
import com.fintech.orion.mapping.process.ProcessSetToListMapper;
import org.mapstruct.Mapper;

/**
 * Created by TharinduMP on 10/18/2016.
 * ProcessRequestMapper
 */
@Mapper(uses = ProcessSetToListMapper.class,componentModel = "spring")
public interface ProcessRequestMapper {

    ProcessingRequestDTO processingRequestToProcessingRequestDTO(ProcessingRequest processingRequest);
}
