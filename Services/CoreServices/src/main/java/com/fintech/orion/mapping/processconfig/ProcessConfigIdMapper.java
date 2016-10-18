package com.fintech.orion.mapping.processconfig;

import com.fintech.orion.dataabstraction.entities.orion.ProcessConfigId;
import com.fintech.orion.dto.processconfig.ProcessConfigDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

/**
 * ProcessConfigId entity mapper
 */
@Mapper
public interface ProcessConfigIdMapper {

    @Mappings({
            @Mapping(source = "processConfigDTO.processType", target = "processType"),
            @Mapping(source = "processConfigDTO.key", target = "key"),
    })
    ProcessConfigId processConfigDTOToProcessConfigId(ProcessConfigDTO processConfigDTO);

}
