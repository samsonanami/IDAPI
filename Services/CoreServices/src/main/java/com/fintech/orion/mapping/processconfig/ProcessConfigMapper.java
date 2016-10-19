package com.fintech.orion.mapping.processconfig;

import com.fintech.orion.dataabstraction.entities.orion.ProcessConfig;
import com.fintech.orion.dto.processconfig.ProcessConfigDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

import java.util.List;

/**
 * ProcessConfig entity mappings
 */
@Mapper(componentModel = "spring")
public interface ProcessConfigMapper {

    @Mappings({
            @Mapping(source = "processConfig.id.processType", target = "processType"),
            @Mapping(source = "processConfig.id.key", target = "key")
    })
    ProcessConfigDTO processConfigToProcessConfigDTO(ProcessConfig processConfig);

    List<ProcessConfigDTO> processConfigsToProcessConfigDTOs(List<ProcessConfig> processConfigs);

}
