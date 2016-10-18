package com.fintech.orion.mapping.resource;

import com.fintech.orion.dataabstraction.entities.orion.Resource;
import com.fintech.orion.dto.resource.ResourceDTO;
import org.mapstruct.Mapper;

import java.util.List;

/**
 * Resource entity mappings
 */
@Mapper(componentModel = "spring")
public interface ResourceMapper {

    ResourceDTO resourceToResourceDTO(Resource Resource);

    Resource resourceDTOToResource(ResourceDTO ResourceDTO);

    List<ResourceDTO> resourcesToResourceDTOs(List<Resource> Resources);
    
}
