package com.fintech.orion.mapping.processtype;

import com.fintech.orion.dataabstraction.entities.orion.ProcessType;
import com.fintech.orion.dto.processtype.ProcessTypeDTO;
import org.mapstruct.Mapper;

/**
 * Created by ChathurangaRW on 10/17/2016.
 */
@Mapper(componentModel = "spring")
public interface ProcessTypeMapper {

    ProcessTypeDTO processTypeToProcessTypeDTO(ProcessType processType);

    ProcessType processTypeDTOToProcessType(ProcessTypeDTO processTypeDTO);

}
