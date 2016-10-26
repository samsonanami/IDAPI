package com.fintech.orion.mapping.processtype;

import com.fintech.orion.dataabstraction.entities.orion.ProcessType;
import com.fintech.orion.dto.processtype.ProcessTypeDTO;
import org.mapstruct.Mapper;

import java.util.List;

/**
 * ProcessType entity mappings
 */
@Mapper(componentModel = "spring")
public interface ProcessTypeMapper {

    ProcessTypeDTO processTypeToProcessTypeDTO(ProcessType processType);

    ProcessType processTypeDTOToProcessType(ProcessTypeDTO processTypeDTO);

}
