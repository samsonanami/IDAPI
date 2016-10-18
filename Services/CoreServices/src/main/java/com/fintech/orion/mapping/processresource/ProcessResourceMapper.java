package com.fintech.orion.mapping.processresource;

import com.fintech.orion.dataabstraction.entities.orion.ProcessResource;
import com.fintech.orion.dto.processresource.ProcessResourceDTO;
import org.mapstruct.Mapper;

import java.util.List;

/**
 * ProcessResource entity mappings
 */
@Mapper(componentModel = "spring")
public interface ProcessResourceMapper {

    ProcessResourceDTO processResourceToProcessResourceDTO(ProcessResource processResource);

    ProcessResource processResourceDTOToProcessResource(ProcessResourceDTO processResource);

    List<ProcessResourceDTO> processResourcesToProcessResourceDTOs(List<ProcessResource> processResources);
}
