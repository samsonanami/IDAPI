package com.fintech.orion.mapping.resourcetype;

import com.fintech.orion.dataabstraction.entities.orion.ResourceType;
import com.fintech.orion.dto.resourcetype.ResourceTypeDTO;
import org.mapstruct.Mapper;

import java.util.List;

/**
 * ResourceType entity mappings
 */
@Mapper(componentModel = "spring")
public interface ResourceTypeMapper {

    ResourceTypeDTO resourceTypeToResourceTypeDTO(ResourceType resourceType);

    ResourceType resourceTypeDTOToResourceType(ResourceTypeDTO resourceTypeDTO);

    List<ResourceTypeDTO> resourceTypesToResourceTypeDTOs(List<ResourceType> resourceTypes);
    
}
