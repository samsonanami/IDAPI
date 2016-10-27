package com.fintech.orion.mapping.resource;

import com.fintech.orion.dataabstraction.entities.orion.Resource;
import com.fintech.orion.dto.resource.ResourceDTO;
import com.fintech.orion.mapping.client.ClientMapper;
import com.fintech.orion.mapping.process.ProcessMapper;
import com.fintech.orion.mapping.resourcetype.ResourceTypeMapper;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

import java.util.List;

/**
 * Resource entity mappings
 */
@Mapper(uses = {ClientMapper.class, ProcessMapper.class, ResourceTypeMapper.class} ,componentModel = "spring")
public interface ResourceMapper {

    @Mappings({
            @Mapping(target = "clientDTO", source = "client"),
            @Mapping(target = "processDTO", source = "process"),
            @Mapping(target = "resourceTypeDTO", source = "resourceType")
    })
    ResourceDTO resourceToResourceDTO(Resource resource);

    List<ResourceDTO> resourcesToResourceDTOs(List<Resource> resource);
    
}
