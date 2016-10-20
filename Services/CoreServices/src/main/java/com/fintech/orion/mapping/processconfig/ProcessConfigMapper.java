package com.fintech.orion.mapping.processconfig;

import com.fintech.orion.dataabstraction.entities.orion.ProcessConfig;
import com.fintech.orion.dto.processconfig.ProcessConfigDTO;
import com.fintech.orion.mapping.processtype.ProcessTypeMapper;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

import java.util.List;

/**
 * ProcessConfig entity mappings
 */
@Mapper(uses = ProcessTypeMapper.class, componentModel = "spring")
public interface ProcessConfigMapper {

    @Mappings({
            @Mapping(target = "processType", source = "id.processType"),
            @Mapping(target = "key", source = "id.key"),
            @Mapping(target = "processTypeDTO", source = "processType"),
            @Mapping(target = "value", source = "value")
    })
    ProcessConfigDTO processConfigToProcessConfigDTO(ProcessConfig processConfig);

    @Mappings({
            @Mapping(target = "id", expression = "java( new com.fintech.orion.dataabstraction.entities.orion.ProcessConfigId(processConfigDTO.getProcessType(), processConfigDTO.getKey()))"),
            @Mapping(target = "processType", source = "processTypeDTO"),
            @Mapping(target = "value", source = "value")
    })
    ProcessConfig processConfigDTOToProcessConfig(ProcessConfigDTO processConfigDTO);

    List<ProcessConfigDTO> processConfigsToProcessConfigDTOs(List<ProcessConfig> processConfigs);

}
